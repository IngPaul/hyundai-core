package challenge.external.webclient;
import challenge.dto.vehicle.VehicleModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class ModelVehicleClient {
    @Value("${vehicle.baseurl}")
    private String baseUrl;

    public Flux<VehicleModelDto> getModelVehicle(Long idVehicle){
        log.info("Starting to retrieve model vehicle");
        return buildClient(idVehicle)
                .get()
                .retrieve()
                .bodyToFlux(VehicleModelDto.class)
                .doOnNext(r->log.info("Model retrieve: {}", r.toString()))
                .doOnError(e->log.error("Error in retrieve model's vehicle", e))
                .retry(3);
    }
    private WebClient buildClient(Long idVehicle){
        return WebClient.builder().baseUrl(baseUrl+idVehicle).build();
    }
}
