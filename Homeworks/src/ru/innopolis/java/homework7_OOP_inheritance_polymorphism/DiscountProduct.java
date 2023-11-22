package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.time.LocalDate;
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
    public void setDiscountValue(Integer discountValue) throws Exception {
        if (discountValue < 0) {
            throw new Exception("Скидка не может быть отрицательной!");
        }

        this.discountValue = discountValue;
    }
    public void setDiscountLimit(LocalDate discountLimit) {
        this.discountLimit = discountLimit;
    }
    public void setDiscountValue(Scanner scanner) throws Exception {
        System.out.println("Введите размер скидки в процентах: ");
        int discountValue = scanner.nextInt();

        if (discountValue < 0) {
            throw new Exception("Скидка не может быть отрицательной!");
        }

        this.discountValue = discountValue;
    }
    @Override
    public void setProductCoast(Scanner scanner) throws Exception {
        System.out.println("Введите цену продукта: ");
        double coast = scanner.nextDouble();

        if (coast < 0) {
            throw new Exception("Цена не может быть отрицательным числом!");
        }

        // В задании не указано, что нужно делать отдельное поля - цена с учетом скидки, потому сделал на базе самой цены продукта по условию
        if (!discountLimit.isAfter(LocalDate.now())) {
            this.setProductCoast(coast);
        } else {
            this.setProductCoast(coast * (1 - (double) discountValue / 100));
        }
    }
    public void setDiscountLimit(Scanner scanner) {
        System.out.println("Введите, до какого числа действует скидка в формате `yyyy-mm-dd`: ");
        this.discountLimit = LocalDate.parse(scanner.next());
    }
    public DiscountProduct(
        String productName,
        double coast,
        Integer discountValue,
        LocalDate discountLimit) throws Exception {
        super(productName);
        this.setDiscountValue(discountValue);
        this.setDiscountLimit(discountLimit);

        if (!discountLimit.isAfter(LocalDate.now())) {
            this.setProductCoast(coast);
        } else {
            this.setProductCoast(coast * (1 - (double) discountValue / 100));
        }
    }
    public DiscountProduct(Scanner scanner) throws Exception {
        super();
        this.setProductName(scanner);
        this.setDiscountValue(scanner);
        this.setDiscountLimit(scanner);
        this.setProductCoast(scanner);
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
