package ru.innopolis.java.homework09_incapsulation_modifiers.Race;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;
import java.util.InputMismatchException;

public class CasualRace extends Race {
    public CasualRace() {
        super();
    }
    public CasualRace(
        Integer distance,
        String route,
        Integer prize,
        Car[] participants
    ) throws IllegalArgumentException {
        super(
            distance,
            route,
            prize,
            participants
        );
    }

    @Override
    public String toString() {
        return "CasualRace{" +
            "distance=" + this.getDistance() +
            ", route='" + this.getRoute() + '\'' +
            ", prize=" + this.getPrize() +
            ", participants=" + Arrays.toString(this.getParticipants()) +
        '}';
    }
}
