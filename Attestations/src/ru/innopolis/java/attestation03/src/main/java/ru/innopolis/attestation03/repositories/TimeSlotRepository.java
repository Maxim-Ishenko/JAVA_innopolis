package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.models.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
