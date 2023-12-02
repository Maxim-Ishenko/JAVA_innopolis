package ru.innopolis.java.homework09_incapsulation_modifiers.Car;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PerformanceCar extends Car {
    private String[] addOns = {};

    public String[] getAddOns() {
        return addOns;
    }

    public void setAddOns(String[] addOns) {
        this.addOns = addOns;
    }

    @Override
    public void setPower(Integer power) throws Exception {
        if (power <= 0) {
            throw new Exception("Мощность может быть только положительным числом!");
        }

        super.setPower((int) (power + power * 0.5));
    }
    @Override
    public void setPower(Scanner scanner) throws Exception {
        System.out.println("Введите мощность автомобиля: ");
        int power = scanner.nextInt();

        if (power <= 0) {
            throw new Exception("Мощность может быть только положительным числом!");
        }

        super.setPower((int) (power + power * 0.5));
    }
    @Override
    public void setSuspension(Integer suspension) throws Exception {
        if (suspension <= 0) {
            throw new Exception("Значение подвески может быть только положительным числом!");
        }

        super.setSuspension((int) (suspension - suspension * 0.25));
    }
    @Override
    public void setSuspension(Scanner scanner) throws Exception {
        System.out.println("Введите значение подвески автомобиля: ");
        int suspension = scanner.nextInt();

        if (suspension <= 0) {
            throw new Exception("Значение подвески может быть только положительным числом!");
        }

        super.setSuspension((int) (suspension - suspension * 0.25));
    }

    public PerformanceCar() {
        super();
    }
    public PerformanceCar(
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
            acceleration,
            durability
        );
        this.setPower(power);
        this.setSuspension(suspension);
    }
    public PerformanceCar(
        String brand,
        String model,
        Integer year,
        Integer power,
        Integer acceleration,
        Integer suspension,
        Integer durability,
        String[] addOns
    ) throws Exception {
        super(
            brand,
            model,
            year,
            acceleration,
            durability
        );
        this.setPower(power);
        this.setSuspension(suspension);
        this.setAddOns(addOns);
    }
    public PerformanceCar(Scanner scanner) throws Exception {
        super();
        super.setBrand(scanner);
        super.setModel(scanner);
        super.setYear(scanner);
        super.setDurability(scanner);
        super.setAcceleration(scanner);
        this.setPower(scanner);
        this.setSuspension(scanner);
    }

    @Override
    public String toString() {
        return "PerformanceCar{" +
            "brand='" + this.getBrand() + '\'' +
            ", model='" + this.getModel() + '\'' +
            ", year=" + this.getYear() +
            ", power=" + this.getPower() +
            ", acceleration=" + this.getAcceleration() +
            ", suspension=" + this.getSuspension() +
            ", durability=" + this.getDurability() +
            "addOns=" + Arrays.toString(addOns) +
        '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerformanceCar that = (PerformanceCar) o;
        return Arrays.equals(getAddOns(), that.getAddOns());
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getAddOns());
        return result;
    }
}
