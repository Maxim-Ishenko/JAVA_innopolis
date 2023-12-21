package ru.innopolis.java.homework012_exceptions.repository.ScannerHelperRepository;

import java.util.Scanner;

public class ScannerHelperRepositoryImpl implements IScannerHelperRepository {

    public ScannerHelperRepositoryImpl() {}

    @Override
    public String getThePersonsDataStringFromConsole(Scanner scanner) {
        System.out.println(
                "Введите данные пользователя в указанном формате: " + "\n" +
                "Фамилия Имя Отчество датарождения номертелефона пол возраст" + "\n" +
                "Форматы данных: " + "\n" +
                "• фамилия, имя, отчество — строка; " + "\n" +
                "• датарождения — строка формата dd.mm.yyyy; " +"\n" +
                "• номертелефона - целое беззнаковое число без форматирования; " + "\n" +
                "• пол - символ латиницей f или m; " + "\n" +
                "• возраст — целое число" + "\n"
                );

        return scanner.nextLine();
    }
}
