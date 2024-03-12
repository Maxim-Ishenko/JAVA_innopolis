package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
