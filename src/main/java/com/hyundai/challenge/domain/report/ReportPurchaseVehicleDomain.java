package com.hyundai.challenge.domain.report;

import com.hyundai.challenge.domain.base.GenericDomain;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPurchaseVehicleDomain extends GenericDomain {
    private List<ModelVehicleDomain> modelVehicleDomainList;

}
