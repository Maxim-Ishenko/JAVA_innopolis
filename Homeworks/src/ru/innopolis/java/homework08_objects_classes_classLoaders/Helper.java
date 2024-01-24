package ru.innopolis.java.homework08_objects_classes_classLoaders;

import ru.innopolis.java.homework08_objects_classes_classLoaders.utils.InputDataHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Helper {
    private final String INITIAL_STRING_VALUE = "";
    private final String PERSON_NAME_SIBSTRING = "name: ";
    private final String PERSON_MONEY_AMOUNT_SUBSTRING = "moneyAmount: ";
    private final String PRODUCT_NAME_SIBSTRING = "productName: ";
    private final String PRODUCT_COAST_SIBSTRING = "coast: ";
    private final String PRODUCTS_PERSONS_INFO_ITEMS_SEPARATOR = ", ";
    private final double INITIAL_COAST_MONEY_AMOUNT = 0;
    private final int PERSON_NAME_SUBSTRING_OFFSET = 6;
    private final int PERSON_MONEY_AMOUNT_SUBSTRING_OFFSET = 13;
    private final int PRODUCT_NAME_SUBSTRING_OFFSET = 13;
    private final int PRODUCT_COAST_SUBSTRING_OFFSET = 7;

    public Helper() {}

    public List<String> setCollectionFromTheFile(String path) {
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(path))) {
            String textLine;

            while((textLine = bufferReader.readLine()) != null) {
                resultString.append(textLine);
                resultString.append("\n");
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return List.of(resultString.toString().trim().split("\n"));
    }

    public List<Person> setPersonsCollectionFormTheString (List<String> personsCollectionString) {
        InputDataHandler inputDataHandler = new InputDataHandler();
        List<Person> personsCollection = new ArrayList<>(List.of());

        for (String personInfo: personsCollectionString) {
            String name = INITIAL_STRING_VALUE;
            double moneyAmount = INITIAL_COAST_MONEY_AMOUNT;

            String[] personInfoArray = personInfo.trim().split(PRODUCTS_PERSONS_INFO_ITEMS_SEPARATOR);

            for (String person: personInfoArray) {
                if (person.contains(PERSON_NAME_SIBSTRING)) {
                    name = person.substring(person.indexOf(PERSON_NAME_SIBSTRING) + PERSON_NAME_SUBSTRING_OFFSET);
                } else if (person.contains(PERSON_MONEY_AMOUNT_SUBSTRING)) {
                    String moneyAmountString = person.substring(person.indexOf(PERSON_MONEY_AMOUNT_SUBSTRING) + PERSON_MONEY_AMOUNT_SUBSTRING_OFFSET);

                    System.out.println("moneyAmountString: " + moneyAmountString);
                    moneyAmount = inputDataHandler.validateNumber(moneyAmountString);
                }
            }

            Person newPerson = new Person(name, moneyAmount);

            personsCollection.add(newPerson);
        }

        return personsCollection;
    }

    public List<Product> setProductsCollectionFormTheString (List<String> productsCollectionString) throws IllegalArgumentException, InputMismatchException {
        List<Product> productsCollection = new ArrayList<>(List.of());

        for (String productInfo: productsCollectionString) {
            String productName = INITIAL_STRING_VALUE;
            double coast = INITIAL_COAST_MONEY_AMOUNT;

            String[] productInfoArray = productInfo.trim().split(PRODUCTS_PERSONS_INFO_ITEMS_SEPARATOR);

            for (String product: productInfoArray) {
                if (product.contains(PRODUCT_NAME_SIBSTRING)) {
                    productName = product.substring(product.indexOf(PRODUCT_NAME_SIBSTRING) + PRODUCT_NAME_SUBSTRING_OFFSET);
                } else if (product.contains(PRODUCT_COAST_SIBSTRING)) {
                    String coastString = product.substring(product.indexOf(PRODUCT_COAST_SIBSTRING) + PRODUCT_COAST_SUBSTRING_OFFSET);
                    coast = Double.parseDouble(coastString);
                }
            }

            Product newProduct = new Product(productName, coast);

            productsCollection.add(newProduct);
        }

        return productsCollection;
    }

    public void setWriteTheResultsToTheFile(String resultString, String fileName) {
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferWriter.append("\n");
            bufferWriter.append(resultString);

            bufferWriter.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
