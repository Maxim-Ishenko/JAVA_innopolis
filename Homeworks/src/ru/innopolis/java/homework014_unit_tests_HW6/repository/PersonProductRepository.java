package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

public interface PersonProductRepository {
    Person createNewPerson(String name, double moneyAmount);
    Product createNewProduct(String name, double coast);
    String getPersonName(String name);
    Double getPersonMoneyAmount(Double moneyAmount);
    String getProductName(String productName);
    Double getProductCoast(Double coast);
}
