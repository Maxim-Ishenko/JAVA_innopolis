package ru.innopolis.repository;

import org.junit.jupiter.api.*;
import ru.innopolis.model.Car.Car;
import ru.innopolis.utils.FileWorkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ru.innopolis.utils.CarDataHandlerParserHelper.setCarsCollectionFromTheFile;

class CarRepositoryImplTest {
    private final String TEST_INPUT_FILE_PATH = "src/test/resources/input.txt";
    private final String TEST_OUTPUT_FILE_PATH = "src/test/resources/output.txt";
    private final Car TEST_CAR = new Car(
            "Lada",
            "Niva",
            1987,
            72,
            1,
            2,
            3
    );
    private final String TEST_CAR_STRING = "brand:Opel,model:Astra,year:1987,power:72,acceleration:1,suspension:2,durability:3";
    private final String TEST_CAR_STRING_WITH_WRONG_NAME =
            "brand:*,model:Astra,year:1987,power:72,acceleration:1,suspension:2,durability:3";
    private final String TEST_CAR_STRING_WITH_WRONG_acceleration =
            "brand:*,model:Astra,year:1987,power:72,acceleration:-,suspension:2,durability:3";
    private final String TEST_CAR_BRAND = "Lada";
    private final String TEST_CAR_MODEL = "Niva";

    private final CarRepositoryImpl CARS_METHODS_STORE = new CarRepositoryImpl();

    @Test
    @Tag("cleanItUp")
    @DisplayName("Тест создания и записи новой машины в выходной файл")
    void create() {
        CARS_METHODS_STORE.create(TEST_CAR, TEST_OUTPUT_FILE_PATH);
        ArrayList<Car> carsCollection = setCarsCollectionFromTheFile(TEST_OUTPUT_FILE_PATH);
        Car targetCar = carsCollection.stream().filter(car ->
                        Objects.equals(TEST_CAR_BRAND, car.getBrand()) && Objects.equals(TEST_CAR_MODEL, car.getModel()))
                .findAny()
                .orElse(null);

        assertNotNull(targetCar);
        assertEquals(targetCar, TEST_CAR);
    }

    @Test
    @DisplayName("Тест получения всех автомобилей из файла")
    void findAll() {
        FileWorkHelper.setWriteTheResultsToTheFile(
                TEST_CAR_STRING,
                TEST_OUTPUT_FILE_PATH
        );
        List<Car> carsList = CARS_METHODS_STORE.findAll(TEST_OUTPUT_FILE_PATH);

        assertFalse(carsList.isEmpty());
    }

    @Test
    @DisplayName("Тест очистки файла")
    void deleteAll() throws IOException {
        FileWorkHelper.setWriteTheResultsToTheFile(
                TEST_CAR_STRING,
                TEST_OUTPUT_FILE_PATH
        );
        CARS_METHODS_STORE.deleteAll(TEST_OUTPUT_FILE_PATH);
        List<Car> carsList = CARS_METHODS_STORE.findAll(TEST_OUTPUT_FILE_PATH);

        assertTrue(carsList.isEmpty());
    }
}