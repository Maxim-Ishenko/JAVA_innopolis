package ru.innopolis.java.homework08_objects_classes_classLoaders;

import java.io.IOException;
import java.util.*;

public class App {
    static String END = "END";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String personsStorePath = "src/ru/innopolis/java/homework08_objects_classes_classLoaders/data/persons.txt";
        String productsStorePath = "src/ru/innopolis/java/homework08_objects_classes_classLoaders/data/products.txt";
        String outputFilePath = "src/ru/innopolis/java/homework08_objects_classes_classLoaders/data/output.txt";

        Helper helper = new Helper();
        List<String> personsCollectionString = helper.setCollectionFromTheFile(personsStorePath);
        List<String> productsCollectionString = helper.setCollectionFromTheFile(productsStorePath);

        List<Person> personsCollection = helper.setPersonsCollectionFormTheString(personsCollectionString);
        List<Product> productsCollection = helper.setProductsCollectionFormTheString(productsCollectionString);

        boolean isEnoughFlag = false;

        for (int i = 0; i < productsCollection.size(); i++) {
            if (isEnoughFlag) break;

            for (int j = 0; j < personsCollection.size(); j++) {
                System.out.println(
                    "Если вы желаете прервать наполнение продуктовой корзины -" +
                        " введите ключевое слово END." + "\n" +
                        "Для продолжения выполнения программы нажмите Enter"
                );
                String deprivateCommand = scanner.nextLine();

                if (Objects.equals(deprivateCommand.trim(), END)) {
                    isEnoughFlag = true;
                    break;
                }
                ;

                Person currentPerson = personsCollection.get(j);
                Product currentProduct = productsCollection.get(i);
                currentPerson.setProductToPackage(currentProduct);

                if (
                    j == personsCollection.size() &&
                        i == productsCollection.size() &&
                        !isEnoughFlag
                ) {
                    isEnoughFlag = true;
                }
            }
        }

        for (Person person : personsCollection) {
            StringBuilder currentPersonsProductsList = new StringBuilder();

            for (Product productsPackageItem : person.getProductsPackage()) {
                if (currentPersonsProductsList.isEmpty()) {
                    currentPersonsProductsList.append(productsPackageItem.getProductName());
                } else {
                    currentPersonsProductsList.append(", ").append(productsPackageItem.getProductName());
                }
            }

            if (Objects.equals(currentPersonsProductsList.length(), 0)) {
                String resultString = person.getName() + " - " + "Ничего не куплено";
                helper.setWriteTheResultsToTheFile(resultString, outputFilePath);
            } else {
                String resultString = person.getName() + " - " + currentPersonsProductsList;
                helper.setWriteTheResultsToTheFile(resultString, outputFilePath);
            }
        }

        scanner.close();
    }
}

