package ru.innopolis.java.homework012_exceptions.test;


import ru.innopolis.java.homework012_exceptions.repository.PersonsRepository.PersonsRepositoryImpl;
import ru.innopolis.java.homework012_exceptions.repository.ScannerHelperRepository.ScannerHelperRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ScannerHelperRepositoryImpl scannerHelper = new ScannerHelperRepositoryImpl();
        PersonsRepositoryImpl personsMethods = new PersonsRepositoryImpl();

        String personsInfoString = scannerHelper.getThePersonsDataStringFromConsole(scanner);

        personsMethods.setWriteTheModifiedPersonStringIntoTheFile(personsInfoString);

        scanner.close();
    }
}
