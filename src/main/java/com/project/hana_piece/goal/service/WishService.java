package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.Wish;
import com.project.hana_piece.goal.dto.WishGetResponse;
import com.project.hana_piece.goal.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public List<WishGetResponse> getAllWishes() {
        List<Wish> wishes = wishRepository.findAll();
        return wishes.stream()
                .map(WishGetResponse::fromEntity)
                .toList();
    }
}