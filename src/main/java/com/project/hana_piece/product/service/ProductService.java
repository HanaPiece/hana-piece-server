package com.project.hana_piece.product.service;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.product.domain.EnrolledProduct;
import com.project.hana_piece.product.domain.Product;
import com.project.hana_piece.product.dto.*;
import com.project.hana_piece.product.exception.ProductNotFoundException;
import com.project.hana_piece.product.repository.EnrolledProductRepository;
import com.project.hana_piece.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EnrolledProductRepository enrolledProductRepository;
    private final UserGoalRepository userGoalRepository;
    private final AiService aiService;

    public List<ProductGetResponse> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductGetResponse::fromProduct)
                .toList();
    }

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
        String promptMessage = buildPromptMessage(userGoal, products, enrolledProductIds);
        GeminiPrompt geminiPrompt = new GeminiPrompt(promptMessage);
        GeminiCallResponse aiResponse = aiService.callGenerativeLanguageApi(geminiPrompt);
        String aiResponseMessage = aiResponse.message();
        String[] productIdStringList = aiResponseMessage.split(",");
        List<Long> productIdList = Arrays.stream(productIdStringList)
                .map(string -> Long.valueOf(string.trim()))
                .filter(productId -> !enrolledProductIds.contains(productId))
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
                .map(EnrolledProductResponse::new)
                .toList();

        return new RecommendationResponse(recommendedProducts, enrolledProductResponses);
    }

    private String buildPromptMessage(UserGoal userGoal, List<Product> products, List<Long> enrolledProductIds) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("아래의 상품 리스트 중 ")
                .append(userGoal.getGoalTypeCd())
                .append("을 위한 하나은행 적금을 최소 7개 추천해줘. ")
                .append("목표 금액은 ").append(userGoal.getAmount()).append("원이고, 시작 날짜는 ").append(userGoal.getGoalBeginDate())
                .append("이며, 목표를 달성하기 위한 기간은 ").append(userGoal.getDuration()).append("년이다. ")
                .append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해주면 돼. ")
                .append("이 내용의 텍스트만 전달해줘. ")
                .append("아래는 상품 리스트야: ");

        for (Product product : products) {
            if (!enrolledProductIds.contains(product.getProductId())) {
                promptMessage.append("ProductId: ").append(product.getProductId())
                        .append(", ProductName: ").append(product.getProductNm())
                        .append(", ProductTermYear: ").append(product.getTermYear())
                        .append(", ProductInterestRate: ").append(product.getInterestRate()).append("; ");
            }
        }

        return promptMessage.toString();
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        return ProductDetailResponse.fromProduct(productOptional.get());
    }

    public void enrollProduct(EnrollProductRequest request) {
        Optional<Product> productOptional = productRepository.findById(request.productId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(request.productId());
        }

        Optional<UserGoal> userGoalOptional = userGoalRepository.findById(request.userGoalId());
        if (userGoalOptional.isEmpty()) {
            throw new UserGoalNotFoundException(request.userGoalId());
        }

        EnrolledProduct enrolledProduct = EnrolledProduct.builder()
                .product(productOptional.get())
                .userGoal(userGoalOptional.get())
                .contractPeriod(request.contractPeriod())
                .initialAmount(request.initialAmount())
                .autoDebitAmount(request.autoDebitAmount())
                .autoDebitDay(request.autoDebitDay())
                .maturityDate(request.maturityDate())
                .autoRenewal(request.autoRenewal())
                .build();

        enrolledProductRepository.save(enrolledProduct);
    }
}

