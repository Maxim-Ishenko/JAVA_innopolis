package ru.innopolis.java.homework4_classes_Object_String.task2_arrows_checker;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String inputString = ArrowsCounter.getInputString(input);
        int leftArrowsAmount = ArrowsCounter.getArrowsAmount(inputString, ArrowsCounter.LEFT_ARROW_SUBSTRING);
        int rightArrowsAmount = ArrowsCounter.getArrowsAmount(inputString, ArrowsCounter.RIGHT_ARROW_SUBSTRING);

        System.out.println("Входная строка содержит " + (leftArrowsAmount + rightArrowsAmount) + " стрел");

        input.close();
    }
}
