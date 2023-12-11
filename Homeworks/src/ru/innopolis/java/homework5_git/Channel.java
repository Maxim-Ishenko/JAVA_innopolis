package ru.innopolis.java.homework5_git;

import java.util.Objects;
import java.util.Scanner;

public class Channel {
    private String name;
    private int orderNumber;
    private Programm programm;

    public Channel() {}
    public Channel(
            String name,
            int orderNumber,
            Programm programm
    ) throws Exception {
        this.setName(name);
        this.setOrderNumber(orderNumber);
        this.setProgramm(programm);
    }

    public String getName() {
        return name;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public Programm getProgramm() {
        return programm;
    }

    public void setName(String name) throws Exception {
        if (Objects.equals(name, "")) {
            throw new Exception("Название канала не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setName(Scanner scanner) throws Exception {
        System.out.println("Введите название канала: ");
        String name = scanner.nextLine();

        if (Objects.equals(name, "")) {
            throw new Exception("Название канала не может быть пустой строкой!");
        }

        this.name = name;
    }
    public void setOrderNumber(int orderNumber) throws Exception {
        if (orderNumber <= 0) {
            throw new Exception("Порядковый номер канала может быть только положительным числом!");
        }

        this.orderNumber = orderNumber;
    }
    public void setOrderNumber(Scanner scanner) throws Exception {
        System.out.println("Введите порядковый номер канала: ");
        int orderNumber = scanner.nextInt();

        if (orderNumber <= 0) {
            throw new Exception("Порядковый номер канала может быть только положительным числом!");
        }

        this.orderNumber = orderNumber;
    }
    public void setProgramm(Programm programm) {
        this.programm = programm;
    }

    @Override
    public String toString() {
        return "Channel{" +
            "name='" + name + '\'' + "\n" +
            ", orderNumber=" + orderNumber + "\n" +
            ", programm=" + programm + "\n" +
            '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return orderNumber == channel.orderNumber && Objects.equals(name, channel.name) && Objects.equals(programm, channel.programm);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, orderNumber, programm);
    }
}
