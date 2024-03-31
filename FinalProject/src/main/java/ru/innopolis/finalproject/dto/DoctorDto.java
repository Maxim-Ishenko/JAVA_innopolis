package ru.innopolis.finalproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность доктора")
public class DoctorDto {
    @Schema(name = "id", description = "Идентификатор доктора", example = "1")
    private Long id;
    @Schema(name = "speciality", description = "Специальность", example = "2")
    private Integer speciality;
    @Schema(name = "firstName", description = "Имя", example = "Геннадий")
    private String firstName;
    @Schema(name = "lastName", description = "Фамилия", example = "Малахов")
    private String lastName;
    @Schema(name = "patronymic", description = "Отчество", example = "Петрович")
    private String patronymic;
    @Schema(name = "phoneNumber", description = "Номер телефона", example = "12345")
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
