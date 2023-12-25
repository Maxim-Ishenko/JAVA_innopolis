package ru.innopolis.java.homework011Addition.test;

import ru.innopolis.java.homework011Addition.model.Car;
import ru.innopolis.java.homework011Addition.model.Colors;
import ru.innopolis.java.homework011Addition.repository.CarsRepositoryImpl;

import java.util.*;

public class Main {
    static final String DATA_FILE_PATH = "src/ru/innopolis/java/homework011Addition/data/cars.txt";
    static final String OUTOUT_FILE_PATH = "src/ru/innopolis/java/homework011Addition/data/output.txt";

    public static void main(String[] args) throws Exception {
        CarsRepositoryImpl carMethodsStore = new CarsRepositoryImpl();
        String inputData = carMethodsStore.readCarsDataStringFromTheFile(DATA_FILE_PATH);
        ArrayList<Car> carCollection = carMethodsStore.setCarsCollectionFromTheString(inputData);

        System.out.println("Входящая коллекция: " + '\n' + carCollection + '\n');

        // 1) Номера   всех   автомобилей,   имеющих   заданный цвет colorToFind или пробег mileageToFind.
        Colors colorToFind = Colors.WHITE;
        Integer mileageToFind = 0;
        carCollection
                .stream().filter(car -> car.getColor() == colorToFind || Objects.equals(car.getMileage(), mileageToFind))
                .forEach(item ->
                        carMethodsStore.writeTheResultsToTheFile(
                                "Номера автомобилей по цвету или пробегу: " + item,
                                OUTOUT_FILE_PATH
                        ));

        // 2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
        int n = 760;
        int m = 900;
        long uniqueItemsFromAreaAmount = carCollection
                .stream().filter(car -> car.getCoast() > n * 1000 && car.getCoast() < m * 1000)
                .distinct().count();
        carMethodsStore.writeTheResultsToTheFile(
                "Уникальные автомобили: " + uniqueItemsFromAreaAmount + " шт.",
                OUTOUT_FILE_PATH
        );

        // 3) Вывести цвет автомобиля с минимальной стоимостью.
        Colors minCoastAutoColor = carCollection
                .stream().min(Comparator.comparing(Car::getCoast)).get().getColor();
        carMethodsStore.writeTheResultsToTheFile(
                "Цвет авто с минимальной стоимостью: " + minCoastAutoColor,
                OUTOUT_FILE_PATH
        );

        // 4) Вывести cреднюю стоимость искомой модели modelToFind/
        String modelToFind = "Toyota";
        double averageCoastOfTheSpecificModel = carCollection
                .stream().filter(car -> Objects.equals(car.getModel(), modelToFind))
                .mapToDouble(Car::getCoast).average().getAsDouble();
        carMethodsStore.writeTheResultsToTheFile(
                "Средняя стоимость модели " + modelToFind + " : " + averageCoastOfTheSpecificModel,
                OUTOUT_FILE_PATH
        );
    }
}
