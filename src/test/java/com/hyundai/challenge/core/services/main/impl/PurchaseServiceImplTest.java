package com.hyundai.challenge.core.services.main.impl;

import Util.MockData;
import com.hyundai.challenge.core.entities.VehiclePurchase;
import com.hyundai.challenge.core.repositories.PurchaseRepository;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.challenge.dto.vehicle.VehicleModelDto;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.VehicleVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {
    @Mock
    private RetrieveVehicleModelService retrieveVehicleModelService;
    @Mock private PurchaseRepository purchaseRepository;
    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeEach
    void setUp() {
    }

    void genericPurchase(){
        VehicleVersion vehicleVersion = MockData.getVehicleVersion();

        Mockito.when(retrieveVehicleModelService.retrieveVehicleInMemory(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Mono.just(vehicleVersion));

        Mockito.when(retrieveVehicleModelService.retrieveVehicleByVersion(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Mono.just(vehicleVersion));
        VehiclePurchase vehiclePurchase= MockData.getVehiclePurchase();
        Mockito.when(purchaseRepository.save(Mockito.any()))
                .thenReturn(Mono.just(vehiclePurchase));

        PostPurchaseVehicleModelRequest request= MockData.getPostPurchaseVehicleModelRequest();
        StepVerifier.create(purchaseService.purchase(request))
                .expectNextMatches(r->r.getData()!=null)
                .verifyComplete();
    }

    @Test
    void purchaseWithUseMemory() {
        genericPurchase();
        Mockito.verify(retrieveVehicleModelService, Mockito.times(1)).retrieveVehicleInMemory(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(purchaseRepository, Mockito.times(1)).save(Mockito.any());
    }
    @Test
    void purchaseWhenNotFoundMemory() {
        genericPurchase();
        Mockito.verify(retrieveVehicleModelService, Mockito.times(1)).retrieveVehicleInMemory(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(retrieveVehicleModelService, Mockito.times(1)).retrieveVehicleByVersion(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(purchaseRepository, Mockito.times(1)).save(Mockito.any());
    }
}