package ru.innopolis.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.models.Doctor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {
    private Long id;
    private Integer speciality;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Long phoneNumber;

    public static DoctorDto from(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .speciality(doctor.getSpeciality())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .patronymic(doctor.getPatronymic())
                .phoneNumber(doctor.getPhoneNumber())
                .build();
    }

    public static List<DoctorDto> from(List<Doctor> doctors) {
        return doctors.stream().map(DoctorDto::from).toList();
    }
}
