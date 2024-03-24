package ru.innopolis.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.models.TimeSlot;
import ru.innopolis.finalproject.utils.Helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotDto {
    private Long id;
    private Long doctorId;
    private String doctorFullName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean availability;

    public static TimeSlotDto from(TimeSlot timeSlot) {
        return TimeSlotDto.builder()
                .id(timeSlot.getId())
                .doctorId(timeSlot.getDoctor().getId())
                .doctorFullName(
                        Helper.getFullName(
                                timeSlot.getDoctor().getLastName(),
                                timeSlot.getDoctor().getFirstName(),
                                timeSlot.getDoctor().getPatronymic()
                        )
                )
                .date(timeSlot.getDate())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .availability(timeSlot.getAvailability())
                .build();
    }

    public static List<TimeSlotDto> from(List<TimeSlot> timeSlots) {
        return timeSlots.stream().map(TimeSlotDto::from).toList();
    }
}
