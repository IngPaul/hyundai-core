package com.hyundai.challenge.core.services.main.impl;

import com.hyundai.challenge.configuration.handler.error.CoreError;
import com.hyundai.challenge.core.mapper.PurchaseMapper;
import com.hyundai.challenge.core.mapper.VehicleMapper;
import com.hyundai.challenge.core.repositories.PurchaseRepository;
import com.hyundai.challenge.core.services.main.PurchaseService;
import com.hyundai.challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import com.hyundai.challenge.core.entities.VehiclePurchase;
import com.hyundai.challenge.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final RetrieveVehicleModelService retrieveVehicleModelService;
    private final PurchaseRepository purchaseRepository;
    @Override
    public Mono<PostPurchaseVehicleModelResponse> purchase(PostPurchaseVehicleModelRequest request) {
        return retrieveVehicleModelService.retrieveVehicleInMemory(request.getData().getConvertionId(),
                        request.getData().getVersion())
                .switchIfEmpty(retrieveVehicleModelService
                        .retrieveVehicleByVersion(request.getData().getCryptocurrency(),
                                request.getData().getModel(),
                                request.getData().getVersion()))
                .switchIfEmpty(Mono.error(CoreError.ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND))
                .map(vehicle -> PurchaseMapper.INSTANCE.toPurchaseEntity(vehicle, request))
                .flatMap(purchaseRepository::save)
                .map(VehicleMapper.INSTANCE::toPostPurchaseVehicleModel)
                .map(data -> new PostPurchaseVehicleModelResponse().data(data));
    }



}
