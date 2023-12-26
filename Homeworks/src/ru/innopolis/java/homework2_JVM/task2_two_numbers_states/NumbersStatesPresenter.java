package ru.innopolis.java.homework2_JVM.task2_two_numbers_states;

import java.util.Scanner;

public class NumbersStatesPresenter {
    // Парам порядок и прокидывание исключения добавил чисто чтобы реализовать разные сообщения при вводе первого и второго числа, как в ождиаемы результатах задания
    public static int getIntNum(int order) throws IllegalArgumentException {
        if (order != 1 && order != 2)
            throw new IllegalArgumentException(
                "Данное приложение предназначенно для сравнения только двух чисел. Порядок должен быть равен 1 или 2"
            );

        System.out.println("Введите " + (order == 1 ? "1-ое" : "второе") + "целое число: ");
        Scanner currentScannerEntity = new Scanner(System.in);

        return currentScannerEntity.nextInt();
    }

    public static int getSumm(int a, int b) {
        return a + b;
    }

    public static int getDifference(int a, int b) {
        return a - b;
    }

    public static int getMultiplicationResult(int a, int b) {
        return a * b;
    }

    public static double getMediumVal(double a, double b) {
        return (a + b) / 2;
    }

    public static int getDistance(int a, int b) {
        return Math.abs(a - b);
    }

    public static int getMax(int a, int b) {
        return Math.max(a, b);
    }

    public static int getMin(int a, int b) {
        return Math.min(a, b);
    }
}
