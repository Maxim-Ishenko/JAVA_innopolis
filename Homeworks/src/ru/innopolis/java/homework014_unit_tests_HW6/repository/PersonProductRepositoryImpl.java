package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

public class PersonProductRepositoryImpl implements PersonProductRepository {
    @Override
    public Person createNewPerson(String name, double moneyAmount) {
        return new Person(name, moneyAmount);
    }

    @Override
    public Product createNewProduct(String name, double coast) {
        return new Product(name, coast);
    }
}
