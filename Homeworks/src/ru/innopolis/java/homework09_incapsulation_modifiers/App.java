package ru.innopolis.java.homework09_incapsulation_modifiers;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;
import ru.innopolis.java.homework09_incapsulation_modifiers.Car.PerformanceCar;
import ru.innopolis.java.homework09_incapsulation_modifiers.Car.ShowCar;
import ru.innopolis.java.homework09_incapsulation_modifiers.Garage.Garage;
import ru.innopolis.java.homework09_incapsulation_modifiers.Race.*;

// Пояснения по дополнительному заданию
// В условии дополнительного задания не оговорено -на каком уровне реализовать чтение и запись файла.
// Так как, при желании, информация о времени и количестве кругов может храниться в разных файлах и логика обработки может отличаться,
// я реализовал данную логику в обоих классах - и TimeLimitRace и CircuitRace, без выноса в отдельный класс.
// По этой же причине запись информации в файл также реализовал только в дополнительных классах


public class App {
    static String outputFilePath = "src/ru/innopolis/java/homework09_incapsulation_modifiers/data/output.txt";
    public static void main(String[] args) throws Exception {
        Car opel = new Car("opel", "34fwsf", 1987, 67, 1, 54, 33);
        PerformanceCar mockvich = new PerformanceCar("mockvich", "412", 1980, 50, 78, 5, 45);
        PerformanceCar mockvichWithAddons = new PerformanceCar("mockvich", "412", 1980, 50, 78, 5, 45, new String[]{"as", "aas"});
        ShowCar niva = new ShowCar("lada", "niva", 1990, 76, 1,7,20);
        ShowCar nivaWithStars = new ShowCar("lada", "niva", 1990, 76, 1,7,20, 7);
// 1
        System.out.println("Cars: {" + "\n" +
            "opel: " + opel + ";\n" +
            "mockvich: " + mockvich + ";\n" +
            "mockvichWithAddons: " + mockvichWithAddons + ";\n" +
            "niva: " +  niva + ";\n" +
            "nivaWithStars: " +  nivaWithStars + ";\n" + "}");

        Race race = new Race(140, "Babrujsk-NewYork", 140,new Car[]{opel, niva});
        CasualRace casualRace = new CasualRace(1870, "Minsk-Pekin", 14,new Car[]{opel, niva});
        DragRace dragRace = new DragRace(102, "StPetersburg-Moscow", 1440,new Car[]{opel, mockvich});
        DriftRace driftRace = new DriftRace(107, "Moscow-Minsk", 70,new Car[]{mockvichWithAddons, nivaWithStars});

        System.out.println("Races: {" + "\n" +
            "race: " + race + ";\n" +
            "casualRace: " + casualRace + ";\n" +
            "dragRace: " + dragRace + ";\n" +
            "driftRace: " +  driftRace + ";\n" + "}");

        Garage emptyGarage = new Garage();
        Garage garage = new Garage(new Car[]{mockvichWithAddons, mockvich});
        garage.modifyPower(mockvich, 1787);

        System.out.println("Garages: {" + "\n" +
                "emptyGarage: " + emptyGarage + ";\n" +
                "garage: " +  garage + ";\n" + "}");

// 2
//        TimeLimitRace timeLimitRace = new TimeLimitRace(140, "Babrujsk-NewYork", 140, new Car[]{opel, niva});
//        timeLimitRace.setWriteTheResultsToTheFile(timeLimitRace.toString(), outputFilePath);
//        CircuitRace circuitRace = new CircuitRace(1870, "Minsk-Pekin", 14, new Car[]{opel, niva});
//        circuitRace.setWriteTheResultsToTheFile(circuitRace.toString(), outputFilePath);
//        System.out.println("timeLimitRace " + timeLimitRace);
//        System.out.println("circuitRace " + circuitRace);
    }
}
