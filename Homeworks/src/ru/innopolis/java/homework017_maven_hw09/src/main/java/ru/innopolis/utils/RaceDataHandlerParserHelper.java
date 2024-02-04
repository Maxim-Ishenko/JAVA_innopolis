package ru.innopolis.utils;

import ru.innopolis.model.Car.Car;
import ru.innopolis.model.Race.Race;

import java.util.Objects;

public class RaceDataHandlerParserHelper {
    public Integer getRaceDistance(Integer distance) throws IllegalArgumentException {
        try {
            if (distance <= 0) {
                throw new IllegalArgumentException("Длина трассы может быть только положительным числом!");
            }

            return distance;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public String getRaceRoute(String route) throws IllegalArgumentException {
        try {
            if (Objects.equals(route, "")) {
                throw new IllegalArgumentException("Маршрут не может быть пустой строкой!");
            }

            return route;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getRacePrize(Integer prize) throws IllegalArgumentException {
        try {
            if (prize < 0) {
                throw new IllegalArgumentException("Приз не может быть отрицательным числом!");
            }

            return prize;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Car[] getRaceParticipants(Car[] participants) throws IllegalArgumentException {
        try {
            return participants;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getTimeLimitRaceGoldTime(Integer goldTime) throws IllegalArgumentException {
        try {
            if (goldTime <= 0) {
                throw new IllegalArgumentException("Значение времени должно быть положительным числом!");
            }

            return goldTime;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Integer getCircuitRaceLapsAmount(Integer laps) throws IllegalArgumentException {
        try {
            if (laps <= 0) {
                throw new IllegalArgumentException("Количество кругов должно быть положительным числом!");
            }

            return laps;
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public Race createNewRace(
            Integer distance, String route, Integer prize
    ) {
        try {
            return new Race(
                    getRaceDistance(distance),
                    getRaceRoute(route),
                    getRacePrize(prize)
            );
        } catch(IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
}
