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
    @Column(name="birthdate")
    private LocalDate birthdate;

    @Column(name="phone_number", unique = true, nullable = false)
    private Integer phoneNumber;

    @Column(name="address")
    private Address address;

    @OneToMany(mappedBy = "patient")
    @Column(name="appointments")
    private List<Appointment> appointment;

    @Column(name="has_removed")
    private Boolean hasRemoved;
}
