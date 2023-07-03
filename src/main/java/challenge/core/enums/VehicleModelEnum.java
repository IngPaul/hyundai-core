package challenge.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hyundai.challenge.configuration.handler.error.CoreError.MODEL_NOT_FOUND;

@AllArgsConstructor
@Getter
public enum VehicleModelEnum {
    ALL_NEW_ACCENT("ACCENT", 1036L),
    ALL_NEW_TUCSON("TUCSON", 1031L),
    SANTA_FE("SANTA FE", 1023L),
    GRAND_I10("GRAND i10", 1038L);

    private final String name;
    private final Long id;

    public static VehicleModelEnum fromName(String name) {
        for (VehicleModelEnum model : VehicleModelEnum.values()) {
            if (model.name.equalsIgnoreCase(name)) {
                return model;
            }
        }
        throw  MODEL_NOT_FOUND;
    }
}
