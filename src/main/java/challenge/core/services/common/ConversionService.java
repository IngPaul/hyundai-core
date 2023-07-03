package challenge.core.services.common;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ConversionService {
    Mono<BigDecimal> conversionToCryptoCurrency(String cryptocurrency, BigDecimal value);
}
