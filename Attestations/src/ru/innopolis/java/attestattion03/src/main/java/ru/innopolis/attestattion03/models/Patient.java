package ru.innopolis.attestattion03.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="patients")
public class Patient {
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="patronymic")
    private String patronymic;

    private String birthdate;
    private String gender;

    @Column(name="phone_number", unique = true)
    private Long phoneNumber;

    private Long email;
    private Address address;
}
