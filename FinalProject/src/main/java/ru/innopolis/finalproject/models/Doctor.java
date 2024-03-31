package ru.innopolis.finalproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="doctor")
@Schema(description = "Модель доктора")
public class Doctor {
    static public String DOCTOR_NOT_FOUND_EXCEPTION_MESSAGE =
            "Доктора с таким идентификатором не существует";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Идентификатор доктора", example = "1")
    private Long id;

    @Column(name="speciality", nullable = false)
    @Schema(name = "speciality", description = "Специальность", example = "2")
    private Integer speciality;

    @Column(name="first_name", nullable = false)
    @Schema(name = "firstName", description = "Имя", example = "Геннадий")
    private String firstName;
    @Column(name="last_name", nullable = false)
    @Schema(name = "lastName", description = "Фамилия", example = "Малахов")
    private String lastName;
    @Column(name="patronymic")
    @Schema(name = "patronymic", description = "Отчество", example = "Петрович")
    private String patronymic;

    @Column(name="phone_number", nullable = false)
    @Schema(name = "phoneNumber", description = "Номер телефона", example = "12345")
    private Long phoneNumber;

    @OneToMany(mappedBy = "doctor")
    private List<TimeSlot> timeSlots;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @Column(name="has_removed")
    @Schema(name = "has_removed", description = "Флаг доступности", example = "false")
    private Boolean hasRemoved;
}
