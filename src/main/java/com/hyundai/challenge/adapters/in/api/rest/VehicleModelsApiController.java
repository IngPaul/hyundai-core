package com.hyundai.challenge.adapters.in.api.rest;

import com.hyundai.challenge.adapters.common.mapper.VehicleVersionMapper;
import com.hyundai.challenge.controllers.VehicleModelsApi;
import com.hyundai.challenge.model.*;
import com.hyundai.challenge.adapters.common.mapper.ModelVehicleDomainMapper;
import com.hyundai.challenge.adapters.common.mapper.PostPurchaseMapper;
import com.hyundai.challenge.adapters.common.mapper.PostPurchaseReportMapper;
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
                .flatMap(request->getReportVehiclePurchaseUseCase
                                        .retrieveByDateAndModelAndCrypto(request.getData().getDate(),
                                                                         request.getData().getModel(),
                                                                         request.getData().getCryptocurrency())
                                        .map(d->PostPurchaseReportMapper.INSTANCE.toPostPurchaseReportResponseData(d))
                                        .collectList())
                .map(data->new PostPurchaseReportResponse().data(data))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<PostPurchaseVehicleModelResponse>> postPurchaseVehicleModel(Mono<PostPurchaseVehicleModelRequest> postPurchaseVehicleModelRequest, ServerWebExchange exchange) {
        return postPurchaseVehicleModelRequest
                .flatMap(request -> {
                    PostPurchaseVehicleModelRequest xx = request;
                    ModelVehicleDomain modelVehicleDomain = new ModelVehicleDomain();
                    modelVehicleDomain.setModel(request.getData().getModel());
                    modelVehicleDomain.setCryptocurrency(request.getData().getCryptocurrency());
                    modelVehicleDomain.setVersion(request.getData().getVersion());
                    modelVehicleDomain.setFullName(request.getData().getFullName());
                    return vehiclePurchaseSaveUseCase.purchase(modelVehicleDomain, request.getData().getConvertionId());
                })
                .map(PostPurchaseMapper.INSTANCE::mapToPostPurchaseModel)
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
                                .map(list -> new Tuple4(list, list.get(0).getMsg(), list.get(0).getId(), request.getData().getModel()))
                )
                .map(data -> {
                    List<VehicleVersion> listData = data.list.stream()
                            .map(domain -> VehicleVersionMapper.INSTANCE.toVehicleVersion(domain, data.getModel()))
                            .collect(Collectors.toList());
                    return new DataResponse()
                            .versions(listData)
                            .conversionTimelife(data.list.get(0).getMsg())
                            .convertionId(data.list.get(0).getId().toString());
                })
                .map(dataResponse -> new PostVehicleModelRetrieveResponse().data(dataResponse))
                .map(ResponseEntity::ok);
    }
    @Data
    @AllArgsConstructor
    private final class Tuple4{
        private List<ModelVehicleDomain> list;
        private String conversionTimelife;
        private UUID id;
        private String model;
    }
}
