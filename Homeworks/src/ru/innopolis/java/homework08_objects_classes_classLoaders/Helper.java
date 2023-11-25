package ru.innopolis.java.homework08_objects_classes_classLoaders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Helper {
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

    public List<Person> setPersonsCollectionFormTheString (List<String> personsCollectionString) throws Exception {
        List<Person> personsCollection = new ArrayList<>(List.of());

        for (String personInfo: personsCollectionString) {
            String name = "";
            double moneyAmount = 0;

            String[] personInfoArray = personInfo.trim().split(", ");

            for (String person: personInfoArray) {
                if (person.contains("name: ")) {
                    name = person.substring(person.indexOf("name: ") + 6);
                } else if (person.contains("moneyAmount: ")) {
                    String moneyAmountString = person.substring(person.indexOf("name: ") + 13);
                    moneyAmount = Double.parseDouble(moneyAmountString);
                }
            }

            Person newPerson = new Person(name, moneyAmount);

            personsCollection.add(newPerson);
        }

        return personsCollection;
    }

    public List<Product> setProductsCollectionFormTheString (List<String> productsCollectionString) throws Exception {
        List<Product> productsCollection = new ArrayList<>(List.of());

        for (String productInfo: productsCollectionString) {
            String productName = "";
            double coast = 0;

            String[] productInfoArray = productInfo.trim().split(", ");

            for (String product: productInfoArray) {
                if (product.contains("productName: ")) {
                    productName = product.substring(product.indexOf("productName: ") + 13);
                } else if (product.contains("coast: ")) {
                    String coastString = product.substring(product.indexOf("coast: ") + 7);
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
