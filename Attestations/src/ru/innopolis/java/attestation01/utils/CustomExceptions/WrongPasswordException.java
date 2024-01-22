package ru.innopolis.java.attestation01.utils.CustomExceptions;

public class WrongPasswordException extends IllegalArgumentException {
    static final String MESSAGE = "Пароль имеет неверный формат!";
    public WrongPasswordException() {
        super(MESSAGE);
    }
}
