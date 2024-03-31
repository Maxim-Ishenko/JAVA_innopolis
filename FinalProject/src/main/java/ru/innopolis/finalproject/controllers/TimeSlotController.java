package ru.innopolis.finalproject.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.finalproject.dto.TimeSlotDto;
import ru.innopolis.finalproject.models.TimeSlot;
import ru.innopolis.finalproject.services.TimeSlotService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/timeSlots"))
@Tag(name = "Контроллер временных слотов", description = "Контроллер операций с временными слотами")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsList() {
        return ResponseEntity.ok(timeSlotService.findAll());
    }

    @GetMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> getTimeSlot(
            @PathVariable("timeSlotId") Long timeSlotId
    ) {
        return ResponseEntity.ok(timeSlotService.findById(timeSlotId));
    }

    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<List<TimeSlotDto>> getAllByDoctorId(
            @PathVariable (value = "doctorId") Long doctorId
    ) {
        return ResponseEntity.ok(timeSlotService.findAllByDoctorId(doctorId));
    }

    @GetMapping("/doctors/{doctorId}/available")
    public ResponseEntity<List<TimeSlotDto>> getAllAvailableByDoctorId(
            @PathVariable (value = "doctorId") Long doctorId
    ) {
        return ResponseEntity.ok(timeSlotService.findAllAvailableByDoctorId(doctorId));
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<TimeSlotDto>> getAllAvailableByDoctorIdAndDateRange(
            @RequestParam (value = "doctorId") Long doctorId,
            @RequestParam (value = "to") LocalDate to,
            @RequestParam (value = "from") LocalDate from
    ) {
        return ResponseEntity.ok(
                timeSlotService.findAllAvailableByDoctorIdAndDateRange(doctorId, to, from)
        );
    }

    @PostMapping("/doctors/{doctorId}")
    public ResponseEntity<TimeSlotDto> addTimeSlot(
            @PathVariable (value = "doctorId") Long doctorId,
            @Valid @RequestBody TimeSlot timeSlot
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(timeSlotService.create(doctorId, timeSlot));
    }

    @PutMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> editTimeSlot(
            @PathVariable("timeSlotId") Long timeSlotId,
            @RequestBody TimeSlotDto editedDoctorEntity
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(timeSlotService.update(timeSlotId, editedDoctorEntity));
    }

    @DeleteMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> deleteTimeSlot(
            @PathVariable("timeSlotId") Long timeSlotId
    ) {
        timeSlotService.deleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{timeSlotId}")
    public ResponseEntity<?> softDeleteTimeSlot(
            @PathVariable("timeSlotId") Long timeSlotId
    ) {
        timeSlotService.softDeleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
