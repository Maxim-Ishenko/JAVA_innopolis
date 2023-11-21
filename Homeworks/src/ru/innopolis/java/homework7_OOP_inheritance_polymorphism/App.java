package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Product> productsCollection = new ArrayList<>();

        boolean isEnoughFlag = false;

        while (!isEnoughFlag) {
            System.out.println("Выберите тип добавляемого продукта: 0 - обычный, 1 - со скидкой: ");
            String productType = scanner.nextLine();

            Product newProduct;

            if (productType.equals("0")) {
                newProduct = new Product(scanner);
                scanner.nextLine();
            } else if (productType.equals("1")) {
                newProduct = new DiscountProduct(scanner);
                scanner.nextLine();
            } else {
                throw new Exception("Доступны только два типа продукта - обычный (0) и со скидкой (1)!");
            }

            productsCollection.add(newProduct);

            System.out.println(
                "Если вы желаете прервать выполнение программы -" +
                " введите ключевое слово END." + "\n" +
                "Для продолжения выполнения программы нажмите Enter"
            );
            String deprivateCommand = scanner.nextLine();

            if (Objects.equals(deprivateCommand.trim(), "END")) {
                isEnoughFlag = true;
            }
        }

        System.out.println("productsCollection: " + productsCollection);

        scanner.close();
    }
}

