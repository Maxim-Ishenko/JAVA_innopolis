package ru.innopolis.utils;

import ru.innopolis.model.Car.Car;

import java.util.*;

import static ru.innopolis.repository.CarRepository.*;

public class CarDataHandlerParserHelper {
    public static String getCarBrandName(String brand) throws IllegalArgumentException {
        try {
            if (Objects.equals(brand, "")) {
                throw new IllegalArgumentException("Марка автомобиля не может быть пустой строкой!");
            }

            return brand;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static String getCarBrandName(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String brand = infoStringCollection.get(BRAND_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Objects.equals(brand, "")) {
                throw new IllegalArgumentException("Марка автомобиля не может быть пустой строкой!");
            }

            return brand;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static String getCarModelName(String model) throws IllegalArgumentException {
        try {
            if (Objects.equals(model, "")) {
                throw new IllegalArgumentException("Модель автомобиля не может быть пустой строкой!");
            }

            return model;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static String getCarModelName(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String model = infoStringCollection.get(MODEL_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Objects.equals(model, "")) {
                throw new IllegalArgumentException("Модель автомобиля не может быть пустой строкой!");
            }

            return model;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static Integer getCarYear(Integer year) throws IllegalArgumentException {
        try {
            if (year <= 0) {
                throw new IllegalArgumentException("Год выпуска может быть только положительным числом!");
            }

            return year;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static Integer getCarYear(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String year = infoStringCollection.get(YEAR_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Integer.parseInt(year) <= 0) {
                throw new IllegalArgumentException("Год выпуска может быть только положительным числом!");
            }

            return Integer.parseInt(year);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static Integer getCarPowerValue(Integer power) throws IllegalArgumentException {
        try {
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность может быть только положительным числом!");
            }

            return power;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static Integer getCarPowerValue(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String power = infoStringCollection.get(POWER_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Integer.parseInt(power) <= 0) {
                throw new IllegalArgumentException("Мощность может быть только положительным числом!");
            }

            return Integer.parseInt(power);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getPerformanceCarPowerValue(Integer power) throws IllegalArgumentException {
        try {
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность может быть только положительным числом!");
            }

            return (int) (power + power * 0.5);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static Integer getCarDurability(Integer durability) throws IllegalArgumentException {
        try {
            if (durability < 0) {
                throw new IllegalArgumentException("Долговечность не может быть отрицательным числом!");
            }

            return durability;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static Integer getCarDurability(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String durability = infoStringCollection.get(DURABILITY_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Integer.parseInt(durability) < 0) {
                throw new IllegalArgumentException("Долговечность не может быть отрицательным числом!");
            }

            return Integer.parseInt(durability);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static Integer getCarSuspension(Integer suspension) throws IllegalArgumentException {
        try {
            if (suspension <= 0) {
                throw new IllegalArgumentException("Значение подвески может быть только положительным числом!");
            }

            return suspension;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static Integer getCarSuspension(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String suspension = infoStringCollection.get(SUSPENSION_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Integer.parseInt(suspension) <= 0) {
                throw new IllegalArgumentException("Значение подвески может быть только положительным числом!");
            }

            return Integer.parseInt(suspension);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getPerformanceCarSuspension(Integer suspension) throws IllegalArgumentException {
        try {
            if (suspension <= 0) {
                throw new IllegalArgumentException("Значение подвески может быть только положительным числом!");
            }

            return (int) (suspension - suspension * 0.25);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static Integer getCarAcceleration(Integer acceleration) throws IllegalArgumentException {
        try {
            if (acceleration <= 0) {
                throw new IllegalArgumentException("Ускорение может быть только положительным числом!");
            }

            return acceleration;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public static Integer getCarAcceleration(List<String> infoStringCollection) throws IllegalArgumentException {
        try {
            String acceleration = infoStringCollection.get(ACCELERATION_INDEX).trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Integer.parseInt(acceleration) <= 0) {
                throw new IllegalArgumentException("Ускорение может быть только положительным числом!");
            }

            return Integer.parseInt(acceleration);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getShowCarStarsAmount(Integer stars) throws IllegalArgumentException {
        try {
            if (stars < 0) {
                throw new IllegalArgumentException("Значение популярности не может быть отрицательным!");
            }

            return stars;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Car createNewCar(
            String brand,
            String model,
            Integer year,
            Integer power,
            Integer acceleration,
            Integer suspension,
            Integer durability
    ) {
        try {
            return new Car(
                    getCarBrandName(brand),
                    getCarModelName(model),
                    getCarYear(year),
                    getCarPowerValue(power),
                    getCarAcceleration(acceleration),
                    getCarSuspension(suspension),
                    getCarDurability(durability)
            );
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public Car createNewCar() {
        try {
            String inputData = FileWorkHelper.getParamsFromTheFile(INPUT_FILE_PATH);
            return new Car(
                    getCarBrandName(FileWorkHelper.getFieldValueFromTheString(inputData, BRAND_PARAM_NAME)),
                    getCarModelName(FileWorkHelper.getFieldValueFromTheString(inputData, MODEL_PARAM_NAME)),
                    getCarYear(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(inputData, YEAR_PARAM_NAME))),
                    getCarPowerValue(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(inputData, POWER_PARAM_NAME))),
                    getCarAcceleration(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(inputData, ACCELERATION_PARAM_NAME))),
                    getCarSuspension(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(inputData, SUSPENSION_PARAM_NAME))),
                    getCarDurability(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(inputData, DURABILITY_PARAM_NAME)))
            );
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public Car createNewCar(String paramsString) {
        try {
            return new Car(
                    getCarBrandName(FileWorkHelper.getFieldValueFromTheString(paramsString, BRAND_PARAM_NAME)),
                    getCarModelName(FileWorkHelper.getFieldValueFromTheString(paramsString, MODEL_PARAM_NAME)),
                    getCarYear(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(paramsString, YEAR_PARAM_NAME))),
                    getCarPowerValue(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(paramsString, POWER_PARAM_NAME))),
                    getCarAcceleration(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(paramsString, ACCELERATION_PARAM_NAME))),
                    getCarSuspension(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(paramsString, SUSPENSION_PARAM_NAME))),
                    getCarDurability(Integer.parseInt(FileWorkHelper.getFieldValueFromTheString(paramsString, DURABILITY_PARAM_NAME)))
            );
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static String readCarsFromTheFile(String path) {
        return FileWorkHelper.getParamsFromTheFile(path);
    }

    public static ArrayList<Car> setCarsCollectionFromTheFile(String path) {
        String dataString = readCarsFromTheFile(path);
        ArrayList<Car> carsCollection = new ArrayList<>();
        List<String> dataStringCollection = new ArrayList<>(Arrays.asList(dataString.split("\n")))
                .stream().filter(item -> !item.isEmpty()).toList();

        for(String currentCarString: dataStringCollection) {
            List<String> specificCarInputParams = new ArrayList<>(List.of(currentCarString.split(",")));

            Car specificCarEntity = new Car(
                    getCarBrandName(specificCarInputParams),
                    getCarModelName(specificCarInputParams),
                    getCarYear(specificCarInputParams),
                    getCarPowerValue(specificCarInputParams),
                    getCarAcceleration(specificCarInputParams),
                    getCarSuspension(specificCarInputParams),
                    getCarDurability(specificCarInputParams)
            );

            Collections.addAll(carsCollection, specificCarEntity);
        }

        return carsCollection;
    }
}
