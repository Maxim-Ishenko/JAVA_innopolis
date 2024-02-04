package ru.innopolis.model.Car;

import lombok.*;

import static ru.innopolis.repository.CarRepository.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {
    private String brand;
    private String model;
    private Integer year;
    private Integer power;
    private Integer acceleration;
    private Integer suspension;
    private Integer durability;

    @Override
    public String toString() {
        return BRAND_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + brand + PARAMS_SEPARATOR +
                MODEL_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + model + PARAMS_SEPARATOR +
                YEAR_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + year + PARAMS_SEPARATOR +
                POWER_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + power + PARAMS_SEPARATOR +
                ACCELERATION_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + acceleration + PARAMS_SEPARATOR +
                SUSPENSION_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + suspension + PARAMS_SEPARATOR +
                DURABILITY_PARAM_NAME + PARAMS_NAME_VALUE_SEPARATOR + durability;
    }
}

