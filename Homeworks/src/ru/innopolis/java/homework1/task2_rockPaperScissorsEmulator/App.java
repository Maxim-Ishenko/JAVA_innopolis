package ru.innopolis.java.homework1.task2_rockPaperScissorsEmulator;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String vasyaOption = RockPaperScissorsGameEmulator.getRandomOption();
        String petyaOption = RockPaperScissorsGameEmulator.getRandomOption();

        int vasyaOptionIndex = RockPaperScissorsGameEmulator.getSelectedOptionIndex(vasyaOption);
        int petyaOptionIndex = RockPaperScissorsGameEmulator.getSelectedOptionIndex(petyaOption);

        String winner = RockPaperScissorsGameEmulator.getTheWinner(vasyaOptionIndex, petyaOptionIndex);

        System.out.println("Vasya threw: " + vasyaOption + "\n");
        System.out.println("Petya threw: " + petyaOption + "\n");
        System.out.println("And the winner is: " + winner);
    }
}
