package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.adapter;

import com.hyundai.challenge.adapters.common.mapper.ModelVehicleDomainMapper;
import com.hyundai.challenge.adapters.common.mapper.PurchaseVehicleMapper;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.repositories.PurchaseRepository;
import com.hyundai.challenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.challenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
@RequiredArgsConstructor
@Component
public class ModelVehicleAdapterPostgres implements GetReportVehiclePurchasePort, VehiclePurchaseSavePort {
    private final PurchaseRepository purchaseRepository;


    @Override
    public Mono<ReportPurchaseVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, ModelVehicleEnum model) {
        return purchaseRepository.findByDateAndModel(date, model.getName())
                .map(ModelVehicleDomainMapper.INSTANCE::entityToDomain)
                .collectList()
                .map(list -> {
                    ReportPurchaseVehicleDomain reportPurchaseVehicleDomain = new ReportPurchaseVehicleDomain();
                    reportPurchaseVehicleDomain.setModelVehicleDomainList(list);
                    return reportPurchaseVehicleDomain;
                });
    }

    @Override
    public Mono<PurchaseVehicleDomain> purchase(PurchaseVehicleDomain purchase) {
        return purchaseRepository.save(PurchaseVehicleMapper.INSTANCE.toVehiclePurchase(purchase))
                .map(p->{
                   purchase.getModelVehicleDomain().setId(p.getId());
                   return purchase;
                });
    }
}
