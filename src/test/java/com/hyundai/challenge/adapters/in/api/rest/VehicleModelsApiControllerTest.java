package com.hyundai.challenge.adapters.in.api.rest;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import static Util.MockData.*;
import static org.hamcrest.core.StringRegularExpression.matchesRegex;
@SpringBootTest
@AutoConfigureWebTestClient
 class VehicleModelsApiControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    String testRetrieve(){
        EntityExchangeResult<byte[]> retrieveResponse = webTestClient.post().uri("/vehicle-models/retrieve")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(RETRIEVE_REQUEST_STRING)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data").isNotEmpty()
                .jsonPath("$.data.convertionId").value(matchesRegex("[a-zA-Z0-9-]+"))
                .returnResult();

        return new String(retrieveResponse.getResponseBody()).replaceAll("\"", "");
    }
    void testPurchase(String convertionId){
        webTestClient.post().uri("/vehicle-models/purchase")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getPurchaseRequestString(convertionId))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data").isNotEmpty();
    }

    void testReport(){
        webTestClient.post().uri("/vehicle-models/purchase/report")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(REPORT_REQUEST_STRING)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
     void testAll() {
        testPurchase(testRetrieve());
        testReport();
    }
}