package ru.innopolis.java.attestation01.utils.CustomExceptions;

public class UserNotFoundException extends IllegalArgumentException {
    String MESSAGE = "Пользователя с заданным идентификатором не существует";
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

