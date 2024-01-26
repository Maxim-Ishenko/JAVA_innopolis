package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PersonProductRepositoryImpl implements PersonProductRepository {
    /**
     * @param name - имя пользователя
     * @param moneyAmount - сумма денег
     * @return - нового пользователя
     */
    @Override
    public Person createNewPerson(String name, double moneyAmount) {
        return new Person(getPersonName(name), getPersonMoneyAmount(moneyAmount));
    }

    /**
     * @param name - название продукта
     * @param coast - цена продукта
     * @return - новый продукт
     */
    @Override
    public Product createNewProduct(String name, double coast) {
        return new Product(getProductName(name), getProductCoast(coast));
    }

    /**
     * @param name - имя пользователя
     * @return - имя или исключение
     */
    @Override
    public String getPersonName(String name) {
        try {
            if (Objects.equals(name, "")) {
                throw new IllegalArgumentException("Имя пользователя не может быть пустой строкой!");
            }

            return name;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    /**
     * @param moneyAmount - сумма денег
     * @return - сумма денег или исключение
     */
    @Override
    public Double getPersonMoneyAmount(Double moneyAmount) {
        try {
            if (moneyAmount < 0) {
                throw new IllegalArgumentException("Сумма не может быть отрицательным числом!");
            }

            return moneyAmount;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    /**
     * @param productName - название продукта
     * @return - название или исключение
     */
    @Override
    public String getProductName(String productName) {
        try {
            if (Objects.equals(productName, "")) {
                throw new IllegalArgumentException("Имя продукта не может быть пустой строкой!");
            }

            return productName;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    /**
     * @param coast - цена продукта
     * @return - цена или исключение
     */
    @Override
    public Double getProductCoast(Double coast) {
        try {
            if (coast < 0) {
                throw new IllegalArgumentException("Цена не может быть отрицательным числом!");
            }

            return coast;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

}
