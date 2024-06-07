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
import com.project.hana_piece.product.exception.InstallmentSavingNotFoundException;
import com.project.hana_piece.product.exception.ProductNotFoundException;
import com.project.hana_piece.product.exception.SavingNotFoundException;
import com.project.hana_piece.product.repository.EnrolledProductRepository;
import com.project.hana_piece.product.repository.ProductRepository;
import com.project.hana_piece.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

//    public RecommendationResponse recommendProducts(Long userGoalId) {
//        Optional<UserGoal> userGoalOptional = userGoalRepository.findById(userGoalId);
//        if (userGoalOptional.isEmpty()) {
//            throw new UserGoalNotFoundException(userGoalId);
//        }
//
//        UserGoal userGoal = userGoalOptional.get();
//        List<Product> products = productRepository.findAll();
//        List<EnrolledProduct> enrolledProducts = enrolledProductRepository.findByUserGoalUserGoalId(userGoalId);
//
//        // 이미 등록된 적금 상품 ID 리스트
//        List<Long> enrolledProductIds = enrolledProducts.stream()
//                .map(enrolledProduct -> enrolledProduct.getProduct().getProductId())
//                .toList();
//
//        // 추천 상품 목록 생성
//        GeminiPrompt geminiPrompt = buildPromptMessage(userGoal, products, enrolledProductIds);
//        GeminiCallResponse aiResponse = aiService.callGenerativeLanguageApi(geminiPrompt);
//        String aiResponseMessage = aiResponse.message();
//        String[] productIdStringList = aiResponseMessage.split(",");
//        List<Long> productIdList = Arrays.stream(productIdStringList)
//                .map(string -> Long.valueOf(string.trim()))
////                .filter(productId -> !enrolledProductIds.contains(productId))
////                .filter(productId -> {
////                    if (!"HOUSE".equals(userGoal.getGoalTypeCd()) && productId == 14) {
////                        return false;
////                    }
////                    return true;
////                })
//                .toList();
//
//        List<ProductGetResponse> recommendedProducts = new ArrayList<>();
//        for (Long productId : productIdList) {
//            Product product = products.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
//            if (product != null) {
//                recommendedProducts.add(ProductGetResponse.fromProduct(product));
//            }
//        }
//
//        // 등록된 적금 목록 생성
//        List<EnrolledProductResponse> enrolledProductResponses = enrolledProducts.stream()
//                .map(EnrolledProductResponse::fromEntity)
//                .toList();
//
//        return new RecommendationResponse(recommendedProducts, enrolledProductResponses);
//    }
//
//    private GeminiPrompt buildPromptMessage(UserGoal userGoal, List<Product> products, List<Long> enrolledProductIds) {
//        String requests = "아래의 상품 리스트 중 " + userGoal.getGoalAlias() + "을 위한 하나은행 적금을 최소 7개 추천해줘. "
//                + "목표 금액은 " + userGoal.getAmount() + "원이고, 시작 날짜는 " + userGoal.getGoalBeginDate()
//                + "이며, 목표를 달성하기 위한 기간은 " + userGoal.getDuration() + "개월이다. ";
//
//        StringBuilder constraintsBuilder = new StringBuilder();
//        constraintsBuilder.append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해줘. ")
//                .append("상품 ID는 이미 등록된 적금 상품 ID를 제외하고 추천해야 해. ");
//
//        if ("HOUSE".equals(userGoal.getGoalTypeCd())) {
//            constraintsBuilder.append("goal_type_cd가 HOUSE인 경우 청년 주택드림 청약통장(ProductId: 14)을 포함해야 해. ");
//        } else {
//            constraintsBuilder.append("goal_type_cd가 HOUSE가 아닌 경우 청년 주택드림 청약통장(ProductId: 14)을 추천하지 말아야 해. ");
//        }
//
//        if ("CAR".equals(userGoal.getGoalTypeCd())) {
//            constraintsBuilder.append("goal_type_cd가 CAR인 경우, 높은 금리와 유연한 적립 조건을 갖춘 상품을 고려해줘. ");
//        }
//
//        StringBuilder exampleDataBuilder = new StringBuilder("아래는 상품 리스트야: ");
//        products.stream()
//                .filter(product -> !enrolledProductIds.contains(product.getProductId()) &&
//                        ("HOUSE".equals(userGoal.getGoalTypeCd()) || product.getProductId() != 14))
//                .forEach(product -> exampleDataBuilder.append("ProductId: ").append(product.getProductId())
//                        .append(", ProductName: ").append(product.getProductNm())
//                        .append(", ProductTermYear: ").append(product.getTermYear())
//                        .append(", ProductInterestRate: ").append(product.getInterestRate())
//                        .append("; "));
//
//        String responseFormat = "추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해줘.";
//
//        return GeminiPrompt.builder()
//                .requests(requests)
//                .constraints(constraintsBuilder.toString())
//                .responseFormat(responseFormat)
//                .exampleData(exampleDataBuilder.toString())
//                .build();
//    }

    public RecommendationResponse recommendProducts(Long userGoalId) {
        Optional<UserGoal> userGoalOptional = userGoalRepository.findById(userGoalId);
        if (userGoalOptional.isEmpty()) {
            throw new UserGoalNotFoundException(userGoalId);
        }

        UserGoal userGoal = userGoalOptional.get();
        List<Product> products = productRepository.findAll();
        List<EnrolledProduct> enrolledProducts = enrolledProductRepository.findByUserGoalUserGoalId(userGoalId);

        // 이미 등록된 적금 상품 ID 리스트
        List<Long> enrolledProductIds = enrolledProducts.stream()
                .map(enrolledProduct -> enrolledProduct.getProduct().getProductId())
                .toList();

        // 추천 상품 목록 생성
        GeminiPrompt geminiPrompt = buildPromptMessage(userGoal, products, enrolledProductIds);
        GeminiCallResponse aiResponse = aiService.callGenerativeLanguageApi(geminiPrompt);
        String aiResponseMessage = aiResponse.message();
        String[] productIdStringList = aiResponseMessage.split(",");
        List<Long> productIdList = Arrays.stream(productIdStringList)
                .map(string -> Long.valueOf(string.trim()))
                .filter(productId -> !enrolledProductIds.contains(productId))
                .filter(productId -> {
                    if (!"HOUSE".equals(userGoal.getGoalTypeCd()) && productId == 14) {
                        return false;
                    }
                    return true;
                })
                .toList();

        List<ProductGetResponse> recommendedProducts = new ArrayList<>();
        for (Long productId : productIdList) {
            Product product = products.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
            if (product != null) {
                recommendedProducts.add(ProductGetResponse.fromProduct(product));
            }
        }

        // 등록된 적금 목록 생성
        List<EnrolledProductResponse> enrolledProductResponses = enrolledProducts.stream()
                .map(EnrolledProductResponse::fromEntity)
                .toList();

        return new RecommendationResponse(recommendedProducts, enrolledProductResponses);
    }

    private GeminiPrompt buildPromptMessage(UserGoal userGoal, List<Product> products, List<Long> enrolledProductIds) {
        String requests = "아래의 상품 리스트 중 " + userGoal.getGoalAlias() + "을 위한 하나은행 적금을 최소 7개 추천해줘. "
                + "목표 금액은 " + userGoal.getAmount() + "원이고, 시작 날짜는 " + userGoal.getGoalBeginDate()
                + "이며, 목표를 달성하기 위한 기간은 " + userGoal.getDuration() + "개월이다. ";

        StringBuilder constraintsBuilder = new StringBuilder();
        constraintsBuilder.append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해줘. ")
                .append("상품 ID는 이미 등록된 적금 상품 ID를 제외하고 추천해야 해. ");

        if ("HOUSE".equals(userGoal.getGoalTypeCd())) {
            constraintsBuilder.append("goal_type_cd가 HOUSE인 경우 청년 주택드림 청약통장(ProductId: 14)을 포함해야 해. ");
        } else {
            constraintsBuilder.append("goal_type_cd가 HOUSE가 아닌 경우 청년 주택드림 청약통장(ProductId: 14)을 추천하지 말아야 해. ");
        }

        if ("CAR".equals(userGoal.getGoalTypeCd())) {
            constraintsBuilder.append("goal_type_cd가 CAR인 경우, 높은 금리와 유연한 적립 조건을 갖춘 상품을 고려해줘. ");
        }

        StringBuilder exampleDataBuilder = new StringBuilder("아래는 상품 리스트야: ");
        products.stream()
                .filter(product -> !enrolledProductIds.contains(product.getProductId()))
                .forEach(product -> exampleDataBuilder.append("ProductId: ").append(product.getProductId())
                        .append(", ProductName: ").append(product.getProductNm())
                        .append(", ProductTermYear: ").append(product.getTermYear())
                        .append(", ProductInterestRate: ").append(product.getInterestRate())
                        .append("; "));

        String responseFormat = "추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해줘.";

        return GeminiPrompt.builder()
                .requests(requests)
                .constraints(constraintsBuilder.toString())
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

