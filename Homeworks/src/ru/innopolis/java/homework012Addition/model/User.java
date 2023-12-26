package ru.innopolis.java.homework012Addition.model;

import ru.innopolis.java.homework012Addition.repository.WrongLoginException;
import ru.innopolis.java.homework012Addition.repository.WrongPasswordException;

import java.util.Objects;

public class User {
    private static final String LOGIN_REQUIREMENTS = "^[a-zA-Z0-9-_]{1,20}$";
    private static final String PASSWORD_REQUIREMENTS = "^[a-zA-Z0-9-_]{1,20}$";
    private String login;
    private String password;

    public User() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isLoginCorrect(String login) throws WrongLoginException {
        if (login != null) {
            if (!login.trim().matches(LOGIN_REQUIREMENTS)) {
                throw new WrongLoginException("Логин имеет неверный формат!");
            }

            return true;
        }

        return false;
    }

    public static boolean isPasswordCorrect(String password) throws WrongPasswordException {
        if (password != null) {
            if (!password.trim().matches(PASSWORD_REQUIREMENTS)) {
                throw new WrongPasswordException("Пароль имеет неверный формат!");
            }

            return true;
        }

        return false;
    }

    public static boolean isPasswordEqualsConfirmPassword(String password, String confirmPassword) throws WrongPasswordException {
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

    public static boolean isAuthorizationCredentialsCorrect(String login, String password, String confirmPassword) {
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

    public void setAuthorizationCredentials(String login, String password, String confirmPassword) {
        try {
            if (isAuthorizationCredentialsCorrect(login, password, confirmPassword)) {
                setLogin(login);
                setPassword(password);

                System.out.println("Пароль/логин обновлены!");
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Ошибка ввода данных:\n" + e.getClass().getCanonicalName() + ",\n" + e.getMessage() + ".\n") ;
            e.printStackTrace(System.out);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
