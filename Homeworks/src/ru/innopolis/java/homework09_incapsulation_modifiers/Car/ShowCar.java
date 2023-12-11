package ru.innopolis.java.homework09_incapsulation_modifiers.Car;

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
    ) throws Exception {
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
    ) throws Exception {
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

    public void setStars(Integer stars) throws Exception {
        if (stars < 0) {
            throw new Exception("Значение популярности не может быть отрицательным!");
        }

        this.stars = stars;
    }
    public void setStars(Scanner scanner) throws Exception {
        System.out.println("Введите значение популярности автомобиля: ");
        int stars = scanner.nextInt();

        if (stars < 0) {
            throw new Exception("Значение популярности не может быть отрицательным!");
        }

        this.stars = stars;
    }
}
