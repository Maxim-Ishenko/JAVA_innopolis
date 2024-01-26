package ru.innopolis.java.homework014_unit_tests_HW6.repository;

import org.junit.jupiter.api.*;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Person;
import ru.innopolis.java.homework014_unit_tests_HW6.model.Product;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonProductRepositoryImplTest {
    private final String WRONG_NAME_EXCEPTION_MESSAGE = "Имя пользователя не может быть пустой строкой!";
    private final String WRONG_MONEY_AMOUNT_EXCEPTION_MESSAGE = "Сумма не может быть отрицательным числом!";
    private final String WRONG_PRODUCT_NAME_EXCEPTION_MESSAGE = "Имя продукта не может быть пустой строкой!";
    private final String WRONG_PRODUCT_COAST_EXCEPTION_MESSAGE = "Цена не может быть отрицательным числом!";
    private final String TEST_CORRECT_PERSON_NAME = "Зинаида";
    private final String TEST_WRONG_PERSON_NAME = "";
    private final Double TEST_CORRECT_PERSON_MONEY_AMOUNT = 12.0;
    private final Double TEST_WRONG_PERSON_MONEY_AMOUNT = -4.0;
    private final String TEST_CORRECT_PRODUCT_NAME = "Вафля";
    private final String TEST_WRONG_PRODUCT_NAME = "";
    private final Double TEST_CORRECT_PRODUCT_COAST = 150.1;
    private final Double TEST_WRONG_PRODUCT_COAST = -2.0;
    private final PersonProductRepositoryImpl PERSON_PRODUCT_METHODS_STORE = new PersonProductRepositoryImpl();

    @Test
    @DisplayName("Тест создания пользователя с корректными данными")
    void createNewPerson() {
        Person testCorrectPerson =
                PERSON_PRODUCT_METHODS_STORE.createNewPerson(
                        TEST_CORRECT_PERSON_NAME, TEST_CORRECT_PERSON_MONEY_AMOUNT
                );

        assertNotNull(testCorrectPerson);
        assertEquals(TEST_CORRECT_PERSON_NAME, testCorrectPerson.getName());
        assertEquals(TEST_CORRECT_PERSON_MONEY_AMOUNT, testCorrectPerson.getMoneyAmount());
    }
    @Test
    @DisplayName("Тест создания пользователя с некорректным именем")
    void createNewPersonWithWrongName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.createNewPerson(
                    TEST_WRONG_PERSON_NAME, TEST_CORRECT_PERSON_MONEY_AMOUNT
            );
        });
        assertEquals(WRONG_NAME_EXCEPTION_MESSAGE, thrown.getMessage());
    }
    @Test
    @DisplayName("Тест создания пользователя с некорректной суммой")
    void createNewPersonWithWrongMoneyAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.createNewPerson(
                    TEST_CORRECT_PERSON_NAME, TEST_WRONG_PERSON_MONEY_AMOUNT
            );
        });
        assertEquals(WRONG_MONEY_AMOUNT_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    @DisplayName("Тест создания продукта с корректными данными")
    void createNewProduct() {
        Product testCorrectProduct =
                PERSON_PRODUCT_METHODS_STORE.createNewProduct(
                        TEST_CORRECT_PRODUCT_NAME,
                        TEST_CORRECT_PRODUCT_COAST
                );

        assertNotNull(testCorrectProduct);
        assertEquals(TEST_CORRECT_PRODUCT_NAME, testCorrectProduct.getProductName());
        assertEquals(TEST_CORRECT_PRODUCT_COAST, testCorrectProduct.getCoast());
    }
    @Test
    @DisplayName("Тест создания продукта с некорректным названием")
    void createNewProductWithWrongName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.createNewProduct(
                    TEST_WRONG_PRODUCT_NAME, TEST_CORRECT_PRODUCT_COAST
            );
        });
        assertEquals(WRONG_PRODUCT_NAME_EXCEPTION_MESSAGE, thrown.getMessage());
    }
    @Test
    @DisplayName("Тест создания продукта с некорректной ценой")
    void createNewProductWithWrongCoast() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.createNewProduct(
                    TEST_CORRECT_PRODUCT_NAME, TEST_WRONG_PRODUCT_COAST
            );
        });
        assertEquals(WRONG_PRODUCT_COAST_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    @DisplayName("Тест формата имени пользователя")
    void getPersonName() {
        String correctTestName = PERSON_PRODUCT_METHODS_STORE.getPersonName(TEST_CORRECT_PERSON_NAME);

        assertEquals(correctTestName, TEST_CORRECT_PERSON_NAME);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.getPersonName(TEST_WRONG_PERSON_NAME);
        });
        Assertions.assertEquals(
                WRONG_NAME_EXCEPTION_MESSAGE,
                thrown.getMessage()
        );
    }

    @Test
    @DisplayName("Тест формата денежной суммы пользователя")
    void getPersonMoneyAmount() {
        Double correctTestMoneyAmount =
                PERSON_PRODUCT_METHODS_STORE.getPersonMoneyAmount(TEST_CORRECT_PERSON_MONEY_AMOUNT);

        assertEquals(correctTestMoneyAmount, TEST_CORRECT_PERSON_MONEY_AMOUNT);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.getPersonMoneyAmount(TEST_WRONG_PERSON_MONEY_AMOUNT);
        });
        Assertions.assertEquals(
                WRONG_MONEY_AMOUNT_EXCEPTION_MESSAGE,
                thrown.getMessage()
        );
    }

    @Test
    @DisplayName("Тест формата названия продукта")
    void getProductName() {
        String correctTestName = PERSON_PRODUCT_METHODS_STORE.getProductName(TEST_CORRECT_PRODUCT_NAME);

        assertEquals(correctTestName, TEST_CORRECT_PRODUCT_NAME);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.getProductName(TEST_WRONG_PRODUCT_NAME);
        });
        Assertions.assertEquals(
                WRONG_PRODUCT_NAME_EXCEPTION_MESSAGE,
                thrown.getMessage()
        );
    }

    @Test
    @DisplayName("Тест формата цены продукта")
    void getProductCoast() {
        Double correctTestCoast = PERSON_PRODUCT_METHODS_STORE.getProductCoast(TEST_CORRECT_PRODUCT_COAST);

        assertEquals(correctTestCoast, TEST_CORRECT_PRODUCT_COAST);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PERSON_PRODUCT_METHODS_STORE.getProductCoast(TEST_WRONG_PRODUCT_COAST);
        });
        Assertions.assertEquals(
                WRONG_PRODUCT_COAST_EXCEPTION_MESSAGE,
                thrown.getMessage()
        );
    }
}
