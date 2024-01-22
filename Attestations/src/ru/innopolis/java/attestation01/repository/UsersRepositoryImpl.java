package ru.innopolis.java.attestation01.repository;

import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.utils.CustomExceptions.UserNotFoundException;
import ru.innopolis.java.attestation01.utils.CustomExceptions.WrongLoginException;
import ru.innopolis.java.attestation01.utils.CustomExceptions.WrongPasswordException;
import ru.innopolis.java.attestation01.utils.Helpers.FileWorkHelper;
import ru.innopolis.java.attestation01.utils.Helpers.ScannerWorkHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UsersRepositoryImpl implements UsersRepository {
    @Override
    public void create(User user) {
        FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
    }

    // Поиск пользователя в файле по идентификатору
    @Override
    public User findById(String id) {
//        try {
//            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();
//            User targetUser = usersCollection.stream().filter(user -> Objects.equals(id, user.getId()))
//                    .findAny()
//                    .orElse(null);
//
//            if (targetUser == null) {
//                throw new UserNotFoundException("Пользователя с заданным идентификатором не существует");
//            }
//        } catch(UserNotFoundException error) {
//            System.out.println(error.getMessage());
//            error.printStackTrace(System.out);
//        }

        ArrayList<User> usersCollection = setUsersCollectionFromTheFile();
        User targetUser = usersCollection.stream().filter(user -> Objects.equals(id, user.getId()))
                .findAny()
                .orElse(null);

        if (Objects.isNull(targetUser)) {
            throw new UserNotFoundException("Пользователя с заданным идентификатором не существует");
        }

        return targetUser;
    }

    @Override
    public List<User> findAll() {
        return setUsersCollectionFromTheFile();
    }

    @Override
    public void update(User user) {
        System.out.println("user-" + user);
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();

            User targetUser = usersCollection.stream().filter(
                        currentUser -> Objects.equals(currentUser.getId(), user.getId())
                    )
                    .findAny()
                    .orElse(null);

            if (Objects.isNull(targetUser)) {
                System.out.println("Пользователя с заданным идентификатором не существует - будет создан новый пользователь");
                FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
            } else {
                List<User> updatedUsersCollection = setUsersCollectionFromTheFile().stream().map(currentUser ->
                    Objects.equals(currentUser.getId(), user.getId())
                            ? user : currentUser
                    ).toList();

                deleteAll();

                for (User currentUser : updatedUsersCollection) {
                    if (Objects.isNull(currentUser)) break;

                    FileWorkHelper.writeTheUserToTheFile(currentUser.toString(), DATA_FILE_PATH, true);
                }

                System.out.println("Обновление пользователя по id прошло успешно");
            }
        } catch(Exception e) {
            System.out.println("Ошибка при обновлении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();
            User targetUser = findById(id);

            if (Objects.isNull(targetUser)) {
                throw new UserNotFoundException("Пользователя с заданным идентификатором не существует");
            }

            List<User> updatedUsersCollection = usersCollection.stream().filter(
                    user -> !Objects.equals(id, user.getId())
            ).toList();

            deleteAll();

            for (User user : updatedUsersCollection) {
                FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
            }

            System.out.println("Удаление пользователя по id и обновление списка прошли успешно");
        } catch(Exception e) {
            System.out.println("Ошибка при удалении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void deleteAll() {
        try {
            new FileOutputStream(DATA_FILE_PATH).close();
            System.out.println("Очистка файла прошла успешно");
        } catch(IOException e) {
            System.err.println("Ошибка при удалении:\n" + e.getMessage() + "\n"); ;
            e.printStackTrace(System.out);
        }
    }

    public String getUserParamsFromConsole() {
        return ScannerWorkHelper.getTheUsersDataStringFromConsole(USERS_PARAMS_INPUT_NOTIFICATION_HELP);
    }

    public String[] getParsedUserInfoString(String infoString) throws IllegalArgumentException {
        String[] usersInfo = infoString.split("\\|");

        if (usersInfo.length < USERS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит не все необходимые параметры!");
        }

        if (usersInfo.length > USERS_PARAMS_AMOUNT) {
            throw new IllegalArgumentException("Введенная строка содержит больше параметров, чем требуется!");
        }

        return usersInfo;
    }

    public User createNewUser() {
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
    public User createNewUser(String paramsString) {
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
    public String getIdFromInfoString(List<String> infoStringCollection) throws IllegalArgumentException, NullPointerException {
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

    public String getLocalDateTimeFromInfoString(List<String> infoStringCollection) {
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

    public String getLoginFromInfoString(List<String> infoStringCollection) {
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

    public String getPasswordFromInfoString(List<String> infoStringCollection) {
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

    public String getConfirmPasswordFromInfoString(List<String> infoStringCollection) {
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

    public String getSurNameFromInfoString(List<String> infoStringCollection) {
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

    public String getNameFromInfoString(List<String> infoStringCollection) {
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

    public String getPatronymicFromInfoString(List<String> infoStringCollection) {
        String patronymic = infoStringCollection.get(PATRONYMIC_INDEX).trim();

        if (!patronymic.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
            throw new IllegalArgumentException("patronymic имеет неверный формат!");
        }

        return patronymic;
    }

    public Integer getAgeFromInfoString(List<String> infoStringCollection) {
        String ageString = infoStringCollection.get(AGE_INDEX).trim();

        if (Integer.parseInt(ageString) <= AGE_EDGE_VALUE) {
            throw new IllegalArgumentException("Возраст должен быть положительным целым числом!");
        }

        return Integer.valueOf(ageString);
    }

    public Boolean getIsWorkerFromInfoString(List<String> infoStringCollection) {
        String isWorkerString = infoStringCollection.get(IS_WORKER_INDEX).trim();

        return Boolean.parseBoolean(isWorkerString);
    }

    /**
    * Проверка кредов авторизации на соответствие требованиям
    */
    public boolean isLoginCorrect(String login) {
        if (login != null) {
            if (!login.trim().matches(LOGIN_REQUIREMENTS)) {
                throw new WrongLoginException("Логин имеет неверный формат!");
            }

            return true;
        }

        throw new NullPointerException(
                "login обязательный параметр и не может быть null!"
        );
    }

    public boolean isPasswordCorrect(String password) {
        if (password != null) {
            if (!password.trim().matches(PASSWORD_REQUIREMENTS)) {
                throw new WrongPasswordException("Пароль имеет неверный формат!");
            }

            return true;
        }

        return false;
    }

    public boolean isPasswordEqualsConfirmPassword(String password, String confirmPassword) {
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

    public boolean isAuthorizationCredentialsCorrect(String login, String password, String confirmPassword) {
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
    public String readUsersFromTheFile() {
        return FileWorkHelper.readUsersDataStringFromTheFile(DATA_FILE_PATH);
    }

    public ArrayList<User> setUsersCollectionFromTheFile() {
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
}
