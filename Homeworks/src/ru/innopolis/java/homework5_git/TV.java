package ru.innopolis.java.homework5_git;

import java.util.*;

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

    // HW5_ADDITIONAL Поля, добавленные в рамках 5 домашки
    private int activeChannelNumber;
    private int volumeValue;
    private boolean isTVActive;

    // HW6
    private Channel[] channels;

    /**
    * Конструктор без параметров
    */
    public TV() {}

    /**
    * Конструктор с тремя параметрами
    * @param companyName - название производителя
    * @param coast - цена
    * @param diagonal - диагональ экрана
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
     * @param companyName - название производителя
     * @param coast - цена
     * @param diagonal - диагональ экрана
     * @param activeChannelNumber - включенный канал
     * @param hasInternetConnectivity - имеется ли доступ к интернету
     * @param isTVActive - включен ли телевизор
     * @param volumeValue - уровень громкости
     * @param screenTechnologyType - технология экрана
    */
    public TV(
            TVCompanies companyName,
            TVScreenTechnologyTypes screenTechnologyType,
            double coast,
            int diagonal,
            boolean hasInternetConnectivity,
            int activeChannelNumber,
            int volumeValue,
            boolean isTVActive,
            Channel[] channels
    ) {
        this.companyName = companyName;
        this.coast = coast;
        this.diagonal = diagonal;
        this.screenTechnologyType = screenTechnologyType;
        this.hasInternetConnectivity = hasInternetConnectivity;
        this.activeChannelNumber = activeChannelNumber;
        this.volumeValue = volumeValue;
        this.isTVActive = isTVActive;
        this.channels = channels;
    }

    // HW5_ADDITIONAL - Конструктор для проверки 5 домашки (с вновь добавленными параметрами)
    public TV (
        TVCompanies companyName,
        int activeChannelNumber,
        int volumeValue,
        boolean isTVActive
    ) {
        this.companyName = companyName;
        this.activeChannelNumber = activeChannelNumber;
        this.volumeValue = volumeValue;
        this.isTVActive = isTVActive;
    }

    // HW6
    public TV(
        Channel[] channels
    ) {
        this.companyName = TVCompanies.LG;
        this.channels = channels;
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

    // HW5_ADDITIONAL
    public int getActiveChannel() {
        return activeChannelNumber;
    }
    public int getVolume() {
        return volumeValue;
    }
    public boolean getIsTVActive() {
        return isTVActive;
    }

    // HW6
    public Channel[] getChannels() { return channels; };

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

    // HW5_ADDITIONAL
    public void setActiveChannelNumber(int activeChannelNumber) {
        this.activeChannelNumber = activeChannelNumber;
    }
    public void setVolumeValue(int volumeValue) throws Exception {
        if (volumeValue > 100 || volumeValue < 0) {
            throw new Exception(
                "Уровень громкости не может превышать значение 100 и быть меньше 0!"
            );
        };

        this.volumeValue = volumeValue;
    }
    public void setTVActive(boolean isTVActive) {
        this.isTVActive = isTVActive;
    }

    // HW6
    public void setChannels(Channel[] channels) {
        this.channels = channels;
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

    // HW5_ADDITIONAL
    @Override
    public String toString() {
        return "Название класса: " + this.getClass().getSimpleName() + ";\n" +
            "Поля класса для проверки 5 домашки: " + "\n" +
            "companyName: " + this.companyName + "; " + "\n" +
            "isTVActive: " + this.isTVActive + "; " + "\n" +
            "volumeValue: " + this.volumeValue + "; " + "\n" +
            "activeChannelNumber: " + this.activeChannelNumber + "; " + "\n" +
            "channels: " + Arrays.toString(this.channels) + ".";
    }

    // HW5_ADDITIONAL - equals & hashCode сгенерировал только по вновь добавленным в HW5Additional полям
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return activeChannelNumber == tv.activeChannelNumber
            && volumeValue == tv.volumeValue
            && isTVActive == tv.isTVActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeChannelNumber, volumeValue, isTVActive);
    }

    // HW5_ADDITIONAL - наполнение коллекции из заданного числа телевизоров с помощью уже реализованных сеттеров и проверок
    public static TV[] getTVCollection(Scanner scanner, int tvAmount) throws Exception {
        TV[] tvCollection = new TV[tvAmount];

        for (int i = 0; i < tvAmount; i++) {
            TV newTV = new TV();

            System.out.println("Введите название компании: ");
            String companyName = scanner.nextLine();
            newTV.setCompanyName(TV.TVCompanies.valueOf(companyName.trim()));

            System.out.println("Телевизор включен: Да или Нет");
            String isTVActiveValue = scanner.nextLine();
            boolean isTVActive = isTVActiveValue.equals("Да");
            newTV.setTVActive(isTVActive);

            System.out.println("Введите уровень громкости от 0 до 100: ");
            int volumeValue = scanner.nextInt();
            newTV.setVolumeValue(volumeValue);

            System.out.println("Введите номер активного канала: ");
            int activeChannelNumber = scanner.nextInt();
            newTV.setActiveChannelNumber(activeChannelNumber);

            tvCollection[i] = newTV;

            System.out.println("Следующий экземпляр TV добавлен в коллекцию: " + "\n" + newTV);

            scanner.nextLine();
        }

        // HW5_ADDITIONAL (Double additional) Реализация сортировки по возрастанию по полю activeChannelNumber
        List<TV> tvArrayList = Arrays.asList(tvCollection);
        tvArrayList.sort(new Comparator<TV>() {
            @Override
            public int compare(TV a, TV b) {
                return a.getActiveChannel() - b.getActiveChannel();
            }
        });

        TV[] sortedTVCollection = new TV[tvArrayList.size()];

        for (int i = 0; i < tvArrayList.size(); i++) {
            sortedTVCollection[i] = tvArrayList.get(i);
        }

        return sortedTVCollection;
    }

    // HW5_ADDITIONAL - Получение списка телевизоров, удовлетворяющих условиям по активности и максимальной громкости
    public static ArrayList<TV> getActiveTVCollectionWithAvailableVolume(TV[] tvCollection, int availableVolume) {
        ArrayList<TV> filteredTVCollection = new ArrayList<>();

        for (TV item: tvCollection) {
            if (item.isTVActive && item.volumeValue <= availableVolume)
                filteredTVCollection.add(item);
        }

        return filteredTVCollection;
    }
}
