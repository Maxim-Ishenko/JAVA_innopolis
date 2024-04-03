package ru.innopolis.finalproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность записи на прием")
public class AppointmentDto {
    @Schema(name = "id", description = "Идентификатор записи на прием", example = "1")
    private Long id;
    @Schema(name = "doctorId", description = "Идентификатор доктора", example = "1")
    private Long doctorId;
    @Schema(name = "doctorFullName", description = "Полное имя доктора", example = "Семенов Семен Семенович")
    private String doctorFullName;
    @Schema(name = "patientId", description = "Идентификатор пациента", example = "1")
    private Long patientId;
    @Schema(name = "patientFullName", description = "Полное имя пациента", example = "Семенов Семен Семенович")
    private String patientFullName;
    @Schema(name = "timeSlotId", description = "Идентификатор временного слота", example = "1")
    private Long timeSlotId;
    @Schema(name = "serviceType", description = "Вид оказываемой услуги", example = "LIVE")
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
