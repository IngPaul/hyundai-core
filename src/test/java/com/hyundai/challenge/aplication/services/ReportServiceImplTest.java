package com.hyundai.challenge.aplication.services;

import Util.MockData;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.report.GetReportVehiclePurchasePort;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock private GetReportVehiclePurchasePort getReportVehiclePurchasePort;
    @Mock private AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    @InjectMocks private ReportServiceImpl reportService;



    @Test
    void retrieveByDateAndModelAndCryptoPriceNotExist() {
        ReportPurchaseVehicleDomain data = MockData.getReportPurchaseVehicleDomain();
        data.getModelVehicleDomainList().get(0).setPriceCryptocurrency(null);
        Mockito.when(getReportVehiclePurchasePort.retrieveByDateAndModelAndCrypto(Mockito.any(), Mockito.any()))
                        .thenReturn(Mono.just(data));



        StepVerifier.create(reportService.retrieveByDateAndModelAndCrypto(LocalDate.now(),"TUCSON","BTC"))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(getReportVehiclePurchasePort, Mockito.times(1)).retrieveByDateAndModelAndCrypto(Mockito.any(), Mockito.any());
    }
    @Test
    void retrieveByDateAndModelAndCryptoPriceExist() {
        ReportPurchaseVehicleDomain data = MockData.getReportPurchaseVehicleDomain();
        Mockito.when(getReportVehiclePurchasePort.retrieveByDateAndModelAndCrypto(Mockito.any(), Mockito.any()))
                .thenReturn(Mono.just(data));

        StepVerifier.create(reportService.retrieveByDateAndModelAndCrypto(LocalDate.now(),"TUCSON","BTC"))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(getReportVehiclePurchasePort, Mockito.times(1)).retrieveByDateAndModelAndCrypto(Mockito.any(), Mockito.any());

    }
}