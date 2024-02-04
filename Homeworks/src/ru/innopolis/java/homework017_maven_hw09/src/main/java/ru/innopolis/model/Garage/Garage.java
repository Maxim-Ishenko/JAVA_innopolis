package ru.innopolis.model.Garage;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.innopolis.model.Car.Car;

@Data
@AllArgsConstructor
public class Garage {
    private Car[] parkedCars = {};
}
