package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

public interface PersonProductRepository {
    Person createNewPerson(String name, double moneyAmount);
    Product createNewProduct(String name, double coast);
    public String getPersonName(String name);
    public Double getPersonMoneyAmount(Double moneyAmount);
    public String getProductName(String productName);
    public Double getProductCoast(Double coast);
}
