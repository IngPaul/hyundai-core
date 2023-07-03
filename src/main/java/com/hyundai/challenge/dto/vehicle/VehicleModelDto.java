package com.hyundai.challenge.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModelDto {
    @JsonProperty("VER_CODIGO")
    private int code;

    @JsonProperty("VER_NOMBRE")
    private String name;

    @JsonProperty("VEA_ANIO")
    private int year;

    @JsonProperty("VEA_PRECIO_PVP")
    private double priceUsd;

    @JsonProperty("VEA_BONO")
    private double bond;

    @JsonProperty("VEA_PRECIO_FINAL")
    private double finalPrice;

    @JsonProperty("VEA_DISCAPACIDAD_100")
    private int disability;

    @JsonProperty("VER_COD_SGC")
    private String securityCode;
}
