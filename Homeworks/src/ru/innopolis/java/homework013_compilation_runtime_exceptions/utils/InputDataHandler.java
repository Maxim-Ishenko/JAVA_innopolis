package ru.innopolis.java.homework013_compilation_runtime_exceptions.utils;

public class InputDataHandler {
    public InputDataHandler() {}
    public Long parseCount(String inputString) throws IllegalArgumentException {
        try {
            return (long) Integer.parseInt(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public Double parseNumber(String inputString) throws IllegalArgumentException {
        try {
            return Double.parseDouble(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public Long validateCount(String inputString) throws IllegalArgumentException {
        try {
            return parseCount(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public Double validateNumber(String inputString) throws IllegalArgumentException {
        try {
            return parseNumber(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }
}
