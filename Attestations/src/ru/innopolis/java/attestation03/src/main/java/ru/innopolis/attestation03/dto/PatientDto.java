package ru.innopolis.attestation03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.models.Patient;
import ru.innopolis.attestation03.utils.Address;

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
    private String birthdate;
    private String gender;
    private Long phoneNumber;
    private Long email;
    private Address address;
    private List<Appointment> appointment;

    public static PatientDto from(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .patronymic(patient.getPatronymic())
                .birthdate(patient.getBirthdate())
                .gender(patient.getGender())
                .phoneNumber(patient.getPhoneNumber())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .appointment(patient.getAppointment())
                .build();
    }

    public static List<PatientDto> from(List<Patient> patients) {
        return patients.stream().map(PatientDto::from).collect(Collectors.toList());
    }
}
