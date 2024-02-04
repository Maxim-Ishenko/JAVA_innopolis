package ru.innopolis.repository;

import ru.innopolis.utils.FileWorkHelper;
import ru.innopolis.model.Car.Car;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static ru.innopolis.utils.CarDataHandlerParserHelper.setCarsCollectionFromTheFile;

public class CarRepositoryImpl implements CarRepository {
    @Override
    public void create(Car car) {
        FileWorkHelper.setWriteTheResultsToTheFile(car.toString(), OUTPUT_FILE_PATH);
    }
    public void create(Car car, String path) {
        FileWorkHelper.setWriteTheResultsToTheFile(car.toString(), path);
    }

    @Override
    public List<Car> findAll() {
        try {
            return setCarsCollectionFromTheFile(OUTPUT_FILE_PATH);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
    public List<Car> findAll(String path) {
        try {
            return setCarsCollectionFromTheFile(path);
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    @Override
    public void deleteAll() throws IOException {
        try {
            new FileOutputStream(OUTPUT_FILE_PATH).close();
            System.out.println("Очистка файла прошла успешно");
        } catch(IOException error) {
            throw new IOException(error.getMessage());
        }
    }
    public void deleteAll(String path) throws IOException {
        try {
            new FileOutputStream(path).close();
            System.out.println("Очистка файла прошла успешно");
        } catch(IOException error) {
            throw new IOException(error.getMessage());
        }
    }
}
