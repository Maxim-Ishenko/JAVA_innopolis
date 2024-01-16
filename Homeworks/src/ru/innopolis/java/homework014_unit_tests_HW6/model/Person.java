package ru.innopolis.java.homework014_unit_tests_HW6.model;

import java.util.*;

public class Person {
    private String name;
    private double moneyAmount;
    private Product[] productsPackage = new Product[0];

    public Person() {}
    public Person(String name, double moneyAmount) throws IllegalArgumentException, InputMismatchException {
        this.setName(name);
        this.setMoneyAmount(moneyAmount);
    }

    public String getName() {
        return name;
    }
    public double getMoneyAmount() {
        return moneyAmount;
    }

    public Product[] getProductsPackage() {
        return productsPackage;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (Objects.equals(name, "")) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }

    public void setMoneyAmount(double moneyAmount) throws IllegalArgumentException {
        if (moneyAmount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }

    public void setProductToPackage(Product product) {
        if (this.moneyAmount < product.getCoast()) {
            System.out.println(
                this.name + " не может позволить себе " + product.getProductName()
            );
        } else {
            ArrayList<Product> productsList = new ArrayList<>(Arrays.asList(productsPackage));
            productsList.add(product);

            this.productsPackage = productsList.toArray(productsPackage);

            System.out.println(this.name + " купил " + product.getProductName());

            this.setMoneyAmount(moneyAmount - product.getCoast());
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", productsPackage=" + Arrays.toString(productsPackage) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(getMoneyAmount(), person.getMoneyAmount()) == 0 && Objects.equals(getName(), person.getName()) && Arrays.equals(getProductsPackage(), person.getProductsPackage());
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getMoneyAmount());
        result = 31 * result + Arrays.hashCode(getProductsPackage());
        return result;
    }
}
