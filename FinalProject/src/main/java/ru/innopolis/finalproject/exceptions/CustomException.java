package ru.innopolis.finalproject.exceptions;

import ru.innopolis.finalproject.enums.ResultsMessages;

public class CustomException extends IllegalArgumentException {

    public CustomException(ResultsMessages resultsMessages) {
        super(resultsMessages.getMessage());
    }

    public CustomException(String message) {
        super(message);
    }
}
