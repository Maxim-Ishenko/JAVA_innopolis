package ru.innopolis.java.homework012Addition.test;

import ru.innopolis.java.homework012Addition.model.User;

/*
    По заданию указано создать только класс User чисто для обработки кредов.
    Поэтому не стал заводить интерфейсы, плюс основной метод по заданию должен был быть статическим. Мне показалось,
    что требуется сделать узкоспециализированный класс с инкапсулированной логикой.
    Добавил, тем не менее, запись логина/пароля в приватные поля, если все проверки прошли успешно.
 */

public class Main {
    public static void main(String[] args) {
        User gennadij = new User();
        gennadij.setAuthorizationCredentials("Gena", "fsfs3", "fsfs3");
    }
}
