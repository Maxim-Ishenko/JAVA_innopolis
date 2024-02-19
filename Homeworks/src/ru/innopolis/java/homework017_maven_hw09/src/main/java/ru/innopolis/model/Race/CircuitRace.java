package ru.innopolis.model.Race;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CircuitRace extends Race {
    private Integer laps;
}
