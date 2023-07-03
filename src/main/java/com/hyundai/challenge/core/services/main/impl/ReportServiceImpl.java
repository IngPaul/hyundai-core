package com.hyundai.challenge.core.services.main.impl;

import com.hyundai.challenge.core.mapper.PurchaseMapper;
import com.hyundai.challenge.core.repositories.PurchaseRepository;
import com.hyundai.challenge.core.services.main.ReportService;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.challenge.model.PostPurchaseReportResponse;
import com.hyundai.challenge.model.PostPurchaseReportResponseData;
import com.hyundai.challenge.core.entities.VehiclePurchase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final PurchaseRepository purchaseRepository;
    private final RetrieveVehicleModelService retrieveVehicleModelService;
    @Override
    public Mono<PostPurchaseReportResponse> postPurchaseReport(LocalDate date, String model, String cryptocurrency) {
         return purchaseRepository.findByDateAndModel(date, model)
                .flatMap(purchase -> {
                    if (purchase.getCryptocurrency() != null && purchase.getCryptocurrency().equals(cryptocurrency))
                        return Mono.just(purchase);
                    else
                        return addPriceInPurchase(purchase, cryptocurrency);
                })
                .map(PurchaseMapper.INSTANCE::toPostPurchaseReportResponseData)
                .collectList()
                .map(this::buildResponse);
    }

    private Publisher<VehiclePurchase> addPriceInPurchase(VehiclePurchase purchase, String cryptocurrency) {
        return retrieveVehicleModelService.retrieveVehicleByVersion(cryptocurrency, purchase.getModel(), purchase.getVersion())
                .map(vehicle -> {
                    purchase.setCryptocurrency(cryptocurrency);
                    purchase.setPriceCryptocurrency(vehicle.getPriceCryptocurrency());
                    return purchase;
                });
    }
    private PostPurchaseReportResponse buildResponse(List<PostPurchaseReportResponseData> data){
        PostPurchaseReportResponse response = new PostPurchaseReportResponse();
        response.setData(data);
        return response;
    }


}
