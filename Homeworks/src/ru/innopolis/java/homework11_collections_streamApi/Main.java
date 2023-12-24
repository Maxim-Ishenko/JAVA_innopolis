package ru.innopolis.java.homework11_collections_streamApi;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    static final Integer NUMBER_INDEX = 0;
    static final Integer MODEL_INDEX = 1;
    static final Integer COLOR_INDEX = 2;
    static final Integer MILEAGE_INDEX = 3;
    static final Integer COAST_INDEX = 4;
    static final int THOUSANDS_ORDER = 1000;

    public static void main(String[] args) throws Exception {
        String inputData = """
                a123me|Mercedes|White|0|8300000
                b873of|Volga|Black|0|673000
                w487mn|Lexus|Grey|76000|900000
                p987hj|Volga|Red|610|704340
                c987ss|Toyota|White|254000|761000
                o983op|Toyota|Black|698000|740000
                p146op|BMW|White|271000|850000
                u893ii|Toyota|Purple|210900|440000
                l097df|Toyota|Black|108000|780000
                y876wd|Toyota|Black|160000|1000000""";
        ArrayList<Car> carCollection = new ArrayList<>();

        for(String specificCarString: inputData.split("\n")) {
            List<String> specificCarInputParams = new ArrayList<>(List.of(specificCarString.split("\\|")));

            Car specificCarEntity = new Car(
                    specificCarInputParams.get(NUMBER_INDEX),
                    specificCarInputParams.get(MODEL_INDEX),
                    Colors.valueOf(specificCarInputParams.get(COLOR_INDEX).toUpperCase()),
                    Integer.valueOf(specificCarInputParams.get(MILEAGE_INDEX)),
                    Double.valueOf(specificCarInputParams.get(COAST_INDEX))
            );

            Collections.addAll(carCollection, specificCarEntity);
        }

        System.out.println("Входящая коллекция: " + '\n' + carCollection + '\n');

        // 1) Номера   всех   автомобилей,   имеющих   заданный цвет colorToFind или пробег mileageToFind.
        Colors colorToFind = Colors.WHITE;
        Integer mileageToFind = 0;
        carCollection
                .stream().filter(car -> car.getColor() == colorToFind || Objects.equals(car.getMileage(), mileageToFind))
                .forEach(item -> System.out.println("Номера автомобилей по цвету или пробегу: " + item));

        // 2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
        int n = 760;
        int m = 900;
        long uniqueItemsFromAreaAmount = carCollection
                .stream().filter(car -> car.getCoast() > n * THOUSANDS_ORDER && car.getCoast() < m * THOUSANDS_ORDER)
                .distinct().count();
        System.out.println("Уникальные автомобили: " + uniqueItemsFromAreaAmount + " шт.");

        // 3) Вывести цвет автомобиля с минимальной стоимостью.
        Colors minCoastAutoColor = carCollection
                .stream().min(Comparator.comparing(Car::getCoast)).get().getColor();
        System.out.println("Цвет авто с минимальной ценой согласно условию 3): " + minCoastAutoColor);

        // 4) Вывести cреднюю стоимость искомой модели modelToFind/
        String modelToFind = "Toyota";
        double averageCoastOfTheSpecificModel = carCollection
                .stream().filter(car -> Objects.equals(car.getModel(), modelToFind))
                .mapToDouble(Car::getCoast).average().getAsDouble();
        System.out.println("Средняя стоимость модели " + modelToFind + " : " + averageCoastOfTheSpecificModel);
    }
}
