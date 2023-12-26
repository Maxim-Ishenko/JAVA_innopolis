package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String productName;
    private double coast;

   public Product() {}
    public Product(String productName) throws IllegalArgumentException, InputMismatchException {
        this.setProductName(productName);
    }
    public Product(String productName, double coast) throws IllegalArgumentException, InputMismatchException {
        this.setProductName(productName);
        this.setProductCoast(coast);
    }

    public String getProductName() {
        return productName;
    }
    public double getCoast() {
        return coast;
    }

    public void setProductName(String productName) throws IllegalArgumentException {
        if (Objects.equals(productName, "")) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой!");
        }

        if (productName.length() < 3) {
            throw new IllegalArgumentException("Название продукта должно содержать минимум 3 символа!");
        }

        if (productName.matches("\\d+")) {
            throw new IllegalArgumentException("Название продукта не может состоять только из цифр!");
        }

        this.productName = productName;
    }
    public void setProductName(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите имя продукта: ");
        String productName = scanner.nextLine();

        if (Objects.equals(productName, "")) {
            throw new InputMismatchException("Имя продукта не может быть пустой строкой!");
        }

        if (productName.length() < 3) {
            throw new InputMismatchException("Название продукта должно содержать минимум 3 символа!");
        }

        if (productName.matches("\\d+")) {
            throw new InputMismatchException("Название продукта не может состоять только из цифр!");
        }

        this.productName = productName;
    }
    public void setProductCoast(double coast) throws IllegalArgumentException {
        if (coast <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительным числом!");
        }

        this.coast = coast;
    }
    public void setProductCoast(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите цену продукта: ");
        double moneyAmount = scanner.nextDouble();

        if (moneyAmount <= 0) {
            throw new InputMismatchException("Цена должна быть положительным числом!");
        }

        this.coast = moneyAmount;
    }

    @Override
    public String toString() {
        return "Product{" + "\n" +
            "productName='" + productName + "\n, " +
            "coast=" + coast + "\n" +
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
