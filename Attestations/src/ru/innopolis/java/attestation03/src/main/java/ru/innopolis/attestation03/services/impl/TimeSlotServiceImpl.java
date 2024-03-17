package ru.innopolis.attestation03.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.exceptions.NotFoundException;
import ru.innopolis.attestation03.models.TimeSlot;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.repositories.TimeSlotRepository;
import ru.innopolis.attestation03.services.TimeSlotService;

import java.time.LocalDate;
import java.util.List;
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
     * @param id - Идентификатор временного слота
     * @return TimeSlotDto
     */
    @Override
    public TimeSlotDto findById(Long id) {
        try {
            TimeSlot targetTimeSlot = timeSlotRepository.findById(id).orElseThrow(NotFoundException::new);

            if (targetTimeSlot.getHasRemoved()) {
                throw new NotFoundException();
            }

            return from(targetTimeSlot);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId - Идентификатор доктора
     * @param timeSlot - Идентификатор временного слота
     * @return TimeSlotDto
     */
    @Override
    public TimeSlotDto create(Long doctorId, TimeSlot timeSlot) {
        try {
            return from(doctorRepository
                    .findById(doctorId)
                    .map(currentDoctor -> {
                        timeSlot.setDoctor(currentDoctor);
                        timeSlot.setHasRemoved(false);

                        return timeSlotRepository.save(timeSlot);
                    })
                    .orElseThrow((Supplier<RuntimeException>) ()
                            -> new CustomException(ResultsMessages.DOCTOR_NOT_FOUND)));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param timeSlotId - Идентификатор временного слота
     * @param editedTimeSlotEntity - Объект временного слота
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

            return from(timeSlotRepository.save(timeSlot));
        } catch (CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param id - Идентификатор временного слота
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
     * @param id - Идентификатор временного слота
     */
    @Override
    public void softDeleteById(Long id) {
        try {
            TimeSlot timeSlot = timeSlotRepository.findById(id).orElseThrow(NotFoundException::new);

            timeSlot.setHasRemoved(true);
            from(timeSlotRepository.save(timeSlot));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId - Идентификатор доктора
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAllByDoctorId(Long doctorId) {
        try {
            return timeSlotRepository
                    .findAllByHasRemovedFalseAndDoctorId(doctorId)
                    .stream()
                    .map(TimeSlotDto::from)
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId - Идентификатор доктора
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAllAvailableByDoctorId(Long doctorId) {
        try {
            return timeSlotRepository
                    .findAllByHasRemovedFalseAndAvailabilityTrueAndDoctorId(doctorId)
                    .stream()
                    .map(TimeSlotDto::from)
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId - Идентификатор доктора
     * @param to - Врехняя граница целевого диапазона дат
     * @param from - Нижняя граница целевого диапазона дат
     * @return List<TimeSlotDto>
     */
    @Override
    public List<TimeSlotDto> findAllAvailableByDoctorIdAndDateRange(Long doctorId, LocalDate to, LocalDate from) {
        try {
                return timeSlotRepository
                        .findAllByHasRemovedFalseAndAvailabilityTrueAndDoctorIdAndDateBetween(
                                doctorId, to, from
                        )
                        .stream()
                        .map(TimeSlotDto::from)
                        .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }
}
