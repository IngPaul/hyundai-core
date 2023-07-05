package com.hyundai.challenge.domain;

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
public class ModelVehicleDomain extends GenericDomain{
    private String conversionId;
    private String fullName;
    private String version;
    private String model;
    private String msg;
    private String cryptocurrency;
    private BigDecimal priceUsd;
    private BigDecimal priceCryptocurrency;
    private LocalDate date;
    public boolean validateDomainDataVehicle(){
        return (this.priceUsd!=null);
    }
}
