package com.hyundai.challenge.domain.catalog;

import com.hyundai.challenge.domain.base.GenericDomain;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CatalogVehicleDomain extends GenericDomain {
    private String convertionId;
    private Long conversionTimelifeValue;
    private String conversionTimelife;
    private List<ModelVehicleDomain> versions;
    public CatalogVehicleDomain init(){
        this.conversionTimelifeValue=20L;
        this.conversionTimelife=this.conversionTimelifeValue+" segundos";
        this.convertionId= UUID.randomUUID().toString();
        return this;
    }
}
