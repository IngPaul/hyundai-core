package com.hyundai.hexchallenge.aplication.port.out.report;

import com.hyundai.challenge.core.entities.VehiclePurchase;
import com.hyundai.challenge.model.PostPurchaseReportResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@FunctionalInterface
public interface GetVehiclePurchaseUseCase {
    Flux<VehiclePurchase> findByDateAndModel(LocalDate date, String model);
}
