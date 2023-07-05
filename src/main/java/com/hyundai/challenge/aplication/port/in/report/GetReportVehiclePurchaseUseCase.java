package com.hyundai.challenge.aplication.port.in.report;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchaseUseCase {
    Mono<ReportPurchaseVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, String model, String cryptocurrency);
}
