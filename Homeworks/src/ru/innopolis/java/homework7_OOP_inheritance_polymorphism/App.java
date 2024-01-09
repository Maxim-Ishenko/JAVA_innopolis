package ru.innopolis.java.homework7_OOP_inheritance_polymorphism;

import java.util.*;

/* Пояснения:
1) Домашнее задание выполнял на баз 6 домашки. Но, так как она в отдельной ветке и еще не проверена и не вмержена -
скопировал файлы и доработки проводил уже в отдельном пакете. Пробовал смержить ветку 6 домашки в новую, но, поскольку
там сразу несколько домашек висят на проверке, то это тянет за собой кучу ненужных коммитов. Остановился на простом копировании.
2) Для первой задачи сделал ввод через консоль, пока пользователь не введт команду END,
так по ожидаемым результатам из условия  этот вариант больше подходит.
Для второй задачи ввод данных оставил таким же, как в 6 домашке - с помощью команды END мы можем прервать наполнение продуктового списка.
Согласно условию 6 домашки, как я понял, ожидалось именно такое поведение.
3) В условии не оговорено - как пользователь должен различать тип создаваемого продукта, поэтому я предоставил ему этот
выбор через консоль - путем ввода 0 или 1
4) Срок действия скидки сделал через LocalDate. Поэтому от пользователя требуется вводить дату в дефолтном формате
LocalDate (так сканнер возвращает тип строки) */

public class App {
    public static void main(String[] args) throws IllegalArgumentException, InputMismatchException {
        Scanner scanner = new Scanner(System.in);

        Children child = new Children("Борис", 10, 14);
        Adult adult = new Adult("Анна Петровна", 2000, 37);
        Pensioner pensioner = new Pensioner("Лаврентий Палыч", 300, 75);

        Product bread = new Product("Хлеб", 40);
        Product milk = new Product("Молоко", 60);
        DiscountProduct cake = new DiscountProduct("Торт", 1000, 15);
        DiscountProduct cofee = new DiscountProduct("Кофе растворимый", 879, 50);
        DiscountProduct spread = new DiscountProduct("Масло", 150, 32);

        List<Person> personsCollection = Arrays.asList(child, adult, pensioner);
        List<Product> productsCollection = Arrays.asList(bread, milk, cake, cofee, spread);

        boolean isEnoughFlag = false;

        for (int i = 0; i < productsCollection.size(); i++) {
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

