package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.models.TimeSlot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    @Query(value="SELECT timeSlots FROM TimeSlot timeSlots WHERE timeSlots.hasRemoved = false")
    List<TimeSlot> findAllNotRemovedTimeSlots();

    Page<TimeSlot> findByDoctorId(Long doctorId, Pageable pageable);
    TimeSlot findByIdAndDoctorId(Long id, Long doctorId);
}
