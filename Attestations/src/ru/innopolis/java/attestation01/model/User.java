package ru.innopolis.java.attestation01.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static ru.innopolis.java.attestation01.repository.UsersRepository.LOCAL_DATE_FORMATTER_PATTERN;
import static ru.innopolis.java.attestation01.repository.UsersRepository.USER_PARAMS_DIVIDER;

public class User {
    private String id;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String login;
    private String password;
    private String confirmPassword;
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private Boolean isWorker = false;

    public User() {}
    /**
     * @param id – гарантированно уникальный ID пользователя. Состоит из букв и цифр;
     * @param localDateTime - дата добавления в систему, по умолчанию сегодня, формат: дата и время;
     * @param login - не может быть только из цифр, содержит буквы,цифры, знак подчеркивания, меньше 20 символов
     * @param password - не может быть только из букв, содержит буквы, цифры, знак подчеркивания, меньше 20символов
     * @param confirmPassword - аналогично с password;
     * @param surname - строка, состоит только из букв;
     * @param name - строка, состоит только из букв;
     * @param patronymic - строка, состоит только из букв, может отсутствовать;
     * @param age - целое число, может отсутствовать;
     * @param isWorker - является ли сотрудником предприятия, по умолчанию false.
     */
    public User(
            String id,
            LocalDateTime localDateTime,
            String login,
            String password,
            String confirmPassword,
            String surname,
            String name,
            String patronymic,
            Integer age,
            Boolean isWorker
    ) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.age = age;
        this.isWorker = isWorker;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getWorker() {
        return isWorker;
    }
    public void setWorker(Boolean worker) {
        isWorker = worker;
    }

    @Override
    public String toString() {
        String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN));

        return id + USER_PARAMS_DIVIDER +
                localDateTimeString + USER_PARAMS_DIVIDER +
                login + USER_PARAMS_DIVIDER +
                password + USER_PARAMS_DIVIDER +
                confirmPassword + USER_PARAMS_DIVIDER +
                surname + USER_PARAMS_DIVIDER +
                name + USER_PARAMS_DIVIDER +
                patronymic + USER_PARAMS_DIVIDER +
                age + USER_PARAMS_DIVIDER +
                isWorker;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(
                getId(), user.getId())
                && Objects.equals(getLocalDateTime(), user.getLocalDateTime())
                && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getConfirmPassword(), user.getConfirmPassword())
                && Objects.equals(getSurname(), user.getSurname())
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getPatronymic(), user.getPatronymic())
                && Objects.equals(getAge(), user.getAge())
                && Objects.equals(isWorker, user.isWorker
        );
    }
    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getLocalDateTime(),
                getLogin(),
                getPassword(),
                getConfirmPassword(),
                getSurname(),
                getName(),
                getPatronymic(),
                getAge(),
                isWorker
        );
    }
}
