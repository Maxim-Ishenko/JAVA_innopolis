package ru.innopolis.java.attestation01.utils.Helpers;

import java.util.Scanner;

public class ScannerWorkHelper {
   public static String getTheUsersDataStringFromConsole(Scanner scanner) {
        System.out.println(
                "Введите данные пользователя в указанном формате: " + "\n" +
                "id localDateTime login password confirmPassword surname name patronymic age isWorker" + "\n" +
                "Форматы данных: " + "\n" +
                "• id – гарантированно уникальный ID пользователя. Состоит избукв и цифр; " + "\n" +
                "• localDateTime — строка формата yyyy-mm-ddThh:mm:ss.mss; " +"\n" +
                "• login - не может быть только из цифр, содержит буквы,цифры, знак подчеркивания, меньше 20 символов; " + "\n" +
                "• password - не может быть только из букв, содержит буквы, цифры, знак подчеркивания, меньше 20символов; " + "\n" +
                "• confirmPassword - аналогично с password; " + "\n" +
                "• name, surname - строка, состоит только из букв; " + "\n" +
                "• patronymic - строка, состоит только из букв, может отсутствовать; " + "\n" +
                "• age - целое число, может отсутствовать; " + "\n" +
                "• isWorker - является ли сотрудником предприятия, по умолчанию false. " + "\n"
        );

        return scanner.nextLine();
    }
}
