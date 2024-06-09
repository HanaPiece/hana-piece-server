package com.project.hana_piece.product.service;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountAutoDebit;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.repository.AccountAutoDebitRepository;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.account.util.AccountNumberGenerator;
import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.product.domain.EnrolledProduct;
import com.project.hana_piece.product.domain.Product;
import com.project.hana_piece.product.dto.*;
import com.project.hana_piece.product.exception.AlreadyEnrolledProductException;
import com.project.hana_piece.product.exception.ProductNotFoundException;
import com.project.hana_piece.product.exception.SavingNotFoundException;
import com.project.hana_piece.product.repository.EnrolledProductRepository;
import com.project.hana_piece.product.repository.ProductRepository;
import com.project.hana_piece.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EnrolledProductRepository enrolledProductRepository;
    private final AccountRepository accountRepository;
    private final AccountAutoDebitRepository accountAutoDebitRepository;
    private final UserGoalRepository userGoalRepository;
    private final AiService aiService;

    public List<ProductGetResponse> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductGetResponse::fromProduct)
                .toList();
    }


    public RecommendationResponse recommendProducts(Long userGoalId) {
        UserGoal userGoal = userGoalRepository.findById(userGoalId).orElseThrow(()-> new UserGoalNotFoundException(userGoalId));

        List<Product> products = productRepository.findAll();
        List<EnrolledProduct> enrolledProducts = enrolledProductRepository.findByUserGoalUserGoalId(userGoalId);

        // 등록된 적금 목록 생성
        List<EnrolledProductResponse> enrolledProductResponses = enrolledProducts.stream()
            .map(EnrolledProductResponse::fromEntity)
            .toList();

        // 추천 상품 목록 생성
        GeminiPrompt geminiPrompt = buildPromptMessage(userGoal, products, enrolledProducts);
        GeminiCallResponse aiResponse = aiService.callGenerativeLanguageApi(geminiPrompt);
        String aiResponseMessage = aiResponse.message();
        System.out.println(geminiPrompt.getTotalPrompt());
        List<Long> productIdList = Arrays.stream(aiResponseMessage.split(","))
                .map(string -> Long.valueOf(string.trim()))
                .toList();

        List<ProductGetResponse> recommendedProductResponse = productIdList.stream().map(productId -> {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
            return ProductGetResponse.fromProduct(product);
        }).toList();

        return new RecommendationResponse(recommendedProductResponse, enrolledProductResponses);
    }

    private GeminiPrompt buildPromptMessage(UserGoal userGoal, List<Product> products, List<EnrolledProduct> enrolledProducts) {
        StringBuilder requests = new StringBuilder();
        requests.append("아래 정보를 바탕으로 목표를 달성하기 위한 아래 상품 리스트(ExampleData) 중 반드시 7개를 추천해줘.\n")
            .append("목표 타입: "+userGoal.getGoalTypeCd()+"\n")
            .append("목표 명: "+userGoal.getGoalAlias()+"\n")
            .append("목표 금액: " + userGoal.getAmount() + "원"+"\n")
            .append("목표 달성 기간: " + userGoal.getDuration() + "개월"+"\n");

        StringBuilder constraints = new StringBuilder();
        constraints.append("아래의 제약 조건들은 중요한 순서대로 정리한 것이야\n")
            .append("1. 상품들을 " + userGoal.getGoalTypeCd() + userGoal.getGoalAlias()+"와 연관된 한글 단어를 10개를 탐색을 한 후 해당 단어와 관련된 상품들을 ExampleData:ProductNm와 관련성이 높은 순서대로 추천해줘\n")
            .append("2. ProductInterestRate 값이 높은 상품 순서대로 추천해줘\n");

        StringBuilder exampleDataBuilder = new StringBuilder();
        List<Long> enrolledProductIds = enrolledProducts.stream()
            .map(enrolledProduct -> enrolledProduct.getProduct().getProductId()).toList();
        List<Product> productList = products.stream()
            .filter(product -> !enrolledProductIds.contains(product.getProductId())).toList();
        productList.forEach(product -> exampleDataBuilder.append("ProductId: ").append(product.getProductId())
                        .append(", ProductNm: ").append(product.getProductNm())
                        .append(", ProductInterestRate: ").append(product.getInterestRate())
                        .append(", ProductInfo: ").append(product.getInfo())
                        .append("; "));

        String responseFormat = "Constraints 중요도가 높은 기준으로 정렬해서 추천 상품의 product_id만 콤마를 기준으로 공백없이 응답해줘.";

        return GeminiPrompt.builder()
                .requests(requests.toString())
                .constraints(constraints.toString())
                .responseFormat(responseFormat)
                .exampleData(exampleDataBuilder.toString())
                .build();
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        return ProductDetailResponse.fromProduct(productOptional.get());
    }

    @Transactional
    public void enrollProduct(EnrollProductRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException(request.productId()));

        UserGoal userGoal = userGoalRepository.findById(request.userGoalId())
                .orElseThrow(() -> new UserGoalNotFoundException(request.userGoalId()));

        User user = userGoal.getUser();

        boolean alreadyEnrolled = enrolledProductRepository.existsByProductAndUserGoal(product, userGoal);
        if (alreadyEnrolled) {
            throw new AlreadyEnrolledProductException();
        }

        EnrolledProduct enrolledProduct = EnrolledProduct.builder()
                .product(product)
                .userGoal(userGoal)
                .contractPeriod(request.contractPeriod())
                .initialAmount(request.initialAmount())
                .autoDebitAmount(request.autoDebitAmount())
                .autoDebitDay(request.autoDebitDay())
                .maturityDate(request.maturityDate())
                .autoRenewal(request.autoRenewal())
                .build();

        enrolledProductRepository.save(enrolledProduct);

        String accountNumber = AccountNumberGenerator.generateAccountNumber();

        Account newAccount = Account.builder()
                .user(user)
                .enrolledProduct(enrolledProduct)
                .accountNumber(accountNumber)
                .accountType(AccountType.INSTALLMENT_SAVING)
                .accountAlias(product.getProductNm() + " 계좌")
                .balance(0L)
                .build();

        Account savedAccount = accountRepository.save(newAccount);

        Account savingAccount = accountRepository.findSavingAccount(user.getUserId())
                .orElseThrow(()->new SavingNotFoundException());

        AccountAutoDebit accountAutoDebit = AccountAutoDebit.builder()
                .account(savingAccount)
                .targetAccountId(savedAccount.getAccountId())
                .autoDebitAmount(enrolledProduct.getAutoDebitAmount())
                .autoDebitDay(enrolledProduct.getAutoDebitDay())
                .build();

        accountAutoDebitRepository.save(accountAutoDebit);
    }
}

