package ru.innopolis.java.homework2_JVM.task4_grid_drawer;

public class App {
    public static void main(String[] args) {
        int columns = GridDrawer.getColumnsAmount();
        int strings = GridDrawer.getStringsAmount();
        String baseElement = GridDrawer.getRepeatableElement();

        GridDrawer.getResultGrid(columns, strings, baseElement);
    }
}
