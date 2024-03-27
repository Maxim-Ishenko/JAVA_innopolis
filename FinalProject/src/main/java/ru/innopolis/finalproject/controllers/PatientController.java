package ru.innopolis.finalproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.finalproject.dto.PatientDto;
import ru.innopolis.finalproject.services.PatientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/patients"))
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatientsList() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(patientService.findById(patientId));
    }

    @PostMapping("/addPatient")
    public ResponseEntity<PatientDto> addPatient(@Valid @RequestBody PatientDto patient) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.create(patient));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> editPatient(@PathVariable("patientId") Long patientId,
                                                @RequestBody PatientDto editedDoctorEntity) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(patientService.update(patientId, editedDoctorEntity));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deleteById(patientId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{patientId}")
    public ResponseEntity<?> softDeletePatient(@PathVariable("patientId") Long patientId) {
        patientService.softDeleteById(patientId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
