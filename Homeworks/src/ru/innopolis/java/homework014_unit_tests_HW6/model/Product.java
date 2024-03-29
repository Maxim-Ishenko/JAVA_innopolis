package ru.innopolis.java.homework014_unit_tests_HW6.model;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String productName;
    private double coast;

   public Product() {}
    public Product(String productName, double coast) throws IllegalArgumentException {
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
        this.productName = productName;
    }

    public void setProductCoast(double coast) throws IllegalArgumentException {
        this.coast = coast;
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
