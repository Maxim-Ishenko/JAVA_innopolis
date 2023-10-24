package ru.innopolis.java.homework1;

import java.util.Arrays;

public class GameEmulator {
    private static final String[] variants = new String[]{ "rock", "paper", "scissors"};
    public static String getRandomOption() {
        int n = (int)Math.floor(Math.random()*variants.length);
        return variants[n];
    };

    public static String getTheWinner(int vasyaOption, int petyaOption) {

        if((vasyaOption == 0 && petyaOption == 2) || (vasyaOption == 2 && petyaOption == 1)) {
            return "Vasya";
        } else if((vasyaOption == 0 && petyaOption == 1) || (vasyaOption == 1 && petyaOption == 2)) {
            return "Petya";
        } else {
            return "Friendship";
        }
    };

    public static void main(String[] args) {
        String vasyaOption = GameEmulator.getRandomOption();
        String petyaOption = GameEmulator.getRandomOption();

        int vasyaOptionIndex = Arrays.asList(variants).indexOf(vasyaOption);
        int petyaOptionIndex = Arrays.asList(variants).indexOf(petyaOption);


        String winner = GameEmulator.getTheWinner(vasyaOptionIndex, petyaOptionIndex);

        System.out.println("Vasya threw: " + vasyaOption + "\b");
        System.out.println("Petye threw: " + petyaOption + "\b");
        System.out.println("And the winner is: " + winner);
    }
}
