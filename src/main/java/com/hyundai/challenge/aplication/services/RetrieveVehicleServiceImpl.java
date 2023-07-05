package com.hyundai.challenge.aplication.services;

import com.hyundai.challenge.aplication.port.out.a_common.memory.SaveListModelVehiclesInMemoryPort;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.aplication.port.in.catalog.RetrieveModelVehiclesUseCase;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
@RequiredArgsConstructor
@Service
public class RetrieveVehicleServiceImpl implements RetrieveModelVehiclesUseCase {
    private final RetrieveModelVehiclesPort retrieveModelVehiclesPort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    private final SaveListModelVehiclesInMemoryPort saveListModelVehiclesInMemoryPort;
    

    @Override
    public Mono<CatalogVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency) {
        CatalogVehicleDomain catalogVehicleDomain= new CatalogVehicleDomain();
        catalogVehicleDomain.init();
        ModelVehicleEnum modelVehicleEnum= ModelVehicleEnum.fromName(model);
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(cryptocurrency);
        return retrieveModelVehiclesPort.retrieveByModelAndCrypto(modelVehicleEnum, cryptoCurrencyEnum)
                .flatMapIterable(catalog -> catalog.getVersions())
                .flatMap(vehicle -> addPriceCriptocurrencyPort.add(cryptoCurrencyEnum, vehicle))
                .collectList()
                .flatMap(list -> saveListModelVehiclesInMemoryPort.save(list, catalogVehicleDomain.getConvertionId(), Duration.ofSeconds(20)))
                .map(list->buildCatalog(list));
    }

    private CatalogVehicleDomain buildCatalog(List<ModelVehicleDomain> list) {
        CatalogVehicleDomain catalogVehicleDomain= new CatalogVehicleDomain();
        catalogVehicleDomain.setVersions(list);
        return catalogVehicleDomain;
    }
}
