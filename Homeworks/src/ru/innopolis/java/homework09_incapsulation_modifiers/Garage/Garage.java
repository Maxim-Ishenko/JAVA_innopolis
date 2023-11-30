package ru.innopolis.java.homework09_incapsulation_modifiers.Garage;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;

public class Garage {
    private Car[] parkedCars = {};

    public Car[] getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(Car[] parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Garage() {}
    public Garage(Car[] parkedCars) {
        this.setParkedCars(parkedCars);
    }

    public void modifyPower(Car car, Integer power) {

    }
    public void modifyAcceleration(Car car, Integer acceleration) {

    }
    public void modifySuspension(Car car, Integer suspension) {

    }
    public void modifyDurability(Car car, Integer durability) {

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
