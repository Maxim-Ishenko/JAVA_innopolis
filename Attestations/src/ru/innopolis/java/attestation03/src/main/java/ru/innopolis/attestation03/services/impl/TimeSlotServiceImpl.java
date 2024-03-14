package ru.innopolis.attestation03.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.attestation03.dto.AddTimeSlotDto;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.exceptions.NotFoundException;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.TimeSlot;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.repositories.TimeSlotRepository;
import ru.innopolis.attestation03.services.TimeSlotService;

import javax.print.Doc;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static ru.innopolis.attestation03.dto.TimeSlotDto.from;


@RequiredArgsConstructor
@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    private final DoctorRepository doctorRepository;

    /**
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAll() {
        try {
            return from(timeSlotRepository.findAllNotRemovedTimeSlots());
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(ResultsMessages.TIMESLOT_LIST_REQ_ERR);
        }
    }

    /**
     * @param id
     * @return TimeSlotDto
     */
    @Override
    public TimeSlotDto findById(Long id) {
        try {
            TimeSlot targetTimeSlot = timeSlotRepository.findById(id).orElseThrow(NotFoundException::new);

            if (targetTimeSlot.getHasRemoved()) {
                throw new NotFoundException();
            }

            return TimeSlotDto.from(targetTimeSlot);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param timeSlot
     * @return TimeSlotDto
     */
    @Override
    public TimeSlotDto create(AddTimeSlotDto timeSlot) {
        try {
            Doctor doctor = doctorRepository
                    .findById(timeSlot.getDoctorId())
                    .orElseThrow((Supplier<RuntimeException>) ()
                        -> new CustomException(ResultsMessages.DOCTOR_NOT_FOUND));

            TimeSlot newTimeSlot = TimeSlot.builder()
                    .doctor(doctor)
                    .date(timeSlot.getDate())
                    .startTime(timeSlot.getStartTime())
                    .endTime(timeSlot.getEndTime())
                    .availability(true)
                    .build();

            return TimeSlotDto.from(timeSlotRepository.save(newTimeSlot));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param timeSlotId
     * @param editedTimeSlotEntity
     * @return TimeSlotDto
     */
    @Override
    public TimeSlotDto update(Long timeSlotId, TimeSlotDto editedTimeSlotEntity) {
        try {
            TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId).orElseThrow(NotFoundException::new);

            timeSlot.setDate(editedTimeSlotEntity.getDate());
            timeSlot.setStartTime(editedTimeSlotEntity.getStartTime());
            timeSlot.setEndTime(editedTimeSlotEntity.getEndTime());
            timeSlot.setAvailability(editedTimeSlotEntity.getAvailability());
            timeSlot.setDoctor(editedTimeSlotEntity.getDoctor());
            timeSlot.setAppointment(editedTimeSlotEntity.getAppointment());

            return from(timeSlotRepository.save(timeSlot));
        } catch (CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        try {
            TimeSlotDto timeSlot = findById(id);

            if (timeSlot == null) throw new CustomException(ResultsMessages.TIMESLOT_NOT_FOUND);

            timeSlotRepository.deleteById(id);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @return ResultsMessages
     */
    @Override
    public ResultsMessages deleteAll() {
        try {
            timeSlotRepository.deleteAll();

            return ResultsMessages.TIMESLOTS_LIST_REMOVE_SUCCESS;
        } catch(CustomException err) {
            err.getStackTrace();
            return ResultsMessages.TIMESLOTS_LIST_REMOVE_ERROR;
        }
    }

    /**
     * @param id
     */
    @Override
    public void softDeleteById(Long id) {
        try {
            TimeSlot timeSlot = timeSlotRepository.findById(id).orElseThrow(NotFoundException::new);

            timeSlot.setHasRemoved(true);
            TimeSlotDto.from(timeSlotRepository.save(timeSlot));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAllByDoctorId(Long doctorId) {
        try {
            return timeSlotRepository
                    .findAllNotRemovedTimeSlots()
                    .stream()
                    .map(TimeSlotDto::from)
                    .filter(currentTimeSlot ->
                            Objects.equals(currentTimeSlot.getDoctor().getId(), doctorId))
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAllAvailableByDoctorId(Long doctorId) {
        try {
            return timeSlotRepository
                    .findAllNotRemovedTimeSlots()
                    .stream()
                    .map(TimeSlotDto::from)
                    .filter(currentTimeSlot ->
                            Objects.equals(currentTimeSlot.getDoctor().getId(), doctorId)
                            && currentTimeSlot.getAvailability())
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }
}
