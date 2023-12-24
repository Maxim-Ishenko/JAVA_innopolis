package ru.innopolis.java.homework09_incapsulation_modifiers.Race;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;
import java.util.InputMismatchException;

public class DragRace extends Race {
    public DragRace() {
        super();
    }
    public DragRace(
        Integer distance,
        String route,
        Integer prize,
        Car[] participants
    ) throws IllegalArgumentException, InputMismatchException {
        super(
            distance,
            route,
            prize,
            participants
        );
    }

    @Override
    public String toString() {
        return "DragRace{" +
            "distance=" + this.getDistance() +
            ", route='" + this.getRoute() + '\'' +
            ", prize=" + this.getPrize() +
            ", participants=" + Arrays.toString(this.getParticipants()) +
        '}';
    }
}
