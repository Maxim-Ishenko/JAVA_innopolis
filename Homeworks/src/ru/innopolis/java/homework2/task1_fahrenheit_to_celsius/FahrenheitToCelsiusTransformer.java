package ru.innopolis.java.homework2.task1_fahrenheit_to_celsius;

import java.util.Scanner;

public class FahrenheitToCelsiusTransformer {
    public static void getCelsiusTemperatureValue() {
        System.out.println("Enter the temperature in the Fahrenheit degrees: ");
        Scanner inputFlowEntity = new Scanner(System.in);
        int fahrenheitValue = inputFlowEntity.nextInt();

        int transformedTemperature = (fahrenheitValue - 32) * 5 / 9;
        System.out.println("Temperature in the Celsius degrees is " + transformedTemperature);
    }
}
