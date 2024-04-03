package ru.innopolis.finalproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность временного слота")
public class TimeSlotDto {
    @Schema(name = "id", description = "Идентификатор временного слота", example = "1")
    private Long id;
    @Schema(name = "doctorId", description = "Идентификатор доктора", example = "1")
    private Long doctorId;
    @Schema(name = "doctorFullName", description = "Полное имя доктора", example = "Семенов Семен Семенович")
    private String doctorFullName;
    @Schema(name = "date", description = "Дата приема", example = "2024-03-25")
    private LocalDate date;
    @Schema(name = "startTime", description = "Начало приема", example = "10:00")
    private LocalTime startTime;
    @Schema(name = "endTime", description = "Окончание приема", example = "11:00")
    private LocalTime endTime;
    @Schema(name = "availability", description = "Доступность (временной слот не занят)", example = "true")
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
