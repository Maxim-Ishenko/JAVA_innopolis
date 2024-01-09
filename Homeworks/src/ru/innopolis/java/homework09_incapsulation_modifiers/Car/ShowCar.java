package ru.innopolis.java.homework09_incapsulation_modifiers.Car;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowCar extends Car {
    private Integer stars = 0;

    public ShowCar() {
        super();
    }
    public ShowCar(
            String brand,
            String model,
            Integer year,
            Integer power,
            Integer acceleration,
            Integer suspension,
            Integer durability
    ) throws IllegalArgumentException {
        super(
                brand,
                model,
                year,
                power,
                acceleration,
                suspension,
                durability
        );
    }
    public ShowCar(
            String brand,
            String model,
            Integer year,
            Integer power,
            Integer acceleration,
            Integer suspension,
            Integer durability,
            Integer stars
    ) throws IllegalArgumentException {
        super(
                brand,
                model,
                year,
                power,
                acceleration,
                suspension,
                durability
        );
        this.setStars(stars);
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) throws IllegalArgumentException {
        if (stars < 0) {
            throw new IllegalArgumentException("Значение популярности не может быть отрицательным!");
        }

        this.stars = stars;
    }
    public void setStars(Scanner scanner) throws IllegalArgumentException {
        System.out.println("Введите значение популярности автомобиля: ");
        int stars = scanner.nextInt();

        if (stars < 0) {
            throw new IllegalArgumentException("Значение популярности не может быть отрицательным!");
        }

        this.stars = stars;
    }
}
