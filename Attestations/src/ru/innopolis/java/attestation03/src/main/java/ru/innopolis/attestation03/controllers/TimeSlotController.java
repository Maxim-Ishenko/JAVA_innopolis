package ru.innopolis.attestation03.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.attestation03.dto.AddTimeSlotDto;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.services.TimeSlotService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/timeSlots"))
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsList() {
        return ResponseEntity.ok(timeSlotService.findAll());
    }

    @GetMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> getTimeSlot(@PathVariable("timeSlotId") Long timeSlotId) {
        return ResponseEntity.ok(timeSlotService.findById(timeSlotId));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsByDoctor(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(timeSlotService.findAllByDoctorId(doctorId));
    }

    @GetMapping("/{doctorId}/available")
    public ResponseEntity<List<TimeSlotDto>> getAvailableTimeSlotsByDoctor(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(timeSlotService.findAllAvailableByDoctorId(doctorId));
    }

    @PostMapping("/addTimeSlot")
    public ResponseEntity<TimeSlotDto> addTimeSlot(@Valid @RequestBody AddTimeSlotDto timeSlot) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(timeSlotService.create(timeSlot));
    }

    @PutMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> editTimeSlot(@PathVariable("timeSlotId") Long timeSlotId,
                                                @RequestBody TimeSlotDto editedDoctorEntity) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(timeSlotService.update(timeSlotId, editedDoctorEntity));
    }

    @DeleteMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> deleteTimeSlot(@PathVariable("timeSlotId") Long timeSlotId) {
        timeSlotService.deleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{timeSlotId}")
    public ResponseEntity<?> softDeleteTimeSlot(@PathVariable("timeSlotId") Long timeSlotId) {
        timeSlotService.softDeleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
