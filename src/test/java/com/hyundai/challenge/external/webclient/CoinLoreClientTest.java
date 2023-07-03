package com.hyundai.challenge.external.webclient;
import com.hyundai.challenge.dto.price.coinlore.PriceLoreDto;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
/*
@SpringBootTest
public class CoinLoreClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CoinLoreClient coinLoreClient;
    @Before
    void setUp(){
        coinLoreClient.setBaseUrl("https://api.coinlore.net/api/ticker/");
    }

    @Test
    public void testGetPrice() {
        // Mock the response from the external API
        PriceLoreDto expectedPrice = new PriceLoreDto();
        // Set the properties of the expectedPrice object

        when(coinLoreClient.getPrice(anyLong())).thenReturn(Mono.just(expectedPrice));

        // Send a GET request to the API endpoint
        webTestClient.get().uri("id=80")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceLoreDto.class)
                .isEqualTo(expectedPrice);
    }
}*/
