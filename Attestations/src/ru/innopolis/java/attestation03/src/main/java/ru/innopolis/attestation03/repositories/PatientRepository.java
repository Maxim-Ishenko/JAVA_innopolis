package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.attestation03.models.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value="SELECT patients FROM Patient patients WHERE patients.hasRemoved = false")
    List<Patient> findAllByHasRemovedFalse();
}
