package ru.innopolis.java.attestation01.repository;

import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.utils.CustomExceptions.WrongLoginException;
import ru.innopolis.java.attestation01.utils.CustomExceptions.WrongPasswordException;

import java.util.List;
import java.util.Objects;

public class UsersRepositoryImpl implements UsersRepository {
    // Создание пользователя и запись его в файл
    @Override
    public void create(User user) {

    }

    // Поиск пользователя в файле по идентификатору
    @Override
    public User findById(String id) {
        return null;
    }

    // Выгрузка всех пользователей из файла
    @Override
    public List<User> findAll() {
        return null;
    }

    // Обновление полей существующего в файле пользователя
    @Override
    public void update(User user) {

    }

    // Удаление пользователя по идентификатору
    @Override
    public void deleteById(String id) {

    }

    // Удаление всех пользователей
    @Override
    public void deleteAll() {

    }

    /**
     * Методы получения сепарированных полей пользователя из строки
     *
     * @param infoStringCollection - Строка из файла с данными о пользователе
     */
    @Override
    public String getIdFromInfoString(String[] infoStringCollection) throws IllegalArgumentException, NullPointerException {
        if (infoStringCollection != null) {
            String id = infoStringCollection[ID_INDEX].trim();

            if (!id.matches(ID_REQUIREMENTS)) {
                throw new IllegalArgumentException("id должен состоять только из букв и цифр!");
            }

            return id;
        }

        throw new NullPointerException(
                "id обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getLocalDateTimeFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String birthDateString = infoStringCollection[LOCAL_DATE_INDEX].trim();

            if (!birthDateString.matches(LOCAL_DATE_REQUIREMENTS)) {
                throw new IllegalArgumentException("localDateTime имеет неверный формат!");
            }

            return birthDateString;
        }

        throw new NullPointerException(
                "localDateTime обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getLoginFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String login = infoStringCollection[LOGIN_INDEX].trim();

            if (!login.matches(LOGIN_REQUIREMENTS)) {
                throw new IllegalArgumentException("login имеет неверный формат!");
            }

            return login;
        }

        throw new NullPointerException(
                "login обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getPasswordFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String password = infoStringCollection[PASSWORD_INDEX].trim();

            if (!password.matches(PASSWORD_REQUIREMENTS)) {
                throw new IllegalArgumentException("password имеет неверный формат!");
            }

            return password;
        }

        throw new NullPointerException(
                "password обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getConfirmPasswordFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String confirmPassword = infoStringCollection[CONFIRM_PASSWORD_INDEX].trim();

            if (!confirmPassword.matches(PASSWORD_REQUIREMENTS)) {
                throw new IllegalArgumentException("confirmPassword имеет неверный формат!");
            }

            return confirmPassword;
        }

        throw new NullPointerException(
                "confirmPassword обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getSurNameFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String surName = infoStringCollection[SURNAME_INDEX].trim();

            if (!surName.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException("surName имеет неверный формат!");
            }

            return surName;
        }

        throw new NullPointerException(
                "surName обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getNameFromInfoString(String[] infoStringCollection) {
        if (infoStringCollection != null) {
            String name = infoStringCollection[NAME_INDEX].trim();

            if (!name.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
                throw new IllegalArgumentException("name имеет неверный формат!");
            }

            return name;
        }

        throw new NullPointerException(
                "name обязательный параметр и не может быть null!"
        );
    }

    @Override
    public String getPatronymicFromInfoString(String[] infoStringCollection) {
        String patronymic = infoStringCollection[PATRONYMIC_INDEX].trim();

        if (!patronymic.matches(SURNAME_NAME_PATRONYMIC_REQUIREMENTS)) {
            throw new IllegalArgumentException("patronymic имеет неверный формат!");
        }

        return patronymic;
    }

    @Override
    public Integer getAgeFromInfoString(String[] infoStringCollection) {
        String ageString = infoStringCollection[AGE_INDEX].trim();

        if (Integer.parseInt(ageString) <= AGE_EDGE_VALUE) {
            throw new IllegalArgumentException("Возраст должен быть положительным целым числом!");
        }

        return Integer.valueOf(ageString);
    }

    @Override
    public Boolean getIsWorkerFromInfoString(String[] infoStringCollection) {
        return null;
    }

    /**
    * Проверка кредов авторизации на соответствие требованиям
    */
    @Override
    public boolean isLoginCorrect(String login) {
        if (login != null) {
            if (!login.trim().matches(LOGIN_REQUIREMENTS)) {
                throw new WrongLoginException("Логин имеет неверный формат!");
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        if (password != null) {
            if (!password.trim().matches(PASSWORD_REQUIREMENTS)) {
                throw new WrongPasswordException("Пароль имеет неверный формат!");
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean isPasswordEqualsConfirmPassword(String password, String confirmPassword) {
        if (isPasswordCorrect(password) && isPasswordCorrect(confirmPassword)){
            if (!Objects.equals(password, confirmPassword)) {
                throw new IllegalArgumentException(
                        "Вы ввели две РАЗНЫХ последовательности символов - пароль и повтор должны совпадать!"
                );
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean isAuthorizationCredentialsCorrect(String login, String password, String confirmPassword) {
        try {
            isLoginCorrect(login);
            isPasswordEqualsConfirmPassword(password, confirmPassword);

            return true;
        } catch(WrongLoginException | WrongPasswordException e) {
            System.out.println("Ошибка ввода данных:\n" + e.getClass().getCanonicalName() + ",\n" + e.getMessage() + ".\n") ;
            e.printStackTrace(System.out);
        }

        return false;
    }
}
