package ru.innopolis.java.attestation01.utils.CustomExceptions;

public class WrongLoginException extends IllegalArgumentException {
    static final String MESSAGE = "Логин имеет неверный формат!";
    public WrongLoginException() {
        super(MESSAGE);
    }
}
