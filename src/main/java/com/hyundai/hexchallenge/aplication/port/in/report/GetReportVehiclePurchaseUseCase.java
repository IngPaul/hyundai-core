package com.hyundai.hexchallenge.aplication.port.in.report;

import com.hyundai.challenge.model.PostPurchaseReportResponse;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchaseUseCase {
    Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, String model, String cryptocurrency);
}
