package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.attestation03.models.TimeSlot;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    @Query(value="SELECT timeSlots FROM TimeSlot timeSlots WHERE timeSlots.hasRemoved = false")
    List<TimeSlot> findAllNotRemovedTimeSlots();
}
