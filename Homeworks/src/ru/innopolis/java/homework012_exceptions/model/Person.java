package ru.innopolis.java.homework012_exceptions.model;

import java.util.Objects;

public class Person {
    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private Integer phoneNumber;
    private String gender;
    private Integer age;

    public Person() {}
    public Person(
            String surname,
            String name,
            String patronymic,
            String birthDate,
            Integer phoneNumber,
            String gender,
            Integer age
    ) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
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

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "\n" +
                "surname='" + surname + '\'' + ";\n" +
                "name='" + name + '\'' + ";\n" +
                "patronymic='" + patronymic + '\'' + ";\n" +
                "birthDate='" + birthDate + '\'' + ";\n" +
                "phoneNumber=" + phoneNumber + ";\n" +
                "gender='" + gender + '\'' + ";\n" +
                "age=" + age + ";\n" +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getSurname(), person.getSurname())
                && Objects.equals(getName(), person.getName())
                && Objects.equals(getPatronymic(), person.getPatronymic())
                && Objects.equals(getBirthDate(), person.getBirthDate())
                && Objects.equals(getPhoneNumber(), person.getPhoneNumber())
                && Objects.equals(getGender(), person.getGender())
                && Objects.equals(getAge(), person.getAge());
    }
    @Override
    public int hashCode() {
        return Objects.hash(
                getSurname(),
                getName(),
                getPatronymic(),
                getBirthDate(),
                getPhoneNumber(),
                getGender(),
                getAge()
        );
    }
}
