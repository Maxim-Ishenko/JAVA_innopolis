package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.Scanner;

public class Children extends Person {
    // В задании указано, что не может быть отрицательным числом. Я сделал булевым, так логичнее.
    private boolean isBuyProductsAvailable = false;

    public boolean getIsBuyProductsAvailable() {
        return isBuyProductsAvailable;
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age > 17) {
            throw new Exception("Возраст ребенка не может быть больше 17 лет!");
        } else {
            this.setAge(age);
        }
    }
    @Override
    public void setAge(Scanner scanner) throws Exception {
        int age = scanner.nextInt();

        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age > 17) {
            throw new Exception("Возраст ребенка не может быть больше 17 лет!");
        } else {
            this.setAge(age);
        }
    }
    private void setIsBuyProductsAvailable() {
        this.isBuyProductsAvailable = true;
    }

    public Children(String name, double moneyAmount, int age) throws Exception {
        super(name, moneyAmount);
        this.setAge(age);
        if (age >= 6) this.setIsBuyProductsAvailable();
    }
    public Children(Scanner scanner) throws Exception {
        super();
        this.setName(scanner);
        this.setMoneyAmount(scanner);
        this.setAge(scanner);
        if (this.getAge() >= 6) this.setIsBuyProductsAvailable();
    }
}
