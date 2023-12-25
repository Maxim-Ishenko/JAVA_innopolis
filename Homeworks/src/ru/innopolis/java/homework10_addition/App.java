package ru.innopolis.java.homework10_addition;

public class App {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer number = pair.getFirst(); // 1
        String string = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println("number: " + number);
        System.out.println("string: " + string);
        System.out.println("mustBeTrue: " + mustBeTrue);
        System.out.println("mustAlsoBeTrue: " + mustAlsoBeTrue);
    }

}
