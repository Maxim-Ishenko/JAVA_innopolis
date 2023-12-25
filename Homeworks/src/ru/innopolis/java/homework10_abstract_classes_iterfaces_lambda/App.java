package ru.innopolis.java.homework10_abstract_classes_iterfaces_lambda;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ByCondition isEvenNumber = number -> number % 2 == 0;
        ByCondition isSumOfDigitsEven = number -> {
            int sum = 0;
            String[] arrOfItemsDigitsStrings = String.valueOf(number).split("");

            for (String arrOfItemsDigitsString : arrOfItemsDigitsStrings) {
                sum = sum + Integer.parseInt(arrOfItemsDigitsString);
            }

            return sum % 2 == 0;
        };
        Integer[] arrEx = {10, 21, 31, 4, 5, 6, 72, 8, 8};

        System.out.println("Initial array: " + Arrays.toString(arrEx));
        System.out.println("isEvenNumber: " + Sequence.filter(arrEx, isEvenNumber));
        System.out.println("isSumOfDigitsEven: " + Sequence.filter(arrEx, isSumOfDigitsEven));
    }
}
