package ru.innopolis.finalproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.models.Appointment;
import ru.innopolis.finalproject.models.Patient;
import ru.innopolis.finalproject.utils.Address;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Сущность пациента")
public class PatientDto {
    @Schema(name = "id", description = "Идентификатор пациента", example = "1")
    private Long id;
    @Schema(name = "firstName", description = "Имя пациента", example = "Сергей")
    private String firstName;
    @Schema(name = "lastName", description = "Фамилия пациента", example = "Иванов")
    private String lastName;
    @Schema(name = "patronymic", description = "Отчество пациента", example = "Афанасьевич")
    private String patronymic;
    @Schema(name = "birthdate", description = "Дата рождения пациента", example = "1988-02-17")
    private LocalDate birthdate;
    @Schema(name = "phoneNumber", description = "Номер телефона пациента", example = "134534")
    private Integer phoneNumber;
    @Schema(name = "address", description = "Адрес пациента")
    private Address address;
    private List<Appointment> appointment;

    public static PatientDto from(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .patronymic(patient.getPatronymic())
                .birthdate(patient.getBirthdate())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .build();
    }

    public static List<PatientDto> from(List<Patient> patients) {
        return patients.stream().map(PatientDto::from).collect(Collectors.toList());
    }
}
