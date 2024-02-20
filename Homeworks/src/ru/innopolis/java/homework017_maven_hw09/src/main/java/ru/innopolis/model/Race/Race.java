package ru.innopolis.model.Race;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Race {
    private Integer distance;
    private String route;
    private Integer prize;
}
