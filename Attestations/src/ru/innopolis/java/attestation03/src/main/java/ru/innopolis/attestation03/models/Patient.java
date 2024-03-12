package ru.innopolis.attestation03.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.utils.Address;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="patient")
public class Patient {
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="patronymic")
    private String patronymic;

    @Temporal(TemporalType.DATE)
    private String birthdate;

    private String gender;

    @Column(name="phone_number", unique = true, nullable = false)
    private Long phoneNumber;

    private Long email;
    private Address address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;

    @Column(name="has_removed")
    private Boolean hasRemoved;
}
