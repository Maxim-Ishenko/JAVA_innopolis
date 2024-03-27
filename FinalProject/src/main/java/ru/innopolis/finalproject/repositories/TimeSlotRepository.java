package ru.innopolis.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.finalproject.models.TimeSlot;


import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    @Query(value="SELECT timeSlots FROM TimeSlot timeSlots WHERE timeSlots.hasRemoved = false")
    List<TimeSlot> findAllNotRemovedTimeSlots();

    List<TimeSlot> findAllByHasRemovedFalseAndDoctorId(Long doctorId);

    List<TimeSlot> findAllByHasRemovedFalseAndAvailabilityTrueAndDoctorId(
            Long doctorId
    );

    List<TimeSlot> findAllByHasRemovedFalseAndAvailabilityTrueAndDoctorIdAndDateBetween(
            Long doctorId, LocalDate to, LocalDate from
    );
}
