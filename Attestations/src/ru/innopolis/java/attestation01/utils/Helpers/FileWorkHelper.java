package ru.innopolis.java.attestation01.utils.Helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWorkHelper {
    public static void setWriteTheUserToTheFile(String userssInfoString, String outputFilePath) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            bufferWriter.append("\n");
            bufferWriter.append(userssInfoString);
        } catch(IOException e) {
            System.out.println("Во время записи в файл возникла проблема: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
