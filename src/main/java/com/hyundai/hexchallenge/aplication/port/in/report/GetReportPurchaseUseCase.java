package com.hyundai.hexchallenge.aplication.port.in.report;

import com.hyundai.challenge.model.PostPurchaseReportResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@FunctionalInterface
public interface GetReportPurchaseUseCase {
    Mono<PostPurchaseReportResponse> generateByDateAndModelAndCryptocurrecy(LocalDate date, String model, String cryptocurrency);
}
