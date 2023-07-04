package com.hyundai.challenge.aplication.port.in.report;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchaseUseCase {
    Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, String model, String cryptocurrency);
}
