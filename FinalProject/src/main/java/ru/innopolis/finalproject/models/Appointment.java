package ru.innopolis.finalproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.finalproject.enums.ServiceType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="appointment")
@Schema(description = "Модель записи на прием")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Идентификатор записи на прием", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Schema(name = "doctorId", description = "Идентификатор доктора", example = "1")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Schema(name = "patientId", description = "Идентификатор пациента", example = "1")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    @Schema(name = "timeSlotId", description = "Идентификатор временного слота", example = "1")
    private TimeSlot timeSlot;

    @Enumerated(value = EnumType.STRING)
    @Column(name="service_type", nullable = false)
    @Schema(name = "serviceType", description = "Вид оказываемой услуги", example = "LIVE")
    private ServiceType serviceType;

    @Column(name="has_removed")
    @Schema(name = "hasRemoved", description = "Флаг доступности", example = "false")
    private Boolean hasRemoved;
}
