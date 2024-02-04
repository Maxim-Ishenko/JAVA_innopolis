package ru.innopolis.model.Car;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShowCar extends Car {
    private Integer stars = 0;
}
