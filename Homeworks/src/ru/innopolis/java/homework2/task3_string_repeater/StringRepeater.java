package ru.innopolis.java.homework2.task3_string_repeater;

import java.util.Scanner;

public class StringRepeater {
    public static String getInputString() {
        System.out.println("Исходная строка: ");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextLine();
    }

    public static int getRepeatOrder() {
        System.out.println("Сколько раз вывести строку?");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextInt();
    }

    public static void getRepeatedString(String baseString, int repeatOrder) {
        System.out.println(baseString.trim().repeat(repeatOrder));
    }
}
