package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.repositories;

import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends ReactiveCrudRepository<VehiclePurchase, UUID> {
    Flux<VehiclePurchase> findByDateAndModel(LocalDate date, String model);
    Mono<VehiclePurchase> save(VehiclePurchase vehiclePurchase);
}