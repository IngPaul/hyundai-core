package com.hyundai.challenge.core.services.main.impl;

import Util.MockData;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.repositories.PurchaseRepository;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
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
class ReportServiceImplTest {
    @Mock private PurchaseRepository purchaseRepository;
    @Mock private RetrieveVehicleModelService retrieveVehicleModelService;
    @InjectMocks ReportServiceImpl reportService;

    void postPurchaseReport(VehiclePurchase vehicleModel){
        Mockito.when(purchaseRepository.findByDateAndModel(Mockito.any(),Mockito.anyString()))
                .thenReturn(Flux.just(vehicleModel));
        Mockito.when(retrieveVehicleModelService.retrieveVehicleByVersion(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Mono.just(MockData.getVehicleVersion()));
        StepVerifier.create(reportService.postPurchaseReport(LocalDate.now(),"TUCSON","BTC"))
                .expectNextMatches(r->r.getData()!=null)
                .verifyComplete();

    }
    @Test
    void postPurchaseReportWithPriceSaved() {
        VehiclePurchase vehicleModel= MockData.getVehiclePurchase();
        postPurchaseReport(vehicleModel);
        Mockito.verify(purchaseRepository, Mockito.times(1)).findByDateAndModel(Mockito.any(),Mockito.anyString());
    }
    @Test
    void postPurchaseReportWithoutPriceSaved() {
        VehiclePurchase vehicleModel= MockData.getVehiclePurchase();
        vehicleModel.setCryptocurrency(null);
        postPurchaseReport(vehicleModel);
        Mockito.verify(purchaseRepository, Mockito.times(1)).findByDateAndModel(Mockito.any(),Mockito.anyString());
        Mockito.verify(retrieveVehicleModelService, Mockito.times(1)).retrieveVehicleByVersion(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}