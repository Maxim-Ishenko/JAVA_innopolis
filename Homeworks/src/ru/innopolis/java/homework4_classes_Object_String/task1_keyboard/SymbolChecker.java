package ru.innopolis.java.homework4_classes_Object_String.task1_keyboard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SymbolChecker {
    private static final String ENGLISH_KEYBOARD_SYMBOLS_STRING = "qwertyuiopasdfghjklzxcvbnm";
    private static final int FIRST_CHAR_INDEX = 0;
    private static final String LAST_CHAR = "m";

    public static String getInputSymbol() throws InputMismatchException {
        System.out.println("Введите символ английского алфавита: " );
        Scanner scanner = new Scanner(System.in);
        String inputEnglishSymbol = scanner.nextLine();

        if (!inputEnglishSymbol.matches("^[a-zA-Z]+$")) {
            throw new InputMismatchException(
                "Ожидается ввод ТОЛЬКО одной буквы АНГЛИЙСКОГО алфавита."
            );
        }

        return inputEnglishSymbol.length() > 1
            ? inputEnglishSymbol.substring(0, 2).toLowerCase()
            : inputEnglishSymbol.toLowerCase();
    }

    public static int getTargetSymbolIndex(String targetSymbol) {
        return ENGLISH_KEYBOARD_SYMBOLS_STRING.indexOf(targetSymbol);
    }

    public static String getLeftSymbol(int targetSymbolIndex) {
        return targetSymbolIndex == FIRST_CHAR_INDEX
            ? LAST_CHAR
            : Character.toString(ENGLISH_KEYBOARD_SYMBOLS_STRING.charAt(targetSymbolIndex - 1));
    }
}
