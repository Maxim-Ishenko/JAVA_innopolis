package ru.innopolis;

import ru.innopolis.model.User;
import ru.innopolis.repository.UsersRepositoryImpl;
import ru.innopolis.utils.Helpers.DataHandlerParserHelper;

public class App {
    public static void main(String[] args) {
        UsersRepositoryImpl usersMethodsStore = new UsersRepositoryImpl();

        System.out.println("Коллекция до: " + usersMethodsStore.findAll());
//        System.out.println("Поиск пользователя по id: " + usersMethodsStore.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));
//        usersMethodsStore.deleteById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");
//        System.out.println("Коллекция после удалению пользователя по id: " + usersMethodsStore.findAll());
//        usersMethodsStore.deleteAll();
//        System.out.println("Коллекция после очистки файла: " + usersMethodsStore.findAll());
//        usersMethodsStore.create(newUser);
//        System.out.println("Коллекция после добавления нового пользователя: " + usersMethodsStore.findAll());
//        usersMethodsStore.update(updatedUser);
//        System.out.println("Коллекция после обновления пользователя: " + usersMethodsStore.findAll());
    }
}
