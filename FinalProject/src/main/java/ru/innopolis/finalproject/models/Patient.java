package ru.innopolis.finalproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.utils.Address;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="patient")
@Schema(description = "Модель пациента")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Идентификатор пациента", example = "1")
    private Long id;

    @Column(name="first_name", nullable = false)
    @Schema(name = "firstName", description = "Имя пациента", example = "Сергей")
    private String firstName;
    @Column(name="last_name", nullable = false)
    @Schema(name = "lastName", description = "Фамилия пациента", example = "Иванов")
    private String lastName;
    @Column(name="patronymic")
    @Schema(name = "patronymic", description = "Отчество пациента", example = "Афанасьевич")
    private String patronymic;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="birthdate")
    @Schema(name = "birthdate", description = "Дата рождения пациента", example = "1988-02-17")
    private LocalDate birthdate;

    @Column(name="phone_number", nullable = false)
    @Schema(name = "phoneNumber", description = "Номер телефона пациента", example = "134534")
    private Integer phoneNumber;

    @Column(name="address")
    @Schema(name = "address", description = "Адрес пациента")
    private Address address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @Column(name="has_removed")
    @Schema(name = "address", description = "Флаг доступности", example = "false")
    private Boolean hasRemoved;
}
