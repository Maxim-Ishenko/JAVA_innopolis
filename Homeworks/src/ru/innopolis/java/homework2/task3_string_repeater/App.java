package ru.innopolis.java.homework2.task3_string_repeater;

public class App {
    public static void main(String[] args) {
        String baseString = StringRepeater.getInputString();
        int repeatOrder = StringRepeater.getRepeatOrder();

        StringRepeater.getRepeatedString(baseString, repeatOrder);
    }
}
