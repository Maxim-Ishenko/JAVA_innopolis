package ru.innopolis.java.attestation01.utils.CustomExceptions;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
    public WrongPasswordException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
