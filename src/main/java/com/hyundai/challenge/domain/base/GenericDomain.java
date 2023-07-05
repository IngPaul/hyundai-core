package com.hyundai.challenge.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericDomain implements Cloneable{
    private UUID id;

    @Override
    public GenericDomain clone() throws CloneNotSupportedException {
        return (GenericDomain)super.clone();
    }
}
