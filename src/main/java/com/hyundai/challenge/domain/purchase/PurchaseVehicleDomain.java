package com.hyundai.challenge.domain.purchase;

import com.hyundai.challenge.domain.base.GenericDomain;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseVehicleDomain extends GenericDomain {
    private String conversionId;
    private String fullName;
    private String version;
    private LocalDate date;
    private ModelVehicleDomain modelVehicleDomain;
}
