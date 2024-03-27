package ru.innopolis.finalproject.dto;

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
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthdate;
    private Integer phoneNumber;
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
