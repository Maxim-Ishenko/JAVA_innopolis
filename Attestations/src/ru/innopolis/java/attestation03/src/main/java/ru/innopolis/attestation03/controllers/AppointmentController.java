package ru.innopolis.attestation03.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.attestation03.dto.AppointmentDto;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.services.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/appointments"))
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(
            @PathVariable("appointmentId") Long appointmentId
    ) {
        return ResponseEntity.ok(appointmentService.findById(appointmentId));
    }

    @GetMapping("/byDoctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByDoctor(
            @PathVariable("doctorId") Long doctorId
    ) {
        return ResponseEntity.ok(appointmentService.findAllByDoctorId(doctorId));
    }

    @GetMapping("/byPatient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByPatient(
            @PathVariable("patientId") Long patientId
    ) {
        return ResponseEntity.ok(appointmentService.findAllByPatientId(patientId));
    }

    @GetMapping("/byTimeSlot/{timeSlotId}")
    public ResponseEntity<Appointment> getAppointmentByTimeSlot(
            @PathVariable("timeSlotId") Long timeSlotId
    ) {
        return ResponseEntity.ok(appointmentService.findByTimeSlotId(timeSlotId));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndDate(
            @RequestParam (value = "doctorId") Long doctorId,
            @RequestParam (value = "date") LocalDate date
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByDoctorIdAndDate(doctorId, date)
        );
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientAndDate(
            @RequestParam (value = "patientId") Long patientId,
            @RequestParam (value = "date") LocalDate date
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByPatientIdAndDate(patientId, date)
        );
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndPatient(
            @RequestParam (value = "doctorId") Long doctorId,
            @RequestParam (value = "patientId") Long patientId
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByDoctorIdAndPatientId(doctorId, patientId)
        );
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDto> addAppointment(
            @Valid @RequestBody AppointmentDto appointment
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.create(appointment));
    }

    @PutMapping("/appointment/{appointmentId}")
    public ResponseEntity<AppointmentDto> editAppointment(
            @PathVariable("appointmentId") Long appointmentId,
            @Valid @RequestBody AppointmentDto appointment
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.update(appointmentId, appointment));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<TimeSlotDto> deleteAppointment(
            @PathVariable("appointmentId") Long appointmentId
    ) {
        appointmentService.deleteById(appointmentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{appointmentId}")
    public ResponseEntity<?> softDeleteAppointment(
            @PathVariable("appointmentId") Long appointmentId
    ) {
        appointmentService.softDeleteById(appointmentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
