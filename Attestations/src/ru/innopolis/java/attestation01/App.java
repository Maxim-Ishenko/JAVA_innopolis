package ru.innopolis.java.attestation01;

import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.repository.UsersRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        UsersRepositoryImpl usersMethodsStore = new UsersRepositoryImpl();
//        String inputData = usersMethodsStore.readUsersFromTheFile();
//        ArrayList<User> usersCollection = usersMethodsStore.setUsersCollectionFromTheFile();

//        System.out.println("usersCollection before:" + usersCollection);
//        System.out.println("userInfoString" + usersMethodsStore.getUserParamsFromConsole());

        User newUser = usersMethodsStore.createNewUser("f5asfgsf9-4b3b-8a65-c424e129b9d2|2023-12-27T12:14:16.123|Fekla_123|7d89ghs|7d89ghs|Протопопов|AAA|BBB|35|true");
//        usersMethodsStore.create("newUser");
//usersMethodsStore.findById("f5asdgsf9-4b3b-8a65-4353534dffb9d2");
        System.out.println("newUser->" + newUser);
        usersMethodsStore.update(newUser);
//usersMethodsStore.deleteAll();
//        usersMethodsStore.deleteById("f5xcv3cc-4ac9-4b3b-8a67-c424e129b8k3");
//        System.out.println("usersCollection after:" + usersCollection);
//        System.out.println("usersCollection after:" + usersCollection);

//        System.out.println("AAA:" + Boolean.getBoolean("true"));

    }
}
//f5asfgsf9-4b3b-8a65-c424e129b9d2|2023-12-27T12:14:16.123|Fekla_123|7d89ghs|7d89ghs|DDD|AAA|BBB|35|true