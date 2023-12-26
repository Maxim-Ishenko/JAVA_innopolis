package ru.innopolis.java.homework012Addition.repository;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
    public WrongPasswordException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
