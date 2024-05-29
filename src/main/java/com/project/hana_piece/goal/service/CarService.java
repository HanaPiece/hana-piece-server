package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.Car;
import com.project.hana_piece.goal.dto.CarGetResponse;
import com.project.hana_piece.goal.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarGetResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarGetResponse::fromEntity)
                .toList();
    }
}
