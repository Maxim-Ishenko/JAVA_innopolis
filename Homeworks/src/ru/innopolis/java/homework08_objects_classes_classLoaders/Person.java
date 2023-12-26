package ru.innopolis.java.homework08_objects_classes_classLoaders;

import java.util.*;

public class Person {
    private static int NEW_ARR_SIZE = 0;
    private String name;
    private double moneyAmount;
    private Product[] productsPackage = new Product[NEW_ARR_SIZE];
    private final String personsStorePath = "src/ru/innopolis/java/homework08_objects_classes_classLoaders/data/persons.txt";

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
    public void setName(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите имя пользователя: ");
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            throw new InputMismatchException("Имя пользователя не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setMoneyAmount(double moneyAmount) throws IllegalArgumentException {
        if (moneyAmount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательным числом!");
        }

        this.moneyAmount = moneyAmount;
    }
    public void setMoneyAmount(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите сумму денег, доступную пользователю: ");
        double moneyAmount = scanner.nextDouble();

        if (moneyAmount < 0) {
            throw new InputMismatchException("Сумма не может быть отрицательным числом!");
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
