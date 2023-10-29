package ru.innopolis.java.homework2_JVM.task2_two_numbers_states;

public class App {
    public static void main(String[] args) throws Exception {
        int firstNum = NumbersStatesPresenter.getIntNum(1);
        int secondNum = NumbersStatesPresenter.getIntNum(2);

        System.out.println("Сумма двух целых чисел: " + NumbersStatesPresenter.getSumm(firstNum, secondNum));
        System.out.println("Разница двух целых чисел: " + NumbersStatesPresenter.getDifference(firstNum, secondNum));
        System.out.println("Произведение из двух целых чисел: " + NumbersStatesPresenter.getMultiplicationResult(firstNum, secondNum));
        System.out.println("Среднее из двух целых чисел: " + NumbersStatesPresenter.getMediumVal(firstNum, secondNum));
        System.out.println("Расстояние двух целых чисел: " + NumbersStatesPresenter.getDistance(firstNum, secondNum));
        System.out.println("Максимальное целое число: " + NumbersStatesPresenter.getMax(firstNum, secondNum));
        System.out.println("Минимальное целое число: " + NumbersStatesPresenter.getMin(firstNum, secondNum));
    }
}
