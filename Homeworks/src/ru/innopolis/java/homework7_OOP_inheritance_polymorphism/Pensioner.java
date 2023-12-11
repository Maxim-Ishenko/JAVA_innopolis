package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.Scanner;

public class Pensioner extends Person {

    public Pensioner() {}
    public Pensioner(String name, double moneyAmount, int age) throws Exception {
        super(name, moneyAmount);
        this.setAge(age);
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age < 65) {
            throw new Exception("Возраст пенсионера человека должен быть от 65 лет!");
        } else {
            super.setAge(age);
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
            super.setAge(age);
        }
    }
    @Override
    public void setProductToPackage(Product product) throws Exception {
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
