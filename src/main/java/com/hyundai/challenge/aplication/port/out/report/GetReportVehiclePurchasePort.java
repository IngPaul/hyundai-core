package com.hyundai.challenge.aplication.port.out.report;


import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchasePort {
    Mono<ReportPurchaseVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, ModelVehicleEnum model);
}
