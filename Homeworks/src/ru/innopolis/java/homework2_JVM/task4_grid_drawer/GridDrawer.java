package ru.innopolis.java.homework2_JVM.task4_grid_drawer;

import java.util.Scanner;

public class GridDrawer {
    public static int getColumnsAmount() {
        System.out.println("Ожидаемое количество колонок в итоговой сетке: ");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextInt();
    }

    public static int getStringsAmount() {
        System.out.println("Ожидаемое количество строк в итоговой сетке: ");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextInt();
    }

    public static String getRepeatableElement() {
        System.out.println("Введите базовый элемент для формирования сетки: ");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextLine();
    }

    public static void getResultGrid(int column, int string, String element) {
        for (int strAmount = 1; strAmount <= string; strAmount++) {
            System.out.println(element.repeat(column) + "\n");
        }
    }
}
