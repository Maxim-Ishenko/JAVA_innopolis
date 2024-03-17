package ru.innopolis.attestation03.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="time_slot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="time_slot", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "timeSlot")
    private Appointment appointment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Temporal(TemporalType.TIME)
    @Column(name="end_time", nullable = false)
    private LocalTime endTime;

    @Column(name="availability", nullable = false, columnDefinition = "Boolean default true")
    private Boolean availability;

    @Column(name="has_removed")
    private Boolean hasRemoved;
}
