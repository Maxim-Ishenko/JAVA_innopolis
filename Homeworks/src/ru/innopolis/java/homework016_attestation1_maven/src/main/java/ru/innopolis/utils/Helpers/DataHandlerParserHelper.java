package ru.innopolis.utils.Helpers;

import ru.innopolis.model.User;
import ru.innopolis.utils.CustomExceptions.WrongLoginException;
import ru.innopolis.utils.CustomExceptions.WrongPasswordException;

import java.io.InputStream;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ru.innopolis.repository.UsersRepository.*;

public class DataHandlerParserHelper {
    public static String getUserParamsFromConsole() {
        return ScannerWorkHelper.getTheUsersDataStringFromConsole(USERS_PARAMS_INPUT_NOTIFICATION_HELP);
    }

    public static String[] getParsedUserInfoString(String infoString) throws IllegalArgumentException {
        String[] usersInfo = infoString.split("\\|");

        if (usersInfo.length < USERS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит не все необходимые параметры!");
        }

        if (usersInfo.length > USERS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит больше параметров, чем требуется!");
        }

        return usersInfo;
    }

    public static User createNewUser() {
        String inputUserString = getUserParamsFromConsole();
        List<String> parsedInputUserParamsList = List.of(getParsedUserInfoString(inputUserString));

        return new User(
                getIdFromInfoString(parsedInputUserParamsList),
                LocalDateTime.parse(
                        getLocalDateTimeFromInfoString(parsedInputUserParamsList),
                        DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)
                ),
                getLoginFromInfoString(parsedInputUserParamsList),
                getPasswordFromInfoString(parsedInputUserParamsList),
                getConfirmPasswordFromInfoString(parsedInputUserParamsList),
                getSurNameFromInfoString(parsedInputUserParamsList),
                getNameFromInfoString(parsedInputUserParamsList),
                getPatronymicFromInfoString(parsedInputUserParamsList),
                getAgeFromInfoString(parsedInputUserParamsList),
                getIsWorkerFromInfoString(parsedInputUserParamsList)
        );
    }
    public static User createNewUser(String paramsString) {
        List<String> parsedInputUserParamsList = List.of(getParsedUserInfoString(paramsString));

        return new User(
                getIdFromInfoString(parsedInputUserParamsList),
                LocalDateTime.parse(
                        getLocalDateTimeFromInfoString(parsedInputUserParamsList),
                        DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)
                ),
                getLoginFromInfoString(parsedInputUserParamsList),
                getPasswordFromInfoString(parsedInputUserParamsList),
                getConfirmPasswordFromInfoString(parsedInputUserParamsList),
                getSurNameFromInfoString(parsedInputUserParamsList),
                getNameFromInfoString(parsedInputUserParamsList),
                getPatronymicFromInfoString(parsedInputUserParamsList),
                getAgeFromInfoString(parsedInputUserParamsList),
                getIsWorkerFromInfoString(parsedInputUserParamsList)
        );
    }

    /**
     * Методы получения сепарированных полей пользователя из строки
     *
     * @param infoStringCollection - Строка из файла с данными о пользователе
     *
     */
    public static String getIdFromInfoString(List<String> infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String id = infoStringCollection.get(ID_INDEX).trim();

            if (!id.trim().matches(ID_REQUIREMENTS)) {
                throw new IllegalArgumentException("id имеет неверный формат: допускаются только английские буквы и цифры!");
            }

            return id;
        }

        throw new NullPointerException(
                "id обязательный параметр и не может быть null!"
        );
    }

    public static String getLocalDateTimeFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String birthDateString = infoStringCollection.get(LOCAL_DATE_INDEX).trim();

            if (!birthDateString.matches(LOCAL_DATE_REQUIREMENTS)) {
                throw new IllegalArgumentException("localDateTime имеет неверный формат!");
            }

            return birthDateString;
        }

        throw new NullPointerException(
                "localDateTime обязательный параметр и не может быть null!"
        );
    }

    public static String getLoginFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String login = infoStringCollection.get(LOGIN_INDEX).trim();

            if (!login.matches(LOGIN_REQUIREMENTS)) {
                throw new IllegalArgumentException("login имеет неверный формат: допускаются только английские буквы и цифры!");
            }

            return login;
        }

        throw new NullPointerException(
                "login обязательный параметр и не может быть null!"
        );
    }

    public static String getPasswordFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String password = infoStringCollection.get(PASSWORD_INDEX).trim();

            if (!password.matches(PASSWORD_REQUIREMENTS)) {
                throw new IllegalArgumentException("password имеет неверный формат: допускаются только английские буквы и цифры!");
            }

            return password;
        }

        throw new NullPointerException(
                "password обязательный параметр и не может быть null!"
        );
    }

    public static String getConfirmPasswordFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String confirmPassword = infoStringCollection.get(CONFIRM_PASSWORD_INDEX).trim();

            if (!confirmPassword.matches(PASSWORD_REQUIREMENTS)) {
                throw new IllegalArgumentException("confirmPassword имеет неверный формат: допускаются только английские буквы и цифры!");
            }

            return confirmPassword;
        }

        throw new NullPointerException(
                "confirmPassword обязательный параметр и не может быть null!"
        );
    }

    public static String getSurNameFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String surName = infoStringCollection.get(SURNAME_INDEX).trim();

            if (!surName.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException("surName имеет неверный формат!");
            }

            return surName;
        }

        throw new NullPointerException(
                "surName обязательный параметр и не может быть null!"
        );
    }

    public static String getNameFromInfoString(List<String> infoStringCollection) {
        if (infoStringCollection != null) {
            String name = infoStringCollection.get(NAME_INDEX).trim();

            if (!name.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException("name имеет неверный формат!");
            }

            return name;
        }

        throw new NullPointerException(
                "name обязательный параметр и не может быть null!"
        );
    }

    public static String getPatronymicFromInfoString(List<String> infoStringCollection) {
        String patronymic = infoStringCollection.get(PATRONYMIC_INDEX).trim();

        if (!patronymic.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
            throw new IllegalArgumentException("patronymic имеет неверный формат!");
        }

        return patronymic;
    }

    public static Integer getAgeFromInfoString(List<String> infoStringCollection) {
        String ageString = infoStringCollection.get(AGE_INDEX).trim();

        if (Integer.parseInt(ageString) <= AGE_EDGE_VALUE) {
            throw new IllegalArgumentException("Возраст должен быть положительным целым числом!");
        }

        return Integer.valueOf(ageString);
    }

    public static Boolean getIsWorkerFromInfoString(List<String> infoStringCollection) {
        String isWorkerString = infoStringCollection.get(IS_WORKER_INDEX).trim();

        return Boolean.parseBoolean(isWorkerString);
    }

    /**
     * Проверка кредов авторизации на соответствие требованиям
     */
    public static boolean isLoginCorrect(String login) {
        if (login != null) {
            if (!login.trim().matches(LOGIN_REQUIREMENTS)) {
                throw new WrongLoginException();
            }

            return true;
        }

        throw new NullPointerException(
                "login обязательный параметр и не может быть null!"
        );
    }

    public static boolean isPasswordCorrect(String password) {
        if (password != null) {
            if (!password.trim().matches(PASSWORD_REQUIREMENTS)) {
                throw new WrongPasswordException();
            }

            return true;
        }

        return false;
    }

    public static boolean isPasswordEqualsConfirmPassword(String password, String confirmPassword) {
        if (isPasswordCorrect(password) && isPasswordCorrect(confirmPassword)){
            if (!Objects.equals(password, confirmPassword)) {
                throw new IllegalArgumentException(
                        "Вы ввели две РАЗНЫХ последовательности символов - password и confirmPassword должны совпадать!"
                );
            }

            return true;
        }

        return false;
    }

    public static boolean isAuthorizationCredentialsCorrect(String login, String password, String confirmPassword) {
        try {
            isLoginCorrect(login);
            isPasswordEqualsConfirmPassword(password, confirmPassword);

            return true;
        } catch(WrongLoginException | WrongPasswordException e) {
            System.err.println("Ошибка ввода данных:\n" + e.getClass().getCanonicalName() + ",\n" + e.getMessage() + ".\n") ;
            e.printStackTrace(System.out);
        }

        return false;
    }

    /**
     * Получение списка пользователей из файла
     */
    public static String readUsersFromTheFile() {
        return FileWorkHelper.readUsersDataStringFromTheFile(DATA_FILE_PATH);
    }
    public static String readUsersFromTheFile(String path) {
        return FileWorkHelper.readUsersDataStringFromTheFile(path);
    }
    public static String readUsersFromTheFile(InputStream inputStream) {
        return FileWorkHelper.readUsersDataStringFromTheFile(inputStream);
    }

    public static ArrayList<User> setUsersCollectionFromTheFile() {
        String dataString = readUsersFromTheFile();
        ArrayList<User> usersCollection = new ArrayList<>();
        List<String> dataStringCollection = new ArrayList<>(Arrays.asList(dataString.split("\n")))
                .stream().filter(item -> !item.isEmpty()).toList();

        for(String currentUserString: dataStringCollection) {
            List<String> specificUserInputParams = new ArrayList<>(List.of(currentUserString.split("\\|")));

            User specificCarEntity = new User(
                    getIdFromInfoString(specificUserInputParams),
                    LocalDateTime.parse(
                            getLocalDateTimeFromInfoString(specificUserInputParams),
                            DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)
                    ),
                    getLoginFromInfoString(specificUserInputParams),
                    getPasswordFromInfoString(specificUserInputParams),
                    getConfirmPasswordFromInfoString(specificUserInputParams),
                    getSurNameFromInfoString(specificUserInputParams),
                    getNameFromInfoString(specificUserInputParams),
                    getPatronymicFromInfoString(specificUserInputParams),
                    getAgeFromInfoString(specificUserInputParams),
                    getIsWorkerFromInfoString(specificUserInputParams)
            );

            Collections.addAll(usersCollection, specificCarEntity);
        }

        return usersCollection;
    }
    public static ArrayList<User> setUsersCollectionFromTheFile(String path) {
        String dataString = readUsersFromTheFile(path);
        ArrayList<User> usersCollection = new ArrayList<>();
        List<String> dataStringCollection = new ArrayList<>(Arrays.asList(dataString.split("\n")))
                .stream().filter(item -> !item.isEmpty()).toList();

        for(String currentUserString: dataStringCollection) {
            List<String> specificUserInputParams = new ArrayList<>(List.of(currentUserString.split("\\|")));

            User specificCarEntity = new User(
                    getIdFromInfoString(specificUserInputParams),
                    LocalDateTime.parse(
                            getLocalDateTimeFromInfoString(specificUserInputParams),
                            DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)
                    ),
                    getLoginFromInfoString(specificUserInputParams),
                    getPasswordFromInfoString(specificUserInputParams),
                    getConfirmPasswordFromInfoString(specificUserInputParams),
                    getSurNameFromInfoString(specificUserInputParams),
                    getNameFromInfoString(specificUserInputParams),
                    getPatronymicFromInfoString(specificUserInputParams),
                    getAgeFromInfoString(specificUserInputParams),
                    getIsWorkerFromInfoString(specificUserInputParams)
            );

            Collections.addAll(usersCollection, specificCarEntity);
        }

        return usersCollection;
    }
    public static ArrayList<User> setUsersCollectionFromTheFile(InputStream inputStream) {
        String dataString = readUsersFromTheFile(inputStream);
        ArrayList<User> usersCollection = new ArrayList<>();
        List<String> dataStringCollection = new ArrayList<>(Arrays.asList(dataString.split("\n")))
                .stream().filter(item -> !item.isEmpty()).toList();

        for(String currentUserString: dataStringCollection) {
            List<String> specificUserInputParams = new ArrayList<>(List.of(currentUserString.split("\\|")));

            User specificCarEntity = new User(
                    getIdFromInfoString(specificUserInputParams),
                    LocalDateTime.parse(
                            getLocalDateTimeFromInfoString(specificUserInputParams),
                            DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)
                    ),
                    getLoginFromInfoString(specificUserInputParams),
                    getPasswordFromInfoString(specificUserInputParams),
                    getConfirmPasswordFromInfoString(specificUserInputParams),
                    getSurNameFromInfoString(specificUserInputParams),
                    getNameFromInfoString(specificUserInputParams),
                    getPatronymicFromInfoString(specificUserInputParams),
                    getAgeFromInfoString(specificUserInputParams),
                    getIsWorkerFromInfoString(specificUserInputParams)
            );

            Collections.addAll(usersCollection, specificCarEntity);
        }

        return usersCollection;
    }
}
