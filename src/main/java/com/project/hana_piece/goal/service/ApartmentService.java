package com.project.hana_piece.goal.service;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.goal.domain.Apartment;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.dto.ApartmentGetResponse;
import com.project.hana_piece.goal.dto.ApartmentPricePredictRequest;
import com.project.hana_piece.goal.dto.ApartmentPricePredictResponse;
import com.project.hana_piece.goal.repository.ApartmentRepository;
import com.project.hana_piece.product.domain.EnrolledProduct;
import com.project.hana_piece.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final AiService aiService;

    public List<ApartmentGetResponse> getAllApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return apartments.stream()
                .map(ApartmentGetResponse::fromEntity)
                .toList();
    }

    public ApartmentPricePredictResponse predictApartmentPrice(ApartmentPricePredictRequest request) {
        GeminiPrompt geminiPrompt = buildPromptMessage(request);

        GeminiCallResponse response = aiService.callGenerativeLanguageApi(geminiPrompt);

        long messageLong = Long.parseLong(response.message());
        return new ApartmentPricePredictResponse(messageLong);
    }

    private GeminiPrompt buildPromptMessage(ApartmentPricePredictRequest dto) {
        StringBuilder requests = new StringBuilder();
        requests.append("아래 정보를 바탕으로 미래의 아파트 가격을 예측해줘\n")
            .append("최근 거래가: " + dto.price() + "원\n")
            .append("지역: " + dto.area() + "\n")
            .append("아파트 이름: " + dto.apartmentNm() + "\n")
            .append("평수: " + dto.area() + "평\n")
            .append("예측 대상 미래: " + dto.duration() + "개월 후"+"\n");

        StringBuilder constraints = new StringBuilder();
        constraints.append("선형 회귀 모델을 이용해 오차범위 50퍼센트 가격 이내에서 중간 시나리오로 최대한 정확히 예측해줘\n")
            .append("짧은 기간에는 비용에 큰 차이가 없도록 해줘\n")
            .append("대한민국의 경제 상황과 국내 정세와 부동산 상황을 파악해서 합리적인 가격을 예측해줘\n");

        String responseFormat = "한국 화폐 단위인 원 단위로 콤마(,) 없이 가격(숫자)만 알려줘";

        return GeminiPrompt.builder()
            .requests(requests.toString())
            .constraints(constraints.toString())
            .responseFormat(responseFormat)
            .build();
    }
}
