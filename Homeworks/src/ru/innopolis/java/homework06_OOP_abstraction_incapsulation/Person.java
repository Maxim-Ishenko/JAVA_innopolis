package ru.innopolis.java.homework06_OOP_abstraction_incapsulation;

import java.util.Objects;
import java.util.Scanner;

public class Person {
    private String name;
    private double moneyAmount;
    private Product[] productsPackage;

    public Person(String name, double moneyAmount, Product[] productsPackage) {
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.productsPackage = productsPackage;
    }

    public String getName() {
        return name;
    }
    public double getMoneyAmount() {
        return moneyAmount;
    }

    public Product[] getProductsPackage() {
        return productsPackage;
    }

    public void setName(String name) throws Exception {
        if (Objects.equals(name, "")) {
            throw new Exception("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setName(Scanner scanner) throws Exception {
        System.out.println("Введите имя пользователя: ");
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            throw new Exception("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setMoneyAmount(double moneyAmount) throws Exception {
        if (moneyAmount < 0) {
            throw new Exception("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }
    public void setMoneyAmount(Scanner scanner) throws Exception {
        System.out.println("Введите сумму денег, доступную пользователю: ");
        double moneyAmount = scanner.nextDouble();

        if (moneyAmount < 0) {
            throw new Exception("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }
    public void setProductsPackage(Product[] products) {

    }


}
