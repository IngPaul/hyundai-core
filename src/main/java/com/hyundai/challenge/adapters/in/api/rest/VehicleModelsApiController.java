package com.hyundai.challenge.adapters.in.api.rest;

import com.hyundai.challenge.adapters.common.mapper.*;
import com.hyundai.challenge.controllers.VehicleModelsApi;
import com.hyundai.challenge.model.*;
import com.hyundai.challenge.aplication.port.in.catalog.RetrieveModelVehiclesUseCase;
import com.hyundai.challenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.challenge.aplication.port.in.report.GetReportVehiclePurchaseUseCase;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VehicleModelsApiController implements VehicleModelsApi {
    private final VehiclePurchaseSaveUseCase vehiclePurchaseSaveUseCase;
    private final RetrieveModelVehiclesUseCase retrieveModelVehiclesUseCase;
    private final GetReportVehiclePurchaseUseCase getReportVehiclePurchaseUseCase;

    @Override
    public Mono<ResponseEntity<PostPurchaseReportResponse>> postPurchaseReport(Mono<PostPurchaseReportRequest> postPurchaseReportRequest, ServerWebExchange exchange) {
        return postPurchaseReportRequest
                .map(request->request.getData())
                .flatMap(request-> getReportVehiclePurchaseUseCase.retrieveByDateAndModelAndCrypto(request.getDate(),
                                                                                                    request.getModel(),
                                                                                                    request.getCryptocurrency())
                                .map(PostPurchaseReportMapper.INSTANCE::toPostPurchaseReportResponseData)
                                .collectList())
                .map(data->new PostPurchaseReportResponse().data(data))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostPurchaseVehicleModelResponse>> postPurchaseVehicleModel(Mono<PostPurchaseVehicleModelRequest> postPurchaseVehicleModelRequest, ServerWebExchange exchange) {
        return postPurchaseVehicleModelRequest
                .map(request->request.getData())
                .map(PostPurchaseVehicleModelRequestDataMapper.INSTANCE::toModelVehicleDomain)
                .flatMap(modelVehicleDomain->vehiclePurchaseSaveUseCase.purchase(modelVehicleDomain, modelVehicleDomain.getConversionId()))
                .map(PostPurchaseVehicleModelMapper.INSTANCE::mapToPostPurchaseModel)
                .map(data -> new PostPurchaseVehicleModelResponse().data(data))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostVehicleModelRetrieveResponse>> postVehicleRetrieveModel(Mono<PostVehicleModelRetrieveRequest> postVehicleModelRetrieveRequest, ServerWebExchange exchange) {
        return postVehicleModelRetrieveRequest
                .flatMap(request ->
                        retrieveModelVehiclesUseCase
                                .retrieveByModelAndCrypto(request.getData().getModel(), request.getData().getCryptoCurrency())
                                .collectList()
                                .map(list -> new Tuple4(list, list.get(0).getMsg(), list.get(0).getId(), request.getData().getModel(),request.getData().getCryptoCurrency())))
                .map(data -> new DataResponse()
                            .versions(completeDataInList(data))
                            .conversionTimelife(data.list.get(0).getMsg())
                            .convertionId(data.list.get(0).getId().toString()))
                .map(dataResponse -> new PostVehicleModelRetrieveResponse().data(dataResponse))
                .map(ResponseEntity::ok);
    }

    private List<VehicleVersion> completeDataInList(Tuple4 data) {
        return data.list.stream()
                .map(domain -> VehicleVersionMapper.INSTANCE.toVehicleVersion(domain, data.getModel()))
                .map(vehicleVersionInBuild->{
                    vehicleVersionInBuild.setCryptocurrency(data.cryptoCurrency);
                    return vehicleVersionInBuild;
                })
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    private final class Tuple4{
        private List<ModelVehicleDomain> list;
        private String conversionTimelife;
        private UUID id;
        private String model;
        private String cryptoCurrency;
    }
}
