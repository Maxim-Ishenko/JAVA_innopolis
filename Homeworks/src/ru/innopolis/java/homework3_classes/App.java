package ru.innopolis.java.homework3_classes;

public class App {
    public static void main(String [] args) {
        TV tvModelOne = new TV();
        tvModelOne.setCompanyName(TV.TVCompanies.LG);
        tvModelOne.setDiagonal(32);
        tvModelOne.setCoast(150);
        tvModelOne.setHasInternetConnectivity(true);
        tvModelOne.setScreenTechnologyType(TV.TVScreenTechnologyTypes.QLED);
        System.out.println("Экземпляр класса, созданный с помощью конструктора без параметров: \n" + tvModelOne);

        TV tvModelTwo = new TV(TV.TVCompanies.RUBIN, 75.25, 32);
        System.out.println(
            "Экземпляр класса, созданный с помощью конструктора с 3мя задаваемыми параметрами \n" +
            "и 2мя предустановленными параметрами: \n" + tvModelTwo);

        TV tvModelThree = new TV(TV.TVCompanies.SAPPHIR, TV.TVScreenTechnologyTypes.OLED, 15.7, 8, false);
        System.out.println("Экземпляр класса, созданный с помощью конструктора со всеми параметрами: \n" + tvModelThree);

        System.out.println("Проверка работоспособности геттеров: \n");
        System.out.println("companyName: " + tvModelOne.getCompanyName() + "\n");
        System.out.println("diagonal: " + tvModelTwo.getDiagonal() + "\n");
        System.out.println("coast: " + tvModelTwo.getCoast() + "\n");
        System.out.println("hasInternetConnectivity: " + tvModelThree.getHasInternetConnectivity() + "\n");
        System.out.println("screenTechnology: " + tvModelThree.getScreenTechnologyType() + "\n");

        System.out.println("Проверка работоспособности ручного ввода имени компании через консоль: \n");
        TV tvModelFour = new TV();
        tvModelFour.setCompanyNameThroughManualInput();
        System.out.println("companyName: " + tvModelFour.getCompanyName() + "\n");
        System.out.println("Сайт производителя: \n");
        System.out.println(tvModelFour.getContactInfo());

        System.out.println("Вывод предупреждения: \n");
        System.out.println(tvModelFour.getStandartWarning());
    }
}
