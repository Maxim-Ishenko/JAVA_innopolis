package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class DiscountProduct extends Product {
    private Integer discountValue = 0;
    private LocalDate discountLimit = LocalDate.now();
    public Integer getDiscountValue() {
        return discountValue;
    }
    public LocalDate getDiscountLimit() {
        return discountLimit;
    }

    public DiscountProduct() {}
    public DiscountProduct(
            String productName,
            double coast,
            Integer discountValue,
            LocalDate discountLimit) throws IllegalArgumentException, InputMismatchException {
        super(productName);
        this.setDiscountValue(discountValue);
        this.setDiscountLimit(discountLimit);

        if (!discountLimit.isAfter(LocalDate.now())) {
            super.setProductCoast(coast);
        } else {
            super.setProductCoast(coast * (1 - (double) discountValue / 100));
        }
    }
    public DiscountProduct(
            String productName,
            double coast,
            Integer discountValue) throws IllegalArgumentException, InputMismatchException {
        super(productName);
        this.setDiscountValue(discountValue);

        if (!discountLimit.isAfter(LocalDate.now())) {
            super.setProductCoast(coast);
        } else {
            super.setProductCoast(coast * (1 - (double) discountValue / 100));
        }
    }

    public void setDiscountValue(Integer discountValue) throws IllegalArgumentException {
        if (discountValue < 0) {
            throw new IllegalArgumentException("Скидка не может быть отрицательной!");
        }

        this.discountValue = discountValue;
    }
    public void setDiscountLimit(LocalDate discountLimit) {
        this.discountLimit = discountLimit;
    }
    public void setDiscountValue(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите размер скидки в процентах: ");
        int discountValue = scanner.nextInt();

        if (discountValue < 0) {
            throw new InputMismatchException("Скидка не может быть отрицательной!");
        }

        this.discountValue = discountValue;
    }
    @Override
    public void setProductCoast(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите цену продукта: ");
        double coast = scanner.nextDouble();

        if (coast <= 0) {
            throw new InputMismatchException("Цена должна быть положительным числом!");
        }

        // В задании не указано, что нужно делать отдельное поля - цена с учетом скидки, поэтому сделал на базе самой цены продукта по условию
        if (!discountLimit.isAfter(LocalDate.now())) {
            super.setProductCoast(coast);
        } else {
            super.setProductCoast(coast - ((coast / 100) * discountValue));
        }
    }

    public void setDiscountLimit(Scanner scanner) {
        System.out.println("Введите, до какого числа действует скидка в формате `yyyy-mm-dd`: ");
        this.discountLimit = LocalDate.parse(scanner.next());
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
            ", productName=" + this.getProductName() + "\n" +
            ", coast=" + this.getCoast() + "\n" +
            ", discountValue=" + discountValue + "\n" +
            ", discountLimit=" + discountLimit + "\n" +
            '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Objects.equals(getDiscountValue(), that.getDiscountValue()) && Objects.equals(getDiscountLimit(), that.getDiscountLimit());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDiscountValue(), getDiscountLimit());
    }
}
