package ru.innopolis.java.homework013_compilation_runtime_exceptions.utils;

public class InputDataHandler {
    public static Long parseCount(String inputString) throws IllegalArgumentException {
        try {
            return Long.parseLong(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public static Double parseNumber(String inputString) throws IllegalArgumentException {
        try {
            return Double.parseDouble(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public static Long validateCount(String inputString) throws IllegalArgumentException {
        try {
            return parseCount(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }

    public static Double validateNumber(String inputString) throws IllegalArgumentException {
        try {
            return parseNumber(inputString);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Невалидное значение", err);
        }
    }
}
