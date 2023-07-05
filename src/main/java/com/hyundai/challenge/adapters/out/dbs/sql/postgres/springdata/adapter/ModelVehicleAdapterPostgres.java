package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.adapter;

import com.hyundai.challenge.adapters.common.mapper.ModelVehicleDomainMapper;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.repositories.PurchaseRepository;
import com.hyundai.challenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.challenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
@RequiredArgsConstructor
@Component
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
