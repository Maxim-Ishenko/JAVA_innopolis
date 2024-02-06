package ru.innopolis.utils.Helpers;

import java.io.*;

public class FileWorkHelper {
    public static String readUsersDataStringFromTheFile(String path) {
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
    public static String readUsersDataStringFromTheFile(InputStream inputStream) {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream))) {
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

    public static void writeTheUserToTheFile(String usersInfoString, String outputFilePath, Boolean shouldAppendFlag) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(outputFilePath, shouldAppendFlag))) {
            bufferWriter.append("\n");
            bufferWriter.append(usersInfoString);
        } catch(IOException e) {
            System.out.println("Во время записи в файл возникла проблема: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
