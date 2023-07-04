package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.adapter;

import Util.MockData;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.repositories.PurchaseRepository;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ModelVehicleAdapterPostgresTest {
    @Mock private PurchaseRepository purchaseRepository;
    @InjectMocks ModelVehicleAdapterPostgres modelVehicleAdapterPostgres;
    @Test
    void purchase() {
        Mockito.when(purchaseRepository.save(Mockito.any()))
                .thenReturn(Mono.just(MockData.getVehiclePurchase()));

        StepVerifier.create(modelVehicleAdapterPostgres.purchase(Mockito.any()))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(purchaseRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void retrieveByDateAndModelAndCrypto() {
        Mockito.when(purchaseRepository.findByDateAndModel(Mockito.any(), Mockito.anyString()))
                        .thenReturn(Flux.just(MockData.getVehiclePurchase()));
        StepVerifier.create(modelVehicleAdapterPostgres.retrieveByDateAndModelAndCrypto(LocalDate.now(), ModelVehicleEnum.ALL_NEW_TUCSON))
                .expectNextCount(1)
                .verifyComplete();
    }
}