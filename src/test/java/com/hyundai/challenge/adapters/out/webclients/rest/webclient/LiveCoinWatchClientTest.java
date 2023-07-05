package com.hyundai.challenge.adapters.out.webclients.rest.webclient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
class LiveCoinWatchClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private LiveCoinWatchClient liveCoinWatchClient;

    @Test
    void testGetPrice() {
        StepVerifier.create(liveCoinWatchClient.getPrice("BTC", "USD"))
                .expectNextCount(1)
                .verifyComplete();
    }
}
