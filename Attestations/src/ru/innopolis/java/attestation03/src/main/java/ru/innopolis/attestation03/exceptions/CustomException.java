package ru.innopolis.attestation03.exceptions;

import ru.innopolis.attestation03.enums.ResultsMessages;

public class CustomException extends IllegalArgumentException {

    public CustomException(ResultsMessages resultsMessages) {
        super(resultsMessages.getMessage());
    }

    public CustomException(String message) {
        super(message);
    }
}
