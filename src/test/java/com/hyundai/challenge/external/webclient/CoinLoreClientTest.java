package com.hyundai.challenge.external.webclient;
import com.hyundai.challenge.adapters.out.webclients.restClient.webclient.CoinLoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class CoinLoreClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CoinLoreClient coinLoreClient;

    @Test
    public void testGetPrice() {
        StepVerifier.create(coinLoreClient.getPrice(80L))
                .expectNextCount(1)
                .verifyComplete();
    }
}
