package ru.innopolis.java.homework09_incapsulation_modifiers.Garage;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;

public class Garage {
    private Car[] parkedCars = {};

    public Garage() {}
    public Garage(Car[] parkedCars) {
        this.setParkedCars(parkedCars);
    }

    public Car[] getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(Car[] parkedCars) {
        this.parkedCars = parkedCars;
    }

    public void modifyPower(Car car, Integer power) throws IllegalArgumentException {
        if (power <= 0) {
            throw new IllegalArgumentException("Мощность может быть только положительным числом!");
        }

        car.setPower(power);
    }
    public void modifyAcceleration(Car car, Integer acceleration) throws IllegalArgumentException {
        if (acceleration <= 0) {
            throw new IllegalArgumentException("Ускорение может быть только положительным числом!");
        }

        car.setAcceleration(acceleration);
    }
    public void modifySuspension(Car car, Integer suspension) throws IllegalArgumentException {
        if (suspension <= 0) {
            throw new IllegalArgumentException("Значение подвески может быть только положительным числом!");
        }

        car.setSuspension(suspension);
    }
    public void modifyDurability(Car car, Integer durability) throws IllegalArgumentException {
        if (durability < 0) {
            throw new IllegalArgumentException("Долговечность не может быть отрицательным числом!");
        }

        car.setDurability(durability);
    }

    @Override
    public String toString() {
        return "Garage{" +
            "parkedCars=" + Arrays.toString(parkedCars) +
        '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Arrays.equals(getParkedCars(), garage.getParkedCars());
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(getParkedCars());
    }
}
