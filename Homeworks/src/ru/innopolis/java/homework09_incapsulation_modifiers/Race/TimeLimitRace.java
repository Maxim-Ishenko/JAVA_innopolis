package ru.innopolis.java.homework09_incapsulation_modifiers.Race;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.io.*;
import java.util.*;

public class TimeLimitRace extends Race {
    private final String PARAMS_SEPARATOR = ",";
    private final String PARAMS_NAME_VALUE_SEPARATOR = ":";
    private final Integer FIRST_ELEMENT_OF_ARR_INDEX = 0;
    private final Integer SECOND_ELEMENT_OF_ARR_INDEX = 1;
    private final String GOLD_TIME_PARAM_NAME = "goldTime";
    private final String INPUT_FILE_PATH = "src/ru/innopolis/java/homework09_incapsulation_modifiers/data/input.txt";

    private Integer goldTime;

    public Integer getGoldTime() {
        return goldTime;
    }

    public TimeLimitRace() {
        super();
    }
    public TimeLimitRace(
            Integer distance,
            String route,
            Integer prize,
            Car[] participants,
            Integer goldTime
    ) throws IllegalArgumentException {
        super(
                distance,
                route,
                prize,
                participants
        );

        this.setGoldTime(goldTime);
    }
    public TimeLimitRace(
            Integer distance,
            String route,
            Integer prize,
            Car[] participants
    ) throws IllegalArgumentException {
        super(
                distance,
                route,
                prize,
                participants
        );

        String paramsStringFromTheFile = this.setParamsFromTheFile();
        Integer goldTimeFromTheFile = Integer.valueOf(this.setFieldValueFromTheString(paramsStringFromTheFile));

        this.setGoldTime(goldTimeFromTheFile);
    }

    public void setGoldTime(Integer goldTime) throws IllegalArgumentException {
        if (goldTime <= 0) {
            throw new IllegalArgumentException("Значение времени должно быть положительным числом!");
        }

        this.goldTime = goldTime;
    }

    public String setParamsFromTheFile() {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(this.INPUT_FILE_PATH))) {
            String textLine;

            while((textLine = bufferReader.readLine()) != null) {
                resultString.append(textLine);
                resultString.append("\n");
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return resultString.toString().trim();
    }
    public String setFieldValueFromTheString (String fieldsStringFromTheFile) {
        StringBuilder targetValue = new StringBuilder();

        for (String fieldEntity: fieldsStringFromTheFile.trim().split(this.PARAMS_SEPARATOR)) {
            String paramName = fieldEntity.trim().split(this.PARAMS_NAME_VALUE_SEPARATOR)[this.FIRST_ELEMENT_OF_ARR_INDEX];
            String paramVal = fieldEntity.trim().split(this.PARAMS_NAME_VALUE_SEPARATOR)[this.SECOND_ELEMENT_OF_ARR_INDEX];

            if (Objects.equals(paramName, GOLD_TIME_PARAM_NAME)) targetValue.append(paramVal);
        }

        return targetValue.toString().trim();
    }
    public void setWriteTheResultsToTheFile(String resultString, String fileName) {
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferWriter.append("\n");
            bufferWriter.append(resultString);

            bufferWriter.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "TimeLimitRace{" +
            "distance=" + this.getDistance() +
            ", route='" + this.getRoute() + '\'' +
            ", prize=" + this.getPrize() +
            ", goldTime=" + this.getGoldTime() +
            ", participants=" + Arrays.toString(this.getParticipants()) +
            '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeLimitRace that = (TimeLimitRace) o;
        return Objects.equals(FIRST_ELEMENT_OF_ARR_INDEX, that.FIRST_ELEMENT_OF_ARR_INDEX)
                && Objects.equals(SECOND_ELEMENT_OF_ARR_INDEX, that.SECOND_ELEMENT_OF_ARR_INDEX)
                && Objects.equals(getGoldTime(), that.getGoldTime());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), PARAMS_SEPARATOR, PARAMS_NAME_VALUE_SEPARATOR, FIRST_ELEMENT_OF_ARR_INDEX, SECOND_ELEMENT_OF_ARR_INDEX, GOLD_TIME_PARAM_NAME, INPUT_FILE_PATH, getGoldTime());
    }
}
