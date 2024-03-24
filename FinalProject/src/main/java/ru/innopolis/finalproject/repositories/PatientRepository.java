package ru.innopolis.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.finalproject.models.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value="SELECT patients FROM Patient patients WHERE patients.hasRemoved = false")
    List<Patient> findAllByHasRemovedFalse();
}
