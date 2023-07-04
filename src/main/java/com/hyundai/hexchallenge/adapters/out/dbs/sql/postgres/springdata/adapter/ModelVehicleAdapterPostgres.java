package com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.adapter;

import com.hyundai.hexchallenge.adapters.mapper.ModelVehicleDomainMapper;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.repositories.PurchaseRepository;
import com.hyundai.hexchallenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.hexchallenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;
import com.hyundai.hexchallenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.hexchallenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
@AllArgsConstructor
public class ModelVehicleAdapterPostgres implements GetReportVehiclePurchasePort, VehiclePurchaseSavePort {
    private final PurchaseRepository purchaseRepository;

    @Override
    public Mono<ModelVehicleDomain> purchase(ModelVehicleDomain request) {
        return purchaseRepository.save(ModelVehicleDomainMapper.INSTANCE.domainToEntity(request))
                .map(ModelVehicleDomainMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Flux<ModelVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, ModelVehicleEnum model) {
        return purchaseRepository.findByDateAndModel(date, model.getName())
                .map(ModelVehicleDomainMapper.INSTANCE::entityToDomain);
    }
}
