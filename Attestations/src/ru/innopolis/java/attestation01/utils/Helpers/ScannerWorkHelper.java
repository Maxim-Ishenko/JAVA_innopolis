package ru.innopolis.java.attestation01.utils.Helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerWorkHelper {
    public static String getTheUsersDataStringFromConsole(String helpMessage) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (helpMessage != null && !helpMessage.isEmpty()) {
                System.out.println(helpMessage);
            }

            return scanner.nextLine();
        } catch(InputMismatchException error) {
            System.out.println("Во время ввода данных произошла ошибка: " + error.getMessage());
            error.printStackTrace(System.out);
        }

        return null;
    }
}
