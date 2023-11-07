package ru.innopolis.java.homework4_classes_Object_String.task3_substrings_sorting;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        String inputString = SubstringSorter.getInputString(input);
        System.out.println("Результат: " + SubstringSorter.getFinalStringWithSortedSubstrings(inputString));

        input.close();
    }
}
