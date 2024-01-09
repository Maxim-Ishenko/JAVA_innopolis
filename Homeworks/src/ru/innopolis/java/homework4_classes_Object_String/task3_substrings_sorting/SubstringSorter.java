package ru.innopolis.java.homework4_classes_Object_String.task3_substrings_sorting;

import java.util.Arrays;
import java.util.Scanner;

public class SubstringSorter {
    private static final String SPACE = "\\s+";
    private static final String EMPTY_SPACE = " ";

    public static String getInputString(Scanner input) {
        System.out.println("Введите входную строку, состоящую из букв английского алфавита: ");

        return input.nextLine();
    }

    public static StringBuilder getFinalStringWithSortedSubstrings(String inputString) {
        String[] inputSubstringsEntity = inputString.split(SPACE);
        StringBuilder sortedStringsCombiner = new StringBuilder();

        for (String s : inputSubstringsEntity) {
            char[] tempCharArray = s.toCharArray();
            Arrays.sort(tempCharArray);
            sortedStringsCombiner.append(EMPTY_SPACE).append(new String(tempCharArray));
        }

        return sortedStringsCombiner;
    }
}
