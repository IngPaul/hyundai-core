package com.hyundai.challenge.core.repositories;

import Util.MockData;
import com.hyundai.challenge.core.entities.VehiclePurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class PurchaseRepositoryTest {
    @Autowired
    PurchaseRepository purchaseRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void test() {
        VehiclePurchase vehicleModel=MockData.getVehiclePurchase();
        vehicleModel.setModel("W_TEST");
        vehicleModel.setId(null);
        vehicleModel.setDate(LocalDate.now());
        StepVerifier.create(purchaseRepository.save(vehicleModel))
                .expectNextCount(1)
                .verifyComplete();
        StepVerifier.create(purchaseRepository.findByDateAndModel(LocalDate.now(),"W_TEST").collectList())
                .expectNextCount(1)
                .verifyComplete();
    }
}