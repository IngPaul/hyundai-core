package com.hyundai.challenge.core.services.main.impl;

import com.hyundai.challenge.configuration.handler.error.CoreError;
import com.hyundai.challenge.core.enums.VehicleModelEnum;
import com.hyundai.challenge.core.mapper.VehicleMapper;
import com.hyundai.challenge.core.services.common.CacheVehicleVersionService;
import com.hyundai.challenge.core.services.common.ConversionService;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.challenge.external.webclient.ModelVehicleClient;
import com.hyundai.challenge.model.DataResponse;
import com.hyundai.challenge.model.PostVehicleModelRetrieveResponse;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrieveVehicleModelServiceImpl implements RetrieveVehicleModelService {
    private final ModelVehicleClient modelVehicleClient;
    private final ConversionService conversionService;
    private final CacheVehicleVersionService cacheService;
    @Override
    public Mono<PostVehicleModelRetrieveResponse> postVehicleModelRetrieve(String model, String cryptocurrency){
        String uuid=UUID.randomUUID().toString();
       return retrieveVehicleWithCryptocurrency(model, cryptocurrency)
                .collectList()
                .flatMap(vehicleVersions -> cacheService.save(vehicleVersions,uuid, Duration.ofSeconds(20L)))
                .map(vehiclesToResponse->new PostVehicleModelRetrieveResponse().data(new DataResponse().versions(vehiclesToResponse)))
                .map(response-> {
                    response.setData(response.getData().convertionId(uuid).conversionTimelife("20 seconds"));
                    return response;
                })
               .doOnError(e->log.error("Error at the momento retrieve model vehicles"));
    }
    @Override
    public Flux<VehicleVersion> retrieveVehicleWithCryptocurrency(String model, String cryptocurrency){
        return modelVehicleClient.getModelVehicle(VehicleModelEnum.fromName(model).getId())
                .map(vehicleModel-> VehicleMapper.INSTANCE.toVehicleVersion(vehicleModel, model, cryptocurrency))
                .flatMap(vehicle->addPriceInCryptoCurrency(cryptocurrency, vehicle))
                .doOnError(e->log.error("Error at the moment to find models vehicles and find the coversion of price in external services"));
    }
    private Mono<VehicleVersion> addPriceInCryptoCurrency(String cryptocurrency, VehicleVersion vehicleToResponse) {
        return conversionService.conversionToCryptoCurrency(cryptocurrency, vehicleToResponse.getPriceUsd())
                .map(priceInCryptoCurrency-> {
                    vehicleToResponse.setPriceCryptocurrency(priceInCryptoCurrency);
                    return vehicleToResponse;
                });
    }
    @Override
    public Mono<VehicleVersion> retrieveVehicleByVersion(String cryptocurrency, String model, String version) {
        return retrieveVehicleWithCryptocurrency(model, cryptocurrency)
                .filter(vehicles->vehicles.getVersion().equals(version))
                .next()
                .switchIfEmpty(Mono.error(CoreError.VERSION_NOT_FOUND));
    }
    @Override
    public Mono<VehicleVersion> retrieveVehicleInMemory(String conversionId, String version) {
        return  cacheService.findByConversionId(conversionId)
                .filter(vehicles->vehicles.getVersion().equals(version))
                .next();
    }

}
