package com.hyundai.challenge.adapters.out.dbs.memory.redis.adapter;

import com.hyundai.challenge.adapters.common.mapper.VehicleVersionMapper;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.adapters.common.mapper.ModelVehicleDomainMapper;
import com.hyundai.challenge.adapters.out.dbs.memory.redis.repository.CacheVehicleVersionRepository;
import com.hyundai.challenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.challenge.aplication.port.out.a_common.memory.SaveListModelVehiclesInMemoryPort;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ModelVehicleAdapterRedis implements RetrieveVersionVehicleInMemoryPort, SaveListModelVehiclesInMemoryPort {
    private final CacheVehicleVersionRepository vehicleVersionRepository;
    @Override
    public Mono<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, ModelVehicleEnum model, String version) {
        return vehicleVersionRepository.findByConversionId(conversionId)
                .filter(data-> data.getVersion().equals(version))
                .next()
                .map(data-> ModelVehicleDomainMapper.INSTANCE.vehicleVersionToDomain(data,model.getName()));
    }


    @Override
    public Mono<List<ModelVehicleDomain>> save(List<ModelVehicleDomain> list, String conversionId, Duration expiration) {
        List<VehicleVersion> listToSave = list.stream().map(domain -> VehicleVersionMapper.INSTANCE.toVehicleVersion(domain, domain.getModel()))
                .toList();
        return vehicleVersionRepository.save(listToSave, conversionId, expiration)
                .thenReturn(list);
    }


}
