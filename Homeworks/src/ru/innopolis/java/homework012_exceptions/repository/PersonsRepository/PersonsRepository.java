package ru.innopolis.java.homework012_exceptions.repository.PersonsRepository;

import ru.innopolis.java.homework012_exceptions.model.Person;

public interface PersonsRepository {
    int SURNAME_INDEX = 0;
    int NAME_INDEX = 1;
    int PATRONYMIC_INDEX = 2;
    int BIRTH_DATE_INDEX = 3;
    int PHONE_NUMBER_INDEX = 4;
    int GENDER_INDEX = 5;
    int AGE_INDEX = 6;
    int PERSONS_PARAMS_AMOUNT = 7;
    int FIRST_SYMBOL = 0;
    String FORBIDDEN_PHONE_NUMBER_FIRST_SYMBOL = "0";
    String FEMALE = "f";
    String MALE = "m";
    Integer GENDER_STRING_LENGTH = 1;
    Integer AGE_EDGE_VALUE = 0;
    String OPEN_BRACKET = "<";
    String CLOSE_BRACKET = ">";
    String FILE_NAME_PARTS_DIVIVDER = "/";
    String OUTPUT_INFO_FOLDER_PATH = "src/ru/innopolis/java/homework012_exceptions/data";
    String SURNAME_NAME_PATRONYMIC_REQUIREMENTS = "^[a-zA-Za-zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_]{1,20}$";
    String BIRTH_DATE_REQUIREMENTS = "([0-9]{2}).([0-9]{2}).([0-9]{4})";

    String[] getParsedInfoString(String infoString) throws Exception;
    String getSurName(String[] infoStringCollection) throws Exception;
    String getName(String[] infoStringCollection) throws Exception;
    String getPatronymic(String[] infoStringCollection) throws Exception;
    String getBirthDate(String[] infoStringCollection) throws Exception;
    Integer getPhoneNumber(String[] infoStringCollection) throws Exception;
    String getPersonsGender(String[] infoStringCollection) throws Exception;
    Integer getAge(String[] infoStringCollection) throws Exception;
    Person getNewPersonEntityFromTheParsedInfoString(String[] infoStringCollection) throws Exception;
    String getPersonInfoStringFromTheEntity(Person personEntity);
    String getSpecificPersonsParamWrappedString(String somePersonsProp);
    String getSpecificPersonEntityOutputFilePath(String surName);
    void setWriteTheModifiedPersonStringIntoTheFile(String personsInfoString) throws Exception;
}
