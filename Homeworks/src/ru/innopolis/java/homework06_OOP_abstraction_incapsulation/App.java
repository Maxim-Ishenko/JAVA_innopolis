package ru.innopolis.java.homework06_OOP_abstraction_incapsulation;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Person pavelAndreich = new Person("Павел Андреевич", 10000);
        Person annaPetrovna = new Person("Анна Петровна", 2000);
        Person boris = new Person("Борис", 10);

        Product bread = new Product("Хлеб", 40);
        Product milk = new Product("Молоко", 60);
        Product cake = new Product("Торт", 1000);
        Product cofee = new Product("Кофе растворимый", 879);
        Product spread = new Product("Масло", 150);
        List<Person> personsCollection = Arrays.asList(pavelAndreich, annaPetrovna, boris);
        List<Product> productsCollection = Arrays.asList(bread, milk, cake, cofee, spread);

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

                if (Objects.equals(deprivateCommand.trim(), "END")) {
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
                System.out.println(person.getName() + " - " + "Ничего не куплено");
            } else {
                System.out.println(person.getName() + " - " + currentPersonsProductsList);
            }
        }

        scanner.close();
    }
}

