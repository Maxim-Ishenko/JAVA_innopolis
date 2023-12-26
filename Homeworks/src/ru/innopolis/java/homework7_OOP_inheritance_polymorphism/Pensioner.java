package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Pensioner extends Person {

    public Pensioner() {}
    public Pensioner(String name, double moneyAmount, int age) throws IllegalArgumentException, InputMismatchException {
        super(name, moneyAmount);
        this.setAge(age);
    }

    @Override
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным числом!");
        } else if (age < 65) {
            throw new IllegalArgumentException("Возраст пенсионера человека должен быть от 65 лет!");
        } else {
            super.setAge(age);
        }
    }
    @Override
    public void setAge(Scanner scanner) throws InputMismatchException {
        int age = scanner.nextInt();

        if (age < 0) {
            throw new InputMismatchException("Возраст не может быть отрицательным числом!");
        } else if (age < 65) {
            throw new InputMismatchException("Возраст пенсионера человека должен быть от 65 лет!");
        } else {
            super.setAge(age);
        }
    }
    @Override
    public void setProductToPackage(Product product) throws IllegalArgumentException {
        double ADDITIONAL_DISCOUNT = 0.05;
        if (this.getMoneyAmount() < product.getCoast() - product.getCoast() * ADDITIONAL_DISCOUNT) {
            System.out.println(
                super.getName() + " не может позволить себе " + product.getProductName()
            );
        } else if (product.getClass().getSimpleName().equals("DiscountProduct")) {
            super.setProductToPackage(product);
        }
    }
}
