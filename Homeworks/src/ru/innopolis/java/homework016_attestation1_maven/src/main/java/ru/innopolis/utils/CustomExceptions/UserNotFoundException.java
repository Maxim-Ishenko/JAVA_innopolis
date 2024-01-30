package ru.innopolis.utils.CustomExceptions;

public class UserNotFoundException extends IllegalArgumentException {
    static final String MESSAGE = "Пользователя с заданным идентификатором не существует";
    public UserNotFoundException() {
        super(MESSAGE);
    }
}

