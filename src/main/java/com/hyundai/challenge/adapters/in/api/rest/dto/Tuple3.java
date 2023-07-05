package com.hyundai.challenge.adapters.in.api.rest.dto;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public final class Tuple3 {
    private List<ModelVehicleDomain> list;
    private String conversionTimelife;
    private String conversionId;
}
