package ru.innopolis.model.Race;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeLimitRace extends Race {
    private Integer goldTime;
}
