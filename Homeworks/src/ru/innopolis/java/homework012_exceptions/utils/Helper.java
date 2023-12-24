package ru.innopolis.java.homework012_exceptions.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    public Helper() {}

    public void setWriteThePersonToTheFile(String personsInfoString, String outputFilePath) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            bufferWriter.append("\n");
            bufferWriter.append(personsInfoString);
        } catch(IOException e) {
            System.out.println("Во время записи в файл возникла проблема: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
