package challenge.core.services.main;


import com.hyundai.challenge.model.PostVehicleModelRetrieveResponse;
import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetrieveVehicleModelService {
    Mono<PostVehicleModelRetrieveResponse> postVehicleModelRetrieve(String model, String cryptocurrency);

    Flux<VehicleVersion> retrieveVehicleWithCryptocurrency(String model, String cryptocurrency);

    Mono<VehicleVersion> retrieveVehicleByVersion(String cryptocurrency, String model, String version);

    Mono<VehicleVersion> retrieveVehicleInMemory(String conversionId, String version);
}
