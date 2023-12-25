package ru.innopolis.java.homework012_exceptions.repository.PersonsRepository;

import ru.innopolis.java.homework012_exceptions.model.Person;
import ru.innopolis.java.homework012_exceptions.utils.Helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class PersonsRepositoryImpl implements PersonsRepository {
    public PersonsRepositoryImpl() {}
    @Override
    public String[] getParsedInfoString(String infoString) throws IllegalArgumentException {
        String[] personsInfo = infoString.split(" ");

        if (personsInfo.length < PERSONS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит не все необходимые параметры!");
        }

        if (personsInfo.length > PERSONS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит больше параметров, чем требуется!");
        }

        return personsInfo;
    }

    @Override
    public String getSurName( String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String surName = infoStringCollection[SURNAME_INDEX].trim();

            if (!surName.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException(
                        "Фамилия должна быть в пределах 1-20 знаков по длине, состоять только из букв русского " +
                                "и/или английского алфавита, цифр, дефиса или нижнего подчеркивания!"
                );
            }

            return surName;
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public String getName(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String name = infoStringCollection[NAME_INDEX].trim();

            if (!name.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException(
                        "Имя должно быть в пределах 1-20 знаков по длине, состоять только из букв русского " +
                                "и/или английского алфавита, цифр, дефиса или нижнего подчеркивания!"
                );
            }

            return name;
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public String getPatronymic(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String patronymic = infoStringCollection[PATRONYMIC_INDEX].trim();

            if (!patronymic.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException(
                        "Отчество должно быть в пределах 1-20 знаков по длине, состоять только из букв русского " +
                                "и/или английского алфавита, цифр, дефиса или нижнего подчеркивания!"
                );
            }

            return patronymic;
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public String getBirthDate(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String birthDateString = infoStringCollection[BIRTH_DATE_INDEX].trim();

            if (!birthDateString.matches(BIRTH_DATE_REQUIREMENTS)) {
                throw new IllegalArgumentException("Дата рождения имеет неверный формат!");
            }

            return birthDateString;
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public Integer getPhoneNumber(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String phoneNumber = infoStringCollection[PHONE_NUMBER_INDEX].trim();

            if (Objects.equals(
                    String.valueOf(phoneNumber.trim().charAt(FIRST_SYMBOL)), FORBIDDEN_PHONE_NUMBER_FIRST_SYMBOL)
            ) {
                throw new IllegalArgumentException("Номер телефона не может начинаться с нуля!");
            }

            return Integer.valueOf(phoneNumber);
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public String getPersonsGender(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String gender = infoStringCollection[GENDER_INDEX].trim();

            if (gender.length() != GENDER_STRING_LENGTH || (!Objects.equals(gender, MALE) && !Objects.equals(gender, FEMALE))) {
                throw new IllegalArgumentException("Гендер должен быть задан одной из букв: f - female, m - male!");
            }

            return gender;
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public Integer getAge(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String ageString = infoStringCollection[AGE_INDEX].trim();

            if (Integer.parseInt(ageString) <= AGE_EDGE_VALUE) {
                throw new IllegalArgumentException("Возраст должен быть положительным целым числом!");
            }

            return Integer.valueOf(ageString);
        }

        throw new NullPointerException(
                "Аргумент infoStringCollection метода getSurName (базовая строка с параметрами) не может быть null!"
        );
    }

    @Override
    public Person getNewPersonEntityFromTheParsedInfoString(String[] infoStringCollection)
            throws IllegalArgumentException, NullPointerException {
            return new Person(
                    getSurName(infoStringCollection),
                    getName(infoStringCollection),
                    getPatronymic(infoStringCollection),
                    getBirthDate(infoStringCollection),
                    getPhoneNumber(infoStringCollection),
                    getPersonsGender(infoStringCollection),
                    getAge(infoStringCollection)
            );
    }

    @Override
    public String getPersonInfoStringFromTheEntity(Person personEntity) {
        return getSpecificPersonsParamWrappedString(personEntity.getSurname())
                + getSpecificPersonsParamWrappedString(personEntity.getName())
                + getSpecificPersonsParamWrappedString(personEntity.getPatronymic())
                + getSpecificPersonsParamWrappedString(personEntity.getBirthDate())
                + getSpecificPersonsParamWrappedString(String.valueOf(personEntity.getPhoneNumber()))
                + getSpecificPersonsParamWrappedString(personEntity.getGender());
    }

    @Override
    public String getSpecificPersonsParamWrappedString(String somePersonsProp) {
        return OPEN_BRACKET + somePersonsProp + CLOSE_BRACKET;
    }

    @Override
    public String getSpecificPersonEntityOutputFilePath(String surName) {
        return OUTPUT_INFO_FOLDER_PATH + FILE_NAME_PARTS_DIVIVDER + surName;
    }

    @Override
    public void setWriteTheModifiedPersonStringIntoTheFile(String personsInfoString)
            throws IllegalArgumentException, NullPointerException {
        String[] personParamsList = getParsedInfoString(personsInfoString);
        Person personEntity = getNewPersonEntityFromTheParsedInfoString(personParamsList);
        Helper helperMethods = new Helper();

        helperMethods.setWriteThePersonToTheFile(
                getPersonInfoStringFromTheEntity(personEntity),
                getSpecificPersonEntityOutputFilePath(personEntity.getSurname())
        );
    }
}
