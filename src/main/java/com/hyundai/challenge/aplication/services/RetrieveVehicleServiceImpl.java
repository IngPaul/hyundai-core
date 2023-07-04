package com.hyundai.challenge.aplication.services;

import com.hyundai.challenge.aplication.port.out.a_common.memory.SaveListModelVehiclesInMemoryPort;
import com.hyundai.challenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.aplication.port.in.catalog.RetrieveModelVehiclesUseCase;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RetrieveVehicleServiceImpl implements RetrieveModelVehiclesUseCase {
    private final RetrieveModelVehiclesPort retrieveModelVehiclesPort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    private final SaveListModelVehiclesInMemoryPort saveListModelVehiclesInMemoryPort;
    

    @Override
    public Flux<ModelVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency) {
        UUID uuid = UUID.randomUUID();
        ModelVehicleEnum modelVehicleEnum= com.hyundai.challenge.domain.enums.ModelVehicleEnum.fromName(model);
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(cryptocurrency);
        return retrieveModelVehiclesPort.retrieveByModelAndCrypto(modelVehicleEnum, cryptoCurrencyEnum)
                .flatMap(vehicle->addPriceCriptocurrencyPort.add(cryptoCurrencyEnum,vehicle))
                .collectList()
                .flatMap(list->saveListModelVehiclesInMemoryPort.save(list, uuid.toString(), Duration.ofSeconds(20)))
                .flatMapIterable(list->completeInformation(list, uuid));
    }

    private List<ModelVehicleDomain> completeInformation(List<ModelVehicleDomain> list, UUID uuid) {
        return list.stream().map(vehicle-> {
            vehicle.setId(uuid);
            vehicle.setMsg("20 segundos");
            return vehicle;
        }).collect(Collectors.toList());
    }
}
