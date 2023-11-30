package ru.innopolis.java.homework09_incapsulation_modifiers.Race;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;
import java.util.Objects;

public class Race {
    private Integer distance;
    private String route;
    private Integer prize;
    private Car[] participants = {};

    public Integer getDistance() {
        return distance;
    }
    public String getRoute() {
        return route;
    }
    public Integer getPrize() {
        return prize;
    }
    public Car[] getParticipants() {
        return participants;
    }

    public void setDistance(Integer distance) throws Exception {
        if (distance <= 0) {
            throw new Exception("Длина трассы может быть только положительным числом!");
        }
        this.distance = distance;
    }
    public void setRoute(String route) throws Exception {
        if (Objects.equals(route, "")) {
            throw new Exception("Маршрут не может быть пустой строкой!");
        }

        this.route = route;
    }
    public void setPrize(Integer prize) throws Exception {
        if (distance < 0) {
            throw new Exception("Приз не может быть отрицательным числом!");
        }

        this.prize = prize;
    }
    public void setParticipants(Car[] participants) {
        this.participants = participants;
    }

    public Race() {}
    public Race(
        Integer distance,
        String route,
        Integer prize,
        Car[] participants
    ) throws Exception {
        this.setDistance(distance);
        this.setPrize(prize);
        this.setRoute(route);
        this.setParticipants(participants);
    }

    @Override
    public String toString() {
        return "Race{" +
            "distance=" + distance +
            ", route='" + route + '\'' +
            ", prize=" + prize +
            ", participants=" + Arrays.toString(participants) +
            '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(getDistance(), race.getDistance())
                && Objects.equals(getRoute(), race.getRoute())
                && Objects.equals(getPrize(), race.getPrize())
                && Arrays.equals(getParticipants(), race.getParticipants());
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(getDistance(), getRoute(), getPrize());
        result = 31 * result + Arrays.hashCode(getParticipants());
        return result;
    }
}
