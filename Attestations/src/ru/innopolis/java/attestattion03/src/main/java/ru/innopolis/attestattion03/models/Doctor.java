package ru.innopolis.attestattion03.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name="speciality")
    private Specialities speciality;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="patronymic")
    private String patronymic;

    @Column(name="office")
    private Integer office;

    @Column(name="phone_number", unique = true)
    private Long phoneNumber;
}
