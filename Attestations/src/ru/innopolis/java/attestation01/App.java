package ru.innopolis.java.attestation01;

import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.repository.UsersRepositoryImpl;
import ru.innopolis.java.attestation01.utils.Helpers.DataHandlerParserHelper;

public class App {
    public static void main(String[] args) {
        UsersRepositoryImpl usersMethodsStore = new UsersRepositoryImpl();

        User newUser = DataHandlerParserHelper.createNewUser(
                "f5asfgsf9-4b3b-8a3465-c424ed2|2021-11-21T12:14:16.123|Gerar|7d89ghs|7d89ghs|Протопопов|Жерар|Жерарович|35|true"
        );
        User updatedUser = DataHandlerParserHelper.createNewUser(
                "f5asfgsf9-4b3b-8a3465-c424ed2|2021-11-21T12:14:16.123|Gena|7d89ghs|7d89ghs|СменилФамилиюИИмя|Геннадий|Жерарович|35|true"
        );

        System.out.println("Коллекция до: " + usersMethodsStore.findAll());
        System.out.println("Поиск пользователя по id: " + usersMethodsStore.findById("f5asdgsf9-4b3b-8a65-4353534dffb9d2"));
        usersMethodsStore.deleteById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");
        System.out.println("Коллекция после удалению пользователя по id: " + usersMethodsStore.findAll());
        usersMethodsStore.deleteAll();
        System.out.println("Коллекция после очистки файла: " + usersMethodsStore.findAll());
        usersMethodsStore.create(newUser);
        System.out.println("Коллекция после добавления нового пользователя: " + usersMethodsStore.findAll());
        usersMethodsStore.update(updatedUser);
        System.out.println("Коллекция после обновления пользователя: " + usersMethodsStore.findAll());
    }
}
