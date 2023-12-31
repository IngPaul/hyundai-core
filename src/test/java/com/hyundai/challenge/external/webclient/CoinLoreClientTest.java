package com.hyundai.challenge.external.webclient;
import com.hyundai.challenge.dto.price.coinlore.PriceLoreDto;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

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
