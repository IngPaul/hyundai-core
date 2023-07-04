package com.hyundai.challenge.aplication.port.out.report;


import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchasePort {
    Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, ModelVehicleEnum model);
}
