package ru.innopolis.java.attestation01.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    /**
     * Поля класса
     *
    */
    private String id;
    private LocalDate localDateTime = LocalDate.now();
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
            LocalDate localDateTime,
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

    public LocalDate getLocalDateTime() {
        return localDateTime;
    }
    public void setLocalDateTime(LocalDate localDateTime) {
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
        return "User{" +
                "id='" + id + '\'' +
                ", localDateTime='" + localDateTime + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", isWorker=" + isWorker +
                '}';
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
