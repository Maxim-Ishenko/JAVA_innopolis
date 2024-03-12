package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
