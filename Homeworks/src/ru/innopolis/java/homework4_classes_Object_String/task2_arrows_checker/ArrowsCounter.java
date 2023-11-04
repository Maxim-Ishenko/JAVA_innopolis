package ru.innopolis.java.homework4_classes_Object_String.task2_arrows_checker;

import java.util.Scanner;

public class ArrowsCounter {
    public static final String LEFT_ARROW_SUBSTRING = "<--<<";
    public static final String RIGHT_ARROW_SUBSTRING = ">>-->";
    private static final int STRING_MAX_LENGTH = 106;

    public static String getInputString(Scanner input) {
        System.out.println("Введите входную последовательность символов '>', '<', '-' без пробелов: ");

        String inputString = input.nextLine();

        return inputString.length() > STRING_MAX_LENGTH
                ? inputString.substring(0, STRING_MAX_LENGTH + 1).toLowerCase().trim()
                : inputString.toLowerCase().trim();
    }

    public static int getArrowsAmount(String inputString, String targetSubstring) {
        int arrowsAmount = 0;
        int index = 0;

        while ((index = inputString.indexOf(targetSubstring, index)) != -1) {
            arrowsAmount++;
            index += targetSubstring.length();
        }

        return arrowsAmount;
    }
}
