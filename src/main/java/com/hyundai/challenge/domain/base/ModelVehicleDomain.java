package com.hyundai.challenge.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public abstract class ModelVehicleDomain extends GenericDomain {
    private String version;
    private String model;
    private String cryptocurrency;
    private BigDecimal priceUsd;
    private BigDecimal priceCryptocurrency;
    public boolean validateDomainDataVehicle(){
        return (this.priceUsd!=null);
    }

}
