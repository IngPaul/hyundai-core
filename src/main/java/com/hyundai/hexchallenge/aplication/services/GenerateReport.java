package com.hyundai.hexchallenge.aplication.services;

import com.hyundai.challenge.core.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.model.PostVehicleModelRetrieveResponse;
import com.hyundai.hexchallenge.aplication.port.in.a_common.convertion.ConvertionVehicleModelUseCase;
import com.hyundai.hexchallenge.aplication.port.in.catalog.RetrieveVehicleModelsUseCase;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class GenerateReport implements ConvertionVehicleModelUseCase, RetrieveVehicleModelsUseCase {
    @Override
    public Mono<BigDecimal> getPrice(CryptoCurrencyEnum cryptocurrency) {
        return null;
    }

    @Override
    public Mono<PostVehicleModelRetrieveResponse> retrieve(String model, String cryptocurrency) {
        return null;
    }
}
