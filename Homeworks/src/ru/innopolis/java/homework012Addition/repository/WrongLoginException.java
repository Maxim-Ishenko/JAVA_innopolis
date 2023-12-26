package ru.innopolis.java.homework012Addition.repository;

public class WrongLoginException extends IllegalArgumentException {
    public WrongLoginException(String errorMessage) {
        super(errorMessage);
    }
    public WrongLoginException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
