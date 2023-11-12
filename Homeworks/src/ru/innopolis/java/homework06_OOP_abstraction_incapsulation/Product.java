package ru.innopolis.java.homework06_OOP_abstraction_incapsulation;

import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String productName;
    private double coast;

    public Product(String productName, double coast) {
        this.productName = productName;
        this.coast = coast;
    }

    public String getProductName() {
        return productName;
    }
    public double getCoast() {
        return coast;
    }

    public void setProductName(String productName) throws Exception {
        if (Objects.equals(productName, "")) {
            throw new Exception("Имя продукта не может быть пустой строкой!");
        }

        this.productName = productName;
    }
    public void setProductName(Scanner scanner) throws Exception {
        System.out.println("Введите имя продукта: ");
        String productName = scanner.nextLine();

        if (Objects.equals(productName, "")) {
            throw new Exception("Имя продукта не может быть пустой строкой!");
        }

        this.productName = productName;
    }
    public void setMoneyAmount(double coast) throws Exception {
        if (coast < 0) {
            throw new Exception("Цена не может быть отрицательным числом!");
        }

        this.coast = coast;
    }
    public void setMoneyAmount(Scanner scanner) throws Exception {
        System.out.println("Введите цену продукта: ");
        double moneyAmount = scanner.nextDouble();

        if (moneyAmount < 0) {
            throw new Exception("Цена не может быть отрицательным числом!");
        }

        this.coast = moneyAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", coast=" + coast +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(getCoast(), product.getCoast()) == 0 && Objects.equals(getProductName(), product.getProductName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getCoast());
    }
}
