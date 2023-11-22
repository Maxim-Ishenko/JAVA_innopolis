package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.Scanner;

public class Pensioner extends Person {
    private final double additionalDiscount = 0.05;

    @Override
    public void setAge(int age) throws Exception {
        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age < 65) {
            throw new Exception("Возраст пенсионера человека должен быть от 65 лет!");
        } else {
            this.setAge(age);
        }
    }
    @Override
    public void setAge(Scanner scanner) throws Exception {
        int age = scanner.nextInt();

        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age < 65) {
            throw new Exception("Возраст пенсионера человека должен быть от 65 лет!");
        } else {
            this.setAge(age);
        }
    }

    public Pensioner(String name, double moneyAmount, int age) throws Exception {
        super(name, moneyAmount);
        this.setAge(age);
    }
    public Pensioner(Scanner scanner) throws Exception {
        super();
        this.setName(scanner);
        this.setMoneyAmount(scanner);
        this.setAge(scanner);
    }
}
