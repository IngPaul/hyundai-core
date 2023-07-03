package challenge.controllers;

import challenge.core.services.main.PurchaseService;
import challenge.core.services.main.ReportService;
import challenge.core.services.main.RetrieveVehicleModelService;
import com.hyundai.challenge.controllers.VehicleModelsApi;
import com.hyundai.challenge.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class VehicleModelsApiController implements VehicleModelsApi {
    private final RetrieveVehicleModelService retrieveVehicleModelService;
    private final PurchaseService purchaseService;
    private final ReportService reportService;

    @Override
    public Mono<ResponseEntity<PostPurchaseReportResponse>> postPurchaseReport(Mono<PostPurchaseReportRequest> postPurchaseReportRequest, ServerWebExchange exchange) {
        return postPurchaseReportRequest.flatMap(request->reportService.postPurchaseReport(request.getData().getDate(), request.getData().getModel(), request.getData().getCryptocurrency()))
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
