package challenge.core.util;

import challenge.configuration.handler.error.CoreError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.challenge.model.VehicleVersion;

import java.util.List;

public class VehicleVersionParse {
    private VehicleVersionParse(){}

    public static String toJsonString(List<VehicleVersion> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw CoreError.ERROR_IN_SERIALIZE_VEHICLE;
        }
    }

    public static List<VehicleVersion> toListVehicle(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<List<VehicleVersion>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw CoreError.ERROR_IN_DESERIALIZE_VEHICLE;
        }
    }
}
