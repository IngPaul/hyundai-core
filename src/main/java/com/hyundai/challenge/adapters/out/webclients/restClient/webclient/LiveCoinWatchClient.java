package com.hyundai.challenge.adapters.out.webclients.restClient.webclient;
import com.hyundai.challenge.adapters.common.dto.price.livecoin.PriceLiveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LiveCoinWatchClient {
    @Value("${live.baseurl}")
    private String baseUrl;

    public Mono<PriceLiveDto> getPrice(String cryptoCurrency, String fiatCurrency){
        log.info("Starting to retrieve price of criptocurrency");
        return buildClient(cryptoCurrency, fiatCurrency)
                .get()
                .retrieve()
                .bodyToMono(PriceLiveDto.class)
                .doOnNext(retrieve->log.info("Price retrieve: {}", retrieve.toString()))
                .doOnError(e->log.error("Error in retrieve price"))
                .retry(3);
    }
    private WebClient buildClient(String cryptoCurrency, String fiatCurrency){
        //https://http-api.livecoinwatch.com/coins/ETH/about?currency=USD
        String url = baseUrl + "/coins/" + cryptoCurrency.toUpperCase() + "/about?currency="+fiatCurrency.toUpperCase();
        return WebClient.builder().baseUrl(url).build();
    }
}
