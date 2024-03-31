package ru.innopolis.finalproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Модель временного слота")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="time_slot", nullable = false)
    @Schema(name = "id", description = "Идентификатор временного слота", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Schema(name = "doctor_id", description = "Идентификатор доктора", example = "1")
    private Doctor doctor;

    @OneToOne(mappedBy = "timeSlot")
    private Appointment appointment;

    @Temporal(TemporalType.DATE)
    @Schema(name = "date", description = "Дата приема", example = "2024-03-25")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name="start_time", nullable = false)
    @Schema(name = "start_time", description = "Начало приема", example = "10:00")
    private LocalTime startTime;

    @Temporal(TemporalType.TIME)
    @Column(name="end_time", nullable = false)
    @Schema(name = "end_time", description = "Окончание приема", example = "11:00")
    private LocalTime endTime;

    @Column(name="availability", nullable = false, columnDefinition = "Boolean default true")
    @Schema(name = "availability", description = "Доступность (временной слот не занят)", example = "true")
    private Boolean availability;

    @Column(name="has_removed")
    @Schema(name = "availability", description = "Флаг доступности (временной слот не удален)", example = "false")
    private Boolean hasRemoved;
}
