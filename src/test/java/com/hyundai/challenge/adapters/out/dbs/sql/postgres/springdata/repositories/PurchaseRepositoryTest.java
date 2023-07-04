package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.repositories;

import Util.MockData;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.time.LocalDate;
@ExtendWith(MockitoExtension.class)
class PurchaseRepositoryTest {
        @Autowired
        PurchaseRepository purchaseRepository;
        @BeforeEach
        void setUp() {
        }

        @Test
        void test() {
            VehiclePurchase vehicleModel= MockData.getVehiclePurchase();
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