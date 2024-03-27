package ru.innopolis.finalproject.models;

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
public class Doctor {
    static public String DOCTOR_NOT_FOUND_EXCEPTION_MESSAGE = "Доктора с таким идентификатором не существует";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="speciality", nullable = false)
    private Integer speciality;

    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="patronymic")
    private String patronymic;

    @Column(name="phone_number", nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy = "doctor")
    private List<TimeSlot> timeSlots;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @Column(name="has_removed")
    private Boolean hasRemoved;
}
