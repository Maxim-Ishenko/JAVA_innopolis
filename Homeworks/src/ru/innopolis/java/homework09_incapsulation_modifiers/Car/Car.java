package ru.innopolis.java.homework09_incapsulation_modifiers.Car;

import java.util.Objects;
import java.util.Scanner;

public class Car {
    private String brand;
    private String model;
    private Integer year;
    private Integer power;
    private Integer acceleration;
    private Integer suspension;
    private Integer durability;

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public Integer getYear() {
        return year;
    }
    public Integer getPower() {
        return power;
    }
    public Integer getAcceleration() {
        return acceleration;
    }
    public Integer getDurability() {
        return durability;
    }
    public Integer getSuspension() {
        return suspension;
    }

    public void setBrand(String brand) throws Exception {
        if (Objects.equals(brand, "")) {
            throw new Exception("Марка автомобиля не может быть пустой строкой!");
        }
        this.brand = brand;
    }
    public void setBrand(Scanner scanner) throws Exception {
        System.out.println("Введите марку автомобиля: ");
        String brand = scanner.nextLine();

        if (Objects.equals(brand, "")) {
            throw new Exception("Марка автомобиля не может быть пустой строкой!");
        }
        this.brand = brand;
    }
    public void setModel(String model) throws Exception {
        if (Objects.equals(model, "")) {
            throw new Exception("Модель автомобиля не может быть пустой строкой!");
        }
        this.model = model;
    }
    public void setModel(Scanner scanner) throws Exception {
        System.out.println("Введите модель автомобиля: ");
        String model = scanner.nextLine();

        if (Objects.equals(model, "")) {
            throw new Exception("Модель автомобиля не может быть пустой строкой!");
        }
        this.model = model;
    }
    public void setYear(Integer year) throws Exception {
        if (year <= 0) {
            throw new Exception("Год выпуска может быть только положительным числом!");
        }
        this.year = year;
    }
    public void setYear(Scanner scanner) throws Exception {
        System.out.println("Введите год выпуска автомобиля: ");
        int year = scanner.nextInt();

        if (year <= 0) {
            throw new Exception("Год выпуска может быть только положительным числом!");
        }
        this.year = year;
    }
    public void setPower(Integer power) throws Exception {
        if (power <= 0) {
            throw new Exception("Мощность может быть только положительным числом!");
        }
        this.power = power;
    }
    public void setPower(Scanner scanner) throws Exception {
        System.out.println("Введите мощность автомобиля: ");
        int power = scanner.nextInt();

        if (power <= 0) {
            throw new Exception("Мощность может быть только положительным числом!");
        }
        this.power = power;
    }
    public void setDurability(Integer durability) throws Exception {
        if (durability < 0) {
            throw new Exception("Долговечность не может быть отрицательным числом!");
        }
        this.durability = durability;
    }
    public void setDurability(Scanner scanner) throws Exception {
        System.out.println("Введите надежность автомобиля: ");
        int durability = scanner.nextInt();

        if (durability < 0) {
            throw new Exception("Долговечность не может быть отрицательным числом!");
        }
        this.durability = durability;
    }
    public void setSuspension(Integer suspension) throws Exception {
        if (suspension <= 0) {
            throw new Exception("Значение подвески может быть только положительным числом!");
        }
        this.suspension = suspension;
    }
    public void setSuspension(Scanner scanner) throws Exception {
        System.out.println("Введите значение подвески автомобиля: ");
        int suspension = scanner.nextInt();

        if (suspension <= 0) {
            throw new Exception("Значение подвески может быть только положительным числом!");
        }
        this.suspension = suspension;
    }
    public void setAcceleration(Integer acceleration) throws Exception {
        if (acceleration <= 0) {
            throw new Exception("Ускорение может быть только положительным числом!");
        }
        this.acceleration = acceleration;
    }
    public void setAcceleration(Scanner scanner) throws Exception {
        System.out.println("Введите ускорение автомобиля: ");
        int acceleration = scanner.nextInt();

        if (acceleration <= 0) {
            throw new Exception("Ускорение может быть только положительным числом!");
        }
        this.acceleration = acceleration;
    }

    public Car() {}
    public Car(
        String brand,
        String model,
        Integer year,
        Integer power,
        Integer acceleration,
        Integer suspension,
        Integer durability
    ) throws Exception {
        this.setBrand(brand);
        this.setModel(model);
        this.setYear(year);
        this.setPower(power);
        this.setAcceleration(acceleration);
        this.setSuspension(suspension);
        this.setDurability(durability);
    }
    public Car(
        String brand,
        String model,
        Integer year,
        Integer acceleration,
        Integer durability
    ) throws Exception {
        this.setBrand(brand);
        this.setModel(model);
        this.setYear(year);
        this.setAcceleration(acceleration);
        this.setDurability(durability);
    }
    public Car(Scanner scanner) throws Exception {
        this.setBrand(scanner);
        this.setModel(scanner);
        this.setYear(scanner);
        this.setPower(scanner);
        this.setAcceleration(scanner);
        this.setSuspension(scanner);
        this.setDurability(scanner);
    }

    @Override
    public String toString() {
        return "Car{" +
            "brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", year=" + year +
            ", power=" + power +
            ", acceleration=" + acceleration +
            ", suspension=" + suspension +
            ", durability=" + durability +
        '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getYear(), car.getYear()) && Objects.equals(getPower(), car.getPower()) && Objects.equals(getAcceleration(), car.getAcceleration()) && Objects.equals(getSuspension(), car.getSuspension()) && Objects.equals(getDurability(), car.getDurability());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getYear(), getPower(), getAcceleration(), getSuspension(), getDurability());
    }
}

