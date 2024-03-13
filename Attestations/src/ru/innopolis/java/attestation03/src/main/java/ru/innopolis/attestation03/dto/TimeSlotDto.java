package ru.innopolis.attestation03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotDto {
    private Long id;
    private Doctor doctor;
    private Appointment appointment;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean availability;

    public static TimeSlotDto from(TimeSlot timeSlot) {
        return TimeSlotDto.builder()
                .id(timeSlot.getId())
                .doctor(timeSlot.getDoctor())
                .appointment(timeSlot.getAppointment())
                .date(timeSlot.getDate())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .availability(timeSlot.getAvailability())
                .build();
    }

    public static List<TimeSlotDto> from(List<TimeSlot> timeSlots) {
        return timeSlots.stream().map(TimeSlotDto::from).collect(Collectors.toList());
    }
}
