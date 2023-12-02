package ru.innopolis.java.homework09_incapsulation_modifiers.Race;

import ru.innopolis.java.homework09_incapsulation_modifiers.Car.Car;

import java.util.Arrays;

public class DriftRace extends Race {
    public DriftRace() {
        super();
    }
    public DriftRace(
        Integer distance,
        String route,
        Integer prize,
        Car[] participants
    ) throws Exception {
       super(
           distance,
           route,
           prize,
           participants
       );
    }

    @Override
    public String toString() {
        return "DriftRace{" +
            "distance=" + this.getDistance() +
            ", route='" + this.getRoute() + '\'' +
            ", prize=" + this.getPrize() +
            ", participants=" + Arrays.toString(this.getParticipants()) +
        '}';
    }
}
