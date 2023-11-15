package ru.innopolis.java.homework5_git;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Пояснения к 5 домашке
 * 1 - Сделал все на базе имеющегося класса TV, как по заданию
 * 2 - Все доработки помечены комментом - HW5_ADDITIONAL
 * 3 - Для запуска оставил только вызовы методов для проверки выполнения условий 5 домашки
 */

public class App {
    public static void main(String [] args) throws Exception {
        //    HW5
        //    Scanner scanner = new Scanner(System.in);
        //
        //    TV[] tvCollection = TV.getTVCollection(scanner, 10);
        //
        //    System.out.println("Введите максимально допустимую громкость: ");
        //    int maxVolume = scanner.nextInt();
        //    scanner.nextLine();
        //
        //    TV[] activeTVCollectionWithAvailableVolume =
        //            TV.getActiveTVCollectionWithAvailableVolume(tvCollection, maxVolume).toArray(new TV[0]);
        //
        //    System.out.println("Коллекция телевизоров, удовлетворяющих условиям: "
        //        + Arrays.toString(activeTVCollectionWithAvailableVolume));
        //
        //    scanner.close();

        //    HW6
        Programm firstProgramm = new Programm("Смехопанорама", 75, 51561);
        Programm secondProgramm = new Programm("Новости", 150, 200000);

        Channel firstChannel = new Channel("ОРТ", 1, firstProgramm);
        Channel secondChannel = new Channel("Культура", 2, secondProgramm);

        TV myTV = new TV(new Channel[]{firstChannel, secondChannel});

        System.out.println("ТВ со стандарным набором каналов: " + myTV);
    }
}
