package ru.innopolis.repository;

import ru.innopolis.model.Car.Car;

import java.io.IOException;
import java.util.List;

public interface CarRepository {
    String PARAMS_SEPARATOR = ",";
    String PARAMS_NAME_VALUE_SEPARATOR = ":";
    Integer FIRST_ELEMENT_OF_ARR_INDEX = 0;
    Integer SECOND_ELEMENT_OF_ARR_INDEX = 1;
    String INPUT_FILE_PATH = "Homeworks/src/ru/innopolis/java/homework017_maven_hw09/src/main/resources/input.txt";
    String OUTPUT_FILE_PATH = "Homeworks/src/ru/innopolis/java/homework017_maven_hw09/src/main/resources/output.txt";
    Integer BRAND_INDEX = 0;
    Integer MODEL_INDEX = 1;
    Integer YEAR_INDEX = 2;
    Integer POWER_INDEX = 3;
    Integer ACCELERATION_INDEX = 4;
    Integer SUSPENSION_INDEX = 5;
    Integer DURABILITY_INDEX = 6;
    String BRAND_PARAM_NAME = "brand";
    String MODEL_PARAM_NAME = "model";
    String YEAR_PARAM_NAME = "year";
    String POWER_PARAM_NAME = "power";
    String ACCELERATION_PARAM_NAME = "acceleration";
    String SUSPENSION_PARAM_NAME = "suspension";
    String DURABILITY_PARAM_NAME = "durability";

    void create(Car car);
    List<Car> findAll();
    void deleteAll() throws IOException;
}
