package com.hyundai.challenge.adapters.in.api.rest;

import com.hyundai.challenge.adapters.common.mapper.*;
import com.hyundai.challenge.adapters.common.mapper.PostPurchaseVehicleModelMapper;
import com.hyundai.challenge.controllers.VehicleModelsApi;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.model.*;
import com.hyundai.challenge.aplication.port.in.catalog.RetrieveModelVehiclesUseCase;
import com.hyundai.challenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.challenge.aplication.port.in.report.GetReportVehiclePurchaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModelVehicleAdapterRest implements VehicleModelsApi {
    private final VehiclePurchaseSaveUseCase vehiclePurchaseSaveUseCase;
    private final RetrieveModelVehiclesUseCase retrieveModelVehiclesUseCase;
    private final GetReportVehiclePurchaseUseCase getReportVehiclePurchaseUseCase;

    @Override
    public Mono<ResponseEntity<PostPurchaseReportResponse>> postPurchaseReport(Mono<PostPurchaseReportRequest> postPurchaseReportRequest, ServerWebExchange exchange) {
        return postPurchaseReportRequest
                .map(PostPurchaseReportRequest::getData)
                .flatMap(request-> getReportVehiclePurchaseUseCase.retrieveByDateAndModelAndCrypto(request.getDate(),
                                                                                                    request.getModel(),
                                                                                                    request.getCryptocurrency())
                        .flatMapIterable(data-> data.getModelVehicleDomainList())
                                .map(PostPurchaseReportMapper.INSTANCE::toPostPurchaseReportResponseData)
                                .collectList())
                .map(data->new PostPurchaseReportResponse().data(data))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostPurchaseVehicleModelResponse>> postPurchaseVehicleModel(Mono<PostPurchaseVehicleModelRequest> postPurchaseVehicleModelRequest, ServerWebExchange exchange) {
        return postPurchaseVehicleModelRequest
                .map(PostPurchaseVehicleModelRequest::getData)
                .flatMap(request->{
                    PurchaseVehicleDomain modelVehicleDomain = PostPurchaseVehicleModelMapper.INSTANCE.toPurchaseVehicleDomain(request);
                    return vehiclePurchaseSaveUseCase.purchase(modelVehicleDomain, request.getConvertionId(), request.getVersion());
                })
                .map(dataSave->{
                    PostPurchaseVehicleModelResponse response = PostPurchaseVehicleModelMapper.INSTANCE.toPostPurchaseVehicleModelResponse(dataSave);
                    response.getData().setDate(dataSave.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    return response;
                })
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostVehicleModelRetrieveResponse>> postVehicleRetrieveModel(Mono<PostVehicleModelRetrieveRequest> postVehicleModelRetrieveRequest, ServerWebExchange exchange) {
        return postVehicleModelRetrieveRequest
                .flatMap(request ->
                        retrieveModelVehiclesUseCase
                                .retrieveByModelAndCrypto(request.getData().getModel(), request.getData().getCryptoCurrency()))
                .map(this::buildDataResponse)
                .map(dataResponse -> new PostVehicleModelRetrieveResponse().data(dataResponse))
                .map(ResponseEntity::ok);
    }

    private DataResponse buildDataResponse(CatalogVehicleDomain data) {
            DataResponse dataResponse= new DataResponse();
            dataResponse.setConversionTimelife(data.getConversionTimelife());
            dataResponse.setConvertionId(data.getConvertionId());
            List<VehicleVersion> versions = data.getVersions().stream().map(vehicle -> {
                VehicleVersion vehicleResponse = new VehicleVersion();
                vehicleResponse.setCryptocurrency(vehicle.getCryptocurrency());
                vehicleResponse.setPriceUsd(vehicle.getPriceUsd());
                vehicleResponse.setPriceCryptocurrency(vehicle.getPriceCryptocurrency());
                vehicleResponse.setModel(vehicle.getModel());
                vehicleResponse.setVersion(vehicle.getVersion());
                return vehicleResponse;
            }).toList();
            dataResponse.setVersions(versions);
            return dataResponse;
    }


}
