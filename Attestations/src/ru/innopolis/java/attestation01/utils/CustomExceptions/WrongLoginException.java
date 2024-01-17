package ru.innopolis.java.attestation01.utils.CustomExceptions;

public class WrongLoginException extends IllegalArgumentException {
    public WrongLoginException(String errorMessage) {
        super(errorMessage);
    }
    public WrongLoginException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
