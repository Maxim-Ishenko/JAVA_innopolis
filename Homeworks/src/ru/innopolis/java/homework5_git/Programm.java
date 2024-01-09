package ru.innopolis.java.homework5_git;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Programm {
    private String name;
    private double rating;
    private long audience;

    public Programm() {}
    public Programm(
            String name,
            double rating,
            long audience) throws IllegalArgumentException, InputMismatchException {
        this.setName(name);
        this.setRating(rating);
        this.setAudience(audience);
    }

    public String getName() {
        return name;
    }
    public double getRating() {
        return rating;
    }
    public long getAudience() {
        return audience;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (Objects.equals(name, "")) {
            throw new IllegalArgumentException("Название программы не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setName(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите название программы: ");
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            throw new InputMismatchException("Название программы не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setRating(Scanner scanner) {
        System.out.println("Введите рейтинг: ");

        this.rating = scanner.nextDouble();
    }
    public void setAudience(long audience) throws IllegalArgumentException {
        if (audience < 0) {
            throw new IllegalArgumentException("Аудитория не может быть отрицательной!");
        }

        this.audience = audience;
    }
    public void setAudience(Scanner scanner) throws InputMismatchException {
        System.out.println("Введите размер аудитории: ");
        long audience = scanner.nextLong();

        if (audience < 0) {
            throw new InputMismatchException("Аудитория не может быть отрицательной!");
        }

        this.audience = audience;
    }

    @Override
    public String toString() {
        return "Programm{" +
            "name='" + name + '\'' + "\n" +
            ", rating=" + rating + "\n" +
            ", audience=" + audience + "\n" +
            '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programm programm = (Programm) o;
        return Double.compare(rating, programm.rating) == 0 && audience == programm.audience && Objects.equals(name, programm.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, rating, audience);
    }
}
