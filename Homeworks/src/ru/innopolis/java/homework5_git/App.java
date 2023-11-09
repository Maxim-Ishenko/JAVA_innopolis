package ru.innopolis.java.homework5_git;

import java.util.Scanner;

public class App {
    public static void main(String [] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        TV[] tvCollection = TV.getTVCollection(scanner, 2);

        scanner.close();
    }
}
