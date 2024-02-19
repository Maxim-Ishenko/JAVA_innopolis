package ru.innopolis.model.Car;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PerformanceCar extends Car {
    private String[] addOns = {};
}
