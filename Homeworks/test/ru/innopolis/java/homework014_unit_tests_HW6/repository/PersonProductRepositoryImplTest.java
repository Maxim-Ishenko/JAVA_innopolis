package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonProductRepositoryImplTest {
    final String WRONG_NAME_EXCEPTION_MESSAGE = "Имя пользователя не может быть пустой строкой!";
    final String WRONG_MONEY_AMOUNT_EXCEPTION_MESSAGE = "Сумма не может быть отрицательным числом!";
    final String WRONG_PRODUCT_NAME_EXCEPTION_MESSAGE = "Имя продукта не может быть пустой строкой!";
    final String WRONG_PRODUCT_COAST_EXCEPTION_MESSAGE = "Цена не может быть отрицательным числом!";


    @BeforeAll
    static void setUp() {
//        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();

    }

//    @BeforeEach
//    void setUp() {
////        Person testedPerson = new Person();
//
//    }

    @AfterEach
    void tearDown() {
    }

//    @ParameterizedTest(name = "{index} - {0} is a ...")
    @ParameterizedTest(name = "Класс Person создается с корректными данными")
    @CsvSource({
            "Павел, 30000",
            "Анна, 3500",
            "Борис, 40",
//            "'', 30000",
//            "Анна, ''",
//            "'', ''"
    })
//    void createNewPerson(String name, double moneyAmount) {
    void createNewPersonWithCorrectData(String name, double moneyAmount) {
//        Person testedPerson = new Person(name, moneyAmount);
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();
        Person testedPerson = testedMethodsStore.createNewPerson(name, moneyAmount);

//        Person testedPerson;
//        IllegalArgumentException thrown = assertThrows(Illeg
//        alArgumentException.class, () -> {
            //Code under test
//        });
//        Executable executableTrue = () -> {
//            if (!Objects.equals(name, "") && moneyAmount >= 0) {
//                testedPerson = new Person(name, moneyAmount);
//            }
//            }
//        }
//        assertNotNull(name);
//        assertTrue(age > 0);

//        Person personMock = Mockito.mock(Person.class);


        assertEquals(name, testedPerson.getName());
        assertEquals(moneyAmount, testedPerson.getMoneyAmount());
    }
    @ParameterizedTest(name = "Проверка исключения при вводе некорректного имени")
    @CsvSource({"'', 30000"})
    void createNewPersonWithWrongName(String name, double moneyAmount) {
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testedMethodsStore.createNewPerson(name, moneyAmount);
        });

        assertEquals(WRONG_NAME_EXCEPTION_MESSAGE, thrown.getMessage());
    }
    @ParameterizedTest(name = "Проверка исключения при вводе некорректного количества денег")
    @CsvSource({"Анна, -1"})
    void createNewPersonWithWrongMoneyAmount(String name, double moneyAmount) {
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testedMethodsStore.createNewPerson(name, moneyAmount);
        });

        assertEquals(WRONG_MONEY_AMOUNT_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @ParameterizedTest(name = "Продукт создается с корректными данными")
    @CsvSource({"Пиво, 3.50", "Хлеб, 3.20", "Вода, 1.00"})
    void createNewProductWithCorrectData(String name, double coast) {
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();
        Product testedProduct = testedMethodsStore.createNewProduct(name, coast);

        assertEquals(name, testedProduct.getProductName());
        assertEquals(coast, testedProduct.getCoast());
    }

    @ParameterizedTest(name = "Проверка исключения при оздании продукта с некорректным именем")
    @CsvSource({"'', 3.50"})
    void createNewProductWithWrongName(String name, double coast) {
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testedMethodsStore.createNewProduct(name, coast);
        });

        assertEquals(WRONG_PRODUCT_NAME_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @ParameterizedTest(name = "Проверка исключения при оздании продукта с некорректной ценой")
    @CsvSource({"Пиво, -8"})
    void createNewProductWithWrongCoast(String name, double coast) {
        PersonProductRepositoryImpl testedMethodsStore = new PersonProductRepositoryImpl();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            testedMethodsStore.createNewProduct(name, coast);
        });

        assertEquals(WRONG_PRODUCT_COAST_EXCEPTION_MESSAGE, thrown.getMessage());
    }
}
//    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
//        inputCustomerData.purchaseProducts(personArrayList, productsArrayList);
//    });