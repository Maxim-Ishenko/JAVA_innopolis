package ru.innopolis.finalproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.finalproject.dto.DoctorDto;
import ru.innopolis.finalproject.services.DoctorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/doctors"))
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctorsList() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(doctorService.findById(doctorId));
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorDto> addDoctor(@Valid @RequestBody DoctorDto doctor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(doctorService.create(doctor));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> editDoctor(@PathVariable("doctorId") Long doctorId,
                                                  @RequestBody DoctorDto editedDoctorEntity) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(doctorService.update(doctorId, editedDoctorEntity));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> deleteDoctor(@PathVariable("doctorId") Long doctorId) {
        doctorService.deleteById(doctorId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{doctorId}")
    public ResponseEntity<?> softDeleteDoctor(@PathVariable("doctorId") Long doctorId) {
        doctorService.softDeleteById(doctorId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
