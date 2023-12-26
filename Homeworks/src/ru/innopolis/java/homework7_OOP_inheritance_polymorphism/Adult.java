package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Adult extends Person {
    private boolean isCreditAvailable = true;

    public boolean getIsCreditAvailable() {
        return isCreditAvailable;
    }

    public Adult() {}
    public Adult(String name, double moneyAmount, int age) throws IllegalArgumentException, InputMismatchException {
        super(name, moneyAmount);
        this.setAge(age);
        this.setIsCreditAvailable(this.getMoneyAmount() > 0);
    }

    @Override
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным числом!");
        } else if (age < 18 || age >= 65) {
            throw new IllegalArgumentException("Возраст зрослого человека должен быть от 18 до 65 лет!");
        } else {
            super.setAge(age);
        }
    }
    @Override
    public void setAge(Scanner scanner) throws InputMismatchException {
        int age = scanner.nextInt();

        if (age < 0) {
            throw new InputMismatchException("Возраст не может быть отрицательным числом!");
        } else if (age < 18 || age >= 65) {
            throw new InputMismatchException("Возраст зрослого человека должен быть от 18 до 65 лет!");
        } else {
            super.setAge(age);
        }
    }
    private void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
    }
}
