package com.project.hana_piece.product.service;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.common.util.JsonUtil;
import com.project.hana_piece.product.domain.Product;
import com.project.hana_piece.product.dto.ProductGetResponse;
import com.project.hana_piece.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AiService aiService;
    private final JsonUtil jsonUtil;

    public List<ProductGetResponse> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductGetResponse::fromProduct)
                .collect(Collectors.toList());
    }

    public List<ProductGetResponse> recommendProducts(String category) {
        List<Product> products = productRepository.findAll();

        StringBuilder promptMessage = new StringBuilder();

        switch (category.toLowerCase()) {
            case "home":
                promptMessage.append("아래의 상품 리스트 중 집을 위한 하나은행 적금 최소 7개, 최대 10개를 추천순위로 제공해줘. ")
                        .append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해주면 돼. ")
                        .append("이 내용의 텍스트만 전달해줘")
                        .append("우선순위 기준은 다음과 같아: ")
                        .append("1. 기간이 10인 상품이 우선순위가 가장 높아야 해. ")
                        .append("2. 기간이 5이하인 상품은 값이 크면 우선순위가 높아. ")
                        .append("3. 만약 기간이 같다면 이자율이 높은 상품이 우선순위가 높아야 해. ")
                        .append("아래는 상품 리스트야: ");
                break;
            case "car":
                promptMessage.append("아래의 상품 리스트 중 차를 위한 하나은행 적금 최소 7개, 최대 10개를 추천순위로 제공해줘. ")
                        .append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해주면 돼. ")
                        .append("이 내용의 텍스트만 전달해줘")
                        .append("우선순위 기준은 다음과 같아: ")
                        .append("1. 기간이 5년 이하여야 해. ")
                        .append("2. 이자율이 높은 상품이 우선순위가 높아야 해. ")
                        .append("아래는 상품 리스트야: ");
                break;
            case "wish":
                promptMessage.append("아래의 상품 리스트 중 소원을 위한 하나은행 적금 최소 7개, 최대 10개를 추천순위로 제공해줘. ")
                        .append("답변으로는 추천 상품의 product_id만 콤마를 기준으로 공백없이 나열해서 응답해주면 돼. ")
                        .append("이 내용의 텍스트만 전달해줘")
                        .append("우선순위 기준은 다음과 같아: ")
                        .append("1. 기간이 1인 상품이 우선순위가 높아야 해. ")
                        .append("2. 만약 기간이 같다면 이자율이 높은 상품이 우선순위가 높아야 해. ")
                        .append("아래는 상품 리스트야: ");
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }

        for (Product product : products) {
            promptMessage.append("ProductId: ").append(product.getProductId())
                    .append(", ProductName: ").append(product.getProductNm())
                    .append(", ProductTermYear: ").append(product.getTermYear())
                    .append(", ProductInterestRate: ").append(product.getInterestRate()).append("; ");
        }

        GeminiPrompt geminiPrompt = new GeminiPrompt(promptMessage.toString());

        GeminiCallResponse aiResponse = aiService.callGenerativeLanguageApi(geminiPrompt);
        // json 파싱
        // 응답
        String aiResponseMessage = aiResponse.message();
        String[] productIdStringList = aiResponseMessage.split(",");
        List<Long> productIdList = Arrays.stream(productIdStringList).map(string -> Long.valueOf(string.trim())).toList();

        List<ProductGetResponse> productGetResponseList = new ArrayList<>();
        for (Long productId : productIdList) {
            Product product = products.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
            if (product != null) {
                productGetResponseList.add(ProductGetResponse.fromProduct(product));
            }
        }
        return productGetResponseList;
    }
}
