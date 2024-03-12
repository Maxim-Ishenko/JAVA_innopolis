package ru.innopolis.attestation03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.enums.ServiceType;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.Patient;
import ru.innopolis.attestation03.models.TimeSlot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private Doctor doctor;
    private Patient patient;
    private TimeSlot timeSlot;
    private ServiceType serviceType;

    public static AppointmentDto from(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .doctor(appointment.getDoctor())
                .patient(appointment.getPatient())
                .timeSlot(appointment.getTimeSlot())
                .serviceType(appointment.getServiceType())
                .build();
    }

    public static List<AppointmentDto> from(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentDto::from).collect(Collectors.toList());
    }
}
