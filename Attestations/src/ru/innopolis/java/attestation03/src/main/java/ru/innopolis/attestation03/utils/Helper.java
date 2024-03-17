package ru.innopolis.attestation03.utils;

public class Helper {
    static String SPACE = " ";
    public static String getFullName(
            String lastName,
            String firstName,
            String patronymic
    ) {
        StringBuilder fullName = new StringBuilder();

        if (!lastName.isEmpty()) {
            fullName.append(lastName).append(SPACE);
        }

        if (!firstName.isEmpty()) {
            fullName.append(firstName).append(SPACE);
        }

        if (!patronymic.isEmpty()) {
            fullName.append(patronymic);
        }

        return fullName.toString();
    }
}
