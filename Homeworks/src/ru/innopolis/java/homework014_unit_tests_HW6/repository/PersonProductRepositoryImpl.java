package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

import java.util.Objects;

public class PersonProductRepositoryImpl implements PersonProductRepository {
    /**
     * @param name
     * @param moneyAmount
     * @return
     */
    @Override
    public Person createNewPerson(String name, double moneyAmount) {
        return new Person(getPersonName(name), getPersonMoneyAmount(moneyAmount));
    }

    /**
     * @param name
     * @param coast
     * @return
     */
    @Override
    public Product createNewProduct(String name, double coast) {
        return new Product(name, coast);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public String getPersonName(String name) {
        try {
            if (Objects.equals(name, "")) {
                throw new IllegalArgumentException("Имя пользователя не может быть пустой строкой!");
            }

            return name;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустой строкой!", error);
        }
    }

    /**
     * @param moneyAmount
     * @return
     */
    @Override
    public Double getPersonMoneyAmount(Double moneyAmount) {
        try {
            if (moneyAmount < 0) {
                throw new IllegalArgumentException("Сумма не может быть отрицательным числом!");
            }

            return moneyAmount;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException("Сумма не может быть отрицательным числом!", error);
        }
    }

    /**
     * @param product
     * @param currentPersonMoneyAmount
     * @return
     */
    @Override
    public Product[] getPersonProductToPackage(Product product, Double currentPersonMoneyAmount) {
        return new Product[0];
    }

    /**
     * @param productName
     * @return
     */
    @Override
    public String getProductName(String productName) {
        try {
            if (Objects.equals(productName, "")) {
                throw new IllegalArgumentException("Имя продукта не может быть пустой строкой!");
            }

            return productName;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException("Имя продукта не может быть пустой строкой!", error);
        }
    }

    /**
     * @param coast
     * @return
     */
    @Override
    public Double getProductCoast(Double coast) {
        try {
            if (coast < 0) {
                throw new IllegalArgumentException("Цена не может быть отрицательным числом!");
            }

            return coast;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException("Цена не может быть отрицательным числом!", error);
        }
    }

}
