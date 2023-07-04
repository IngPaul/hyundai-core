package com.hyundai.challenge.adapters.out.webclients.restClient.webclient;

import com.hyundai.challenge.adapters.out.webclients.restClient.webclient.ModelVehicleClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

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