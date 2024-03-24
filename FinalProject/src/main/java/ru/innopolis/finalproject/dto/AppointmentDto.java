package ru.innopolis.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.enums.ServiceType;
import ru.innopolis.finalproject.models.Appointment;
import ru.innopolis.finalproject.utils.Helper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private Long doctorId;
    private String doctorFullName;
    private Long patientId;
    private String patientFullName;
    private Long timeSlotId;
    private ServiceType serviceType;

    public static AppointmentDto from(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .doctorId(appointment.getDoctor().getId())
                .doctorFullName(
                        Helper.getFullName(
                                appointment.getDoctor().getLastName(),
                                appointment.getDoctor().getFirstName(),
                                appointment.getDoctor().getPatronymic()
                        )
                )
                .patientId(appointment.getPatient().getId())
                .patientFullName(
                        Helper.getFullName(
                                appointment.getPatient().getLastName(),
                                appointment.getPatient().getFirstName(),
                                appointment.getPatient().getPatronymic()
                        )
                )
                .timeSlotId(appointment.getTimeSlot().getId())
                .serviceType(appointment.getServiceType())
                .build();
    }

    public static List<AppointmentDto> from(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentDto::from).collect(Collectors.toList());
    }
}
