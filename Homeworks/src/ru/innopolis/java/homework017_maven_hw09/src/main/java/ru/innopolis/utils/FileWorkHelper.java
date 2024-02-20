package ru.innopolis.utils;

import java.io.*;
import java.util.Objects;

import static ru.innopolis.repository.CarRepository.*;

public class FileWorkHelper {
    public static String getParamsFromTheFile(String path) {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(path))) {
            String textLine;

            while((textLine = bufferReader.readLine()) != null) {
                resultString.append(textLine);
                resultString.append("\n");
            }

        } catch(IOException e) {
            System.err.println(e.getMessage());
        }

        return resultString.toString();
    }

    public static String getFieldValueFromTheString (String fieldsStringFromTheFile, String targetParamName) {
        StringBuilder targetValue = new StringBuilder();

        for (String fieldEntity: fieldsStringFromTheFile.trim().split(PARAMS_SEPARATOR)) {
            String paramName = fieldEntity.trim().split(PARAMS_NAME_VALUE_SEPARATOR)[FIRST_ELEMENT_OF_ARR_INDEX];
            String paramVal = fieldEntity.trim().split(PARAMS_NAME_VALUE_SEPARATOR)[SECOND_ELEMENT_OF_ARR_INDEX];

            if (Objects.equals(paramName, targetParamName)) targetValue.append(paramVal);
        }

        return targetValue.toString().trim();
    }

    public static void setWriteTheResultsToTheFile(String resultString, String fileName) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferWriter.append("\n");
            bufferWriter.append(resultString);
        } catch(IOException e) {
            System.err.println("Во время записи в файл возникла проблема: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
