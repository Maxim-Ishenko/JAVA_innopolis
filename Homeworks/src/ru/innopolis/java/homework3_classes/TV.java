package ru.innopolis.java.homework3_classes;

import java.util.Scanner;

public class TV {
    /**
    * Допустимые к выбору технологии экранов
    */
    public enum TVScreenTechnologyTypes {
        LCD, OLED, QLED
    }
    public enum TVCompanies {
        LG, SONY, SAMSUNG, SAPPHIR, RUBIN,
    }

    /**
    * Поля класса
    */
    private TVCompanies companyName;
    private TVScreenTechnologyTypes screenTechnologyType;
    private int diagonal;
    private double coast;
    private boolean hasInternetConnectivity;

    /**
    * Конструктор без параметров
    */
    public TV() {}

    /**
    * Конструктор с тремя параметрами
    */
    public TV(TVCompanies companyName, double coast, int diagonal) {
        this.companyName = companyName;
        this.coast = coast;
        this.diagonal = diagonal;
        // Параметры с значениями по умолчанию
        this.screenTechnologyType = TVScreenTechnologyTypes.LCD;
        this.hasInternetConnectivity = false;
    }

    /**
    * Конструктор со всеми необходимыми параметрами
    */
    public TV(
            TVCompanies companyName,
            TVScreenTechnologyTypes screenTechnologyType,
            double coast,
            int diagonal,
            boolean hasInternetConnectivity
    ) {
        this.companyName = companyName;
        this.coast = coast;
        this.diagonal = diagonal;
        this.screenTechnologyType = screenTechnologyType;
        this.hasInternetConnectivity = hasInternetConnectivity;
    }

    // getters
    public TVCompanies getCompanyName() {
        return companyName;
    }
    public TVScreenTechnologyTypes getScreenTechnologyType() {
        return screenTechnologyType;
    }
    public double getCoast() {
        return coast;
    }
    public int getDiagonal() {
        return diagonal;
    }
    public boolean getHasInternetConnectivity() {
        return hasInternetConnectivity;
    }

    //setters
    public void setCompanyName(TVCompanies companyName) {
        this.companyName = companyName;
    }
    public void setScreenTechnologyType(TVScreenTechnologyTypes screenTechnologyType) {
        this.screenTechnologyType = screenTechnologyType;
    }
    public void setCoast(double coast) {
        this.coast = coast;
    }
    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
    public void setHasInternetConnectivity(boolean hasInternetConnectivity) {
        this.hasInternetConnectivity = hasInternetConnectivity;
    }
    public void setCompanyNameThroughManualInput() {
        System.out.println("Введите искомого производителя: ");
        Scanner inputCompanyName = new Scanner(System.in);
        this.companyName = TVCompanies.valueOf(inputCompanyName.next());
    }
    public void setDiagonalThroughManualInput() {
        System.out.println("Введите искомую диагональ: ");
        Scanner diagonal = new Scanner(System.in);
        this.diagonal = diagonal.nextInt();
    }

    // Метод класса без параметров
    public String getStandartWarning() {
        return
            "Телевизор является средством повышенной опасности! \n " +
            "Не стоит оставлять наедине с включенным телевизором детей, \n" +
            "беременных женщин, людей с неокрепшей психикой! \n";
    };

    // Метод класса с параметрами
    public String getContactInfo() {
        return switch (this.companyName) {
            case LG -> "www.lg.com";
            case SAMSUNG -> "www.samsung.com";
            case SONY -> "www.sony.com";
            case RUBIN -> "www.rubin.com";
            case SAPPHIR -> "www.sapphir.com";
            default -> "Пожалуйста, проверьте правильность написания имени производителя!";
        };
    }

    public String toString() {
        return "Модель фирмы: " + this.companyName + ";\n" +
            "с диагональю экрана: " + this.diagonal + ";\n" +
            "изготовленного по технологии: " + this.screenTechnologyType + ";\n" +
            "наличие соединения с интернетом: " + (this.hasInternetConnectivity ? "Да" : "Нет") + ";\n" +
            "стоит: " + this.coast + ".\n";
    }
}
