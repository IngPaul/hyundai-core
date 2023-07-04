package com.hyundai.hexchallenge.aplication.port.out.report;


import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportVehiclePurchasePort {
    Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, ModelVehicleEnum model);
}
