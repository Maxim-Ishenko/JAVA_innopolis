package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Person {
    private String name;
    private double moneyAmount;
    private Product[] productsPackage = new Product[0];
    private int age;

    // HW7
    public Person() {}
    public Person(String name, double moneyAmount) throws Exception {
        this.setName(name);
        this.setMoneyAmount(moneyAmount);
    }
    public Person(String name, double moneyAmount, int age) throws Exception {
        this.setName(name);
        this.setMoneyAmount(moneyAmount);
        this.setAge(age);
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
    public int getAge() { return this.age; };

    public void setName(String name) throws Exception {
        if (Objects.equals(name, "")) {
            throw new Exception("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setName(Scanner scanner) throws Exception {
        System.out.println("Введите имя пользователя: ");
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            throw new Exception("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setMoneyAmount(double moneyAmount) throws Exception {
        if (moneyAmount < 0) {
            throw new Exception("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }
    public void setMoneyAmount(Scanner scanner) throws Exception {
        System.out.println("Введите сумму денег, доступную пользователю: ");
        double moneyAmount = scanner.nextDouble();

        if (moneyAmount < 0) {
            throw new Exception("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }
    public void setProductToPackage(Product product) throws Exception {
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
    // HW7
    public void setAge(int age) throws Exception {
        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        }

        this.age = age;
    }
    public void setAge(Scanner scanner) throws Exception {
        System.out.println("Введите возраст пользователя: ");
        int age = scanner.nextInt();

        if (age < 0) {
            throw new Exception("Возраст не может быть отрицательным числом!");
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", moneyAmount=" + moneyAmount +
            ", age=" + age +
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
