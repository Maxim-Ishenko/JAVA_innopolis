package ru.innopolis.java.homework011Addition.repository;

import ru.innopolis.java.homework011Addition.model.Colors;
import ru.innopolis.java.homework011Addition.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarsRepositoryImpl implements CarsRepository {
    static final Integer NUMBER_INDEX = 0;
    static final Integer MODEL_INDEX = 1;
    static final Integer COLOR_INDEX = 2;
    static final Integer MILEAGE_INDEX = 3;
    static final Integer COAST_INDEX = 4;

    public CarsRepositoryImpl() {}

    public String readCarsDataStringFromTheFile(String path) {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(path))) {
            String textLine;

            while((textLine = bufferReader.readLine()) != null) {
                resultString.append(textLine);
                resultString.append("\n");
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return resultString.toString();
    }

    @Override
    public ArrayList<Car> setCarsCollectionFromTheString(String dataString) throws Exception {
        ArrayList<Car> carCollection = new ArrayList<>();

        for(String specificCarString: dataString.split("\n")) {
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

        return carCollection;
    }

    @Override
    public void writeTheResultsToTheFile(String resultString, String fileName) {
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferWriter.append("\n");
            bufferWriter.append(resultString);

            bufferWriter.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
