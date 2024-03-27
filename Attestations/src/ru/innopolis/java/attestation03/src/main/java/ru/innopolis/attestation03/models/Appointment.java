package ru.innopolis.attestation03.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.enums.ServiceType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;

    @Enumerated(value = EnumType.STRING)
    @Column(name="service_type", nullable = false)
    private ServiceType serviceType;

    @Column(name="has_removed")
    private Boolean hasRemoved;
}
