package com.hyundai.hexchallenge.adapters.in.api.rest;

import com.hyundai.challenge.controllers.VehicleModelsApi;
import com.hyundai.challenge.model.*;
import com.hyundai.challenge.model.*;
import com.hyundai.challenge.core.services.main.PurchaseService;
import com.hyundai.challenge.core.services.main.ReportService;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.hexchallenge.aplication.port.in.catalog.RetrieveVersionVehicleUseCase;
import com.hyundai.hexchallenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.hexchallenge.aplication.port.in.report.GetReportVehiclePurchaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class VehicleModelsApiController implements VehicleModelsApi {
    private final VehiclePurchaseSaveUseCase vehiclePurchaseSaveUseCase;
    private final RetrieveVersionVehicleUseCase retrieveVersionVehicleUseCase;
    private final GetReportVehiclePurchaseUseCase getReportVehiclePurchaseUseCase;

    @Override
    public Mono<ResponseEntity<PostPurchaseReportResponse>> postPurchaseReport(Mono<PostPurchaseReportRequest> postPurchaseReportRequest, ServerWebExchange exchange) {
        return postPurchaseReportRequest.flatMap(request->vehiclePurchaseSaveUseCase.postPurchaseReport(request.getData().getDate(), request.getData().getModel(), request.getData().getCryptocurrency()))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostPurchaseVehicleModelResponse>> postPurchaseVehicleModel(Mono<PostPurchaseVehicleModelRequest> postPurchaseVehicleModelRequest, ServerWebExchange exchange) {
        return  postPurchaseVehicleModelRequest.flatMap(purchaseService::purchase)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostVehicleModelRetrieveResponse>> postVehicleRetrieveModel(Mono<PostVehicleModelRetrieveRequest> postVehicleModelRetrieveRequest, ServerWebExchange exchange) {
       return postVehicleModelRetrieveRequest.flatMap(request -> retrieveVehicleModelService.postVehicleModelRetrieve(request.getData().getModel(), request.getData().getCryptoCurrency()))
                .map(ResponseEntity::ok);
    }
}
