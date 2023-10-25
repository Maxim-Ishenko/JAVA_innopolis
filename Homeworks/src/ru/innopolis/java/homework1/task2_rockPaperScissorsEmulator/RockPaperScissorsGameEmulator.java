package ru.innopolis.java.homework1.task2_rockPaperScissorsEmulator;

import java.util.Arrays;

public class RockPaperScissorsGameEmulator {
    private static final String[] variants = new String[]{ "rock", "paper", "scissors"};
    public static String getRandomOption() {
        int n = (int)Math.floor(Math.random()*variants.length);
        return variants[n];
    };

    public static int getSelectedOptionIndex(String selectedOption)  {
        return Arrays.asList(variants).indexOf(selectedOption);
    }

    public static String getTheWinner(int vasyaOptionIndex, int petyaOptionIndex) {
        if((vasyaOptionIndex == 0 && petyaOptionIndex == 2) || (vasyaOptionIndex == 2 && petyaOptionIndex == 1) || (vasyaOptionIndex == 1 && petyaOptionIndex == 0)) {
            return "Vasya";
        } else if((vasyaOptionIndex == 0 && petyaOptionIndex == 1) || (vasyaOptionIndex == 1 && petyaOptionIndex == 2) || (vasyaOptionIndex == 2 && petyaOptionIndex == 0)) {
            return "Petya";
        } else {
            return "Friendship";
        }
    };
}
