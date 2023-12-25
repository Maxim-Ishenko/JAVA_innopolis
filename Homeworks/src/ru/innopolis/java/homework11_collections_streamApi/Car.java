package ru.innopolis.java.homework11_collections_streamApi;

import java.util.Objects;

public class Car {
    private String number;
    private String model;
    private Colors color;
    private Integer mileage;
    private Double coast;

    public Car() {}
    public Car(String number, String model, Colors color, Integer mileage, Double coast) throws Exception {
        this.setNumber(number);
        this.setModel(model);
        this.setColor(color);
        this.setMileage(mileage);
        this.setCoast(coast);
    }

    public String getNumber() {
        return number;
    }
    public String getModel() {
        return model;
    }
    public Colors getColor() {
        return color;
    }
    public Integer getMileage() {
        return mileage;
    }
    public Double getCoast() {
        return coast;
    }

    public void setNumber(String number) throws IllegalArgumentException {
        // Требований к формату номера в условии не оговорено, поэтому ввел только одну проверку - для проформы
        if (number.length() != 6) {
            throw new IllegalArgumentException("Номер автомобиля должен состоять из 6 знаков!");
        }

        this.number = number;
    }
    public void setModel(String model) throws IllegalArgumentException {
        if (Objects.equals(model, "")) {
            throw new IllegalArgumentException("Модель автомобиля не может быть пустой строкой!");
        }

        this.model = model;
    }
    public void setColor(Colors color) {
        this.color = color;
    }
    public void setMileage(Integer mileage) throws IllegalArgumentException {
        if (mileage < 0) {
            throw new IllegalArgumentException("Пробег не может быть отрицательным числом!");
        }

        this.mileage = mileage;
    }
    public void setCoast(Double coast) throws IllegalArgumentException {
        if (coast < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательным числом!");
        }

        this.coast = coast;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", coast=" + coast +
                '}' + '\n';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getNumber(), car.getNumber()) &&
                Objects.equals(getModel(), car.getModel()) &&
                Objects.equals(getColor(), car.getColor()) &&
                Objects.equals(getMileage(), car.getMileage()) &&
                Objects.equals(getCoast(), car.getCoast());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getModel(), getColor(), getMileage(), getCoast());
    }
}
