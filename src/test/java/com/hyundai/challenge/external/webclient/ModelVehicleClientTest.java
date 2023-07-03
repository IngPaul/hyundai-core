package com.hyundai.challenge.external.webclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureWebTestClient
class ModelVehicleClientTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ModelVehicleClient modelVehicleClient;
    @Test
    void getModelVehicle() {
        StepVerifier.create(modelVehicleClient.getModelVehicle(1036L).collectList())
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}