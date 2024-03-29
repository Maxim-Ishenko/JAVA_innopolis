package ru.innopolis.java.homework4_classes_Object_String.task1_keyboard;

import java.util.InputMismatchException;

public class App {
    public static void main(String[] args) throws InputMismatchException {
        String targetEnglishSymbol = SymbolChecker.getInputSymbol();
        int targetSymbolIndex = SymbolChecker.getTargetSymbolIndex(targetEnglishSymbol);
        String leftSymbol = SymbolChecker.getLeftSymbol(targetSymbolIndex);

        System.out.println("Введенный символ: " + targetEnglishSymbol + "\n");
        System.out.println("Символ слева от введенного: " + leftSymbol);
    }
}
