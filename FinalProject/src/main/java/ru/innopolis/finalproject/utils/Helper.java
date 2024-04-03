package ru.innopolis.finalproject.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(obj);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
