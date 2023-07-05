package com.hyundai.challenge.aplication.services;

import com.hyundai.challenge.aplication.port.in.report.GetReportVehiclePurchaseUseCase;
import com.hyundai.challenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements GetReportVehiclePurchaseUseCase {
    private final GetReportVehiclePurchasePort getReportVehiclePurchasePort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;



    @Override
    public Mono<ReportPurchaseVehicleDomain> retrieveByDateAndModelAndCrypto(LocalDate date, String model, String cryptocurrency) {
        CryptoCurrencyEnum cryptocurrencyEnum=CryptoCurrencyEnum.fromName(cryptocurrency);
        ModelVehicleEnum modelVehicleEnum=ModelVehicleEnum.fromName(model);
        return getReportVehiclePurchasePort.retrieveByDateAndModelAndCrypto(date, modelVehicleEnum)
                .flatMapIterable(dataPurchaseVehicle -> dataPurchaseVehicle.getModelVehicleDomainList())
                .flatMap(purchase -> {
                    if (purchase.getCryptocurrency() != null && purchase.getCryptocurrency().equals(cryptocurrency))
                        return Mono.just(purchase);
                    else
                        return addPriceCriptocurrencyPort.add(cryptocurrencyEnum, purchase);
                })
                .collectList()
                .map(listDomainModelVehicle -> {
                    ReportPurchaseVehicleDomain reportPurchaseVehicleDomain = new ReportPurchaseVehicleDomain();
                    reportPurchaseVehicleDomain.setModelVehicleDomainList(listDomainModelVehicle);
                    return reportPurchaseVehicleDomain;
                });
    }
}
