package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.Scanner;

public class Adult extends Person {
    private boolean isCreditAvailable = true;

    public boolean getIsCreditAvailable() {
        return isCreditAvailable;
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age < 18 || age >= 65) {
            throw new Exception("Возраст зрослого человека должен быть от 18 до 65 лет!");
        } else {
            this.setAge(age);
        }
    }
    @Override
    public void setAge(Scanner scanner) throws Exception {
        int age = scanner.nextInt();

        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        } else if (age < 18 || age >= 65) {
            throw new Exception("Возраст зрослого человека должен быть от 18 до 65 лет!");
        } else {
            this.setAge(age);
        }
    }
    private void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
    }

    public Adult(String name, double moneyAmount, int age) throws Exception {
        super(name, moneyAmount);
        this.setAge(age);
        this.setIsCreditAvailable(this.getMoneyAmount() > 0);
    }
    public Adult(Scanner scanner) throws Exception {
        super();
        this.setName(scanner);
        this.setMoneyAmount(scanner);
        this.setAge(scanner);
        this.setIsCreditAvailable(this.getMoneyAmount() > 0);
    }
}
