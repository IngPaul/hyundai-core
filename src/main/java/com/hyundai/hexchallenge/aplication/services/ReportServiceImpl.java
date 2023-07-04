package com.hyundai.hexchallenge.aplication.services;

import com.hyundai.hexchallenge.aplication.port.in.report.GetReportVehiclePurchaseUseCase;
import com.hyundai.hexchallenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ReportServiceImpl implements GetReportVehiclePurchaseUseCase {
    private final GetReportVehiclePurchasePort getReportVehiclePurchasePort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;



    @Override
    public Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, String model, String cryptocurrency) {
        CryptoCurrencyEnum cryptocurrencyEnum=CryptoCurrencyEnum.fromName(cryptocurrency);
        ModelVehicleEnum modelVehicleEnum=ModelVehicleEnum.fromName(model);
        return getReportVehiclePurchasePort.retrieveByDateAndModelAndCrypto(date, modelVehicleEnum )
                .flatMap(purchase -> {
                    if (purchase.getCryptocurrency() != null && purchase.getCryptocurrency().equals(cryptocurrency))
                        return Mono.just(purchase);
                    else
                        return addPriceCriptocurrencyPort.add(cryptocurrencyEnum, purchase);
                });
    }
}
