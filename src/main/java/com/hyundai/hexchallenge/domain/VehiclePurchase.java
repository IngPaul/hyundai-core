package com.hyundai.hexchallenge.domain;

import com.hyundai.challenge.core.entities.base.GenericEntity;
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
public class VehiclePurchase extends GenericEntity {
    private String fullName;
    private String version;
    private String model;
    private String cryptocurrency;
    private BigDecimal priceUsd;
    private BigDecimal priceCryptocurrency;
    private LocalDate date;
    public boolean validateDataVehicle(){
        if(this.priceUsd!=null)
            return true;
        return false;
    }
}
