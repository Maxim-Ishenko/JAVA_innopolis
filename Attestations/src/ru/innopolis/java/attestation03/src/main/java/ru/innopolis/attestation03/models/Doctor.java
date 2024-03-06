package ru.innopolis.attestation03.models;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name="speciality", nullable = false)
    private Specialities speciality;

    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="patronymic")
    private String patronymic;

    @Column(name="phone_number", unique = true)
    private Long phoneNumber;

    @OneToMany(mappedBy = "doctor")
    private List<TimeSlot> timeSlotsList;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;
}
