package com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient;
import com.hyundai.challenge.dto.price.coinlore.PriceLoreDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Data
@Slf4j
public class CoinLoreClient {
    @Value("${lore.baseurl}")
    private String baseUrl;
    public Mono<PriceLoreDto> getPrice(Long idCryptoCurrency){
        log.info("Starting to retrieve price of criptocurrency");
        return buildClient()
                .get()
                .uri("?id={id}", idCryptoCurrency)
                .retrieve()
                .bodyToFlux(PriceLoreDto.class)
                .next()
                .doOnSuccess(r->log.info("Retrieve price: {}", r.toString()))
                .doOnError(e->log.error("Error in retrieve price"))
                .retry(3);
    }
    private WebClient buildClient(){
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
