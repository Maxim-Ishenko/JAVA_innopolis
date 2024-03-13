package ru.innopolis.attestation03.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.exceptions.NotFoundException;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.services.DoctorService;

import static ru.innopolis.attestation03.dto.DoctorDto.from;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    /**
     * @return List<DoctorDto>
     */
    @Override
    public List<DoctorDto> findAll() {
        try {
            return from(doctorRepository.findAllNotRemovedDoctors());
        } catch(CustomException err) {
            throw new CustomException(ResultsMessages.DOCTOR_LIST_REQ_ERR);
        }
    }

    /**
     * @param id
     * @return Doctor
     */
    @Override
    public DoctorDto findById(Long id) {
        try {
           Doctor targetDoctor = doctorRepository.findById(id).orElseThrow(NotFoundException::new);

           if (targetDoctor.getHasRemoved()) {
               throw new NotFoundException();
           }

           return from(targetDoctor);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }


    /**
     * @param doctor
     * @return
     */
    @Override
    public DoctorDto create(DoctorDto doctor) {
        try {
            return from(doctorRepository.save(
                    Doctor.builder()
                            .speciality(doctor.getSpeciality())
                            .firstName(doctor.getFirstName())
                            .lastName(doctor.getLastName())
                            .patronymic(doctor.getPatronymic())
                            .phoneNumber(doctor.getPhoneNumber())
                            .appointmentList(doctor.getAppointmentList())
                            .timeSlotsList(doctor.getTimeSlotsList())
                            .hasRemoved(false)
                            .build()
                    )
            );
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(ResultsMessages.DOCTOR_CREATING_ERROR);
        }
    }

    /**
     * @param doctorId
     * @param editedDoctorEntity
     * @return
     */
    @Transactional
    @Override
    public DoctorDto update(Long doctorId, DoctorDto editedDoctorEntity) {
        try {
            Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(NotFoundException::new);

            doctor.setSpeciality(editedDoctorEntity.getSpeciality());
            doctor.setFirstName(editedDoctorEntity.getFirstName());
            doctor.setLastName(editedDoctorEntity.getLastName());
            doctor.setPatronymic(editedDoctorEntity.getPatronymic());
            doctor.setPhoneNumber(editedDoctorEntity.getPhoneNumber());
            doctor.setAppointmentList(editedDoctorEntity.getAppointmentList());
            doctor.setTimeSlotsList(editedDoctorEntity.getTimeSlotsList());

            return from(doctorRepository.save(doctor));
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
            DoctorDto doctor = findById(id);

            if (doctor == null) throw new CustomException(ResultsMessages.DOCTOR_NOT_FOUND);

            doctorRepository.deleteById(id);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @return ResultsMessages - Строка-статус
     */
    @Override
    public ResultsMessages deleteAll() {
        try {
            doctorRepository.deleteAll();
        } catch(CustomException err) {
            return ResultsMessages.DOCTORS_LIST_REMOVE_ERROR;
        }

        return ResultsMessages.DOCTORS_LIST_REMOVE_SUCCESS;
    }

    /**
     * @param id
     */
    @Override
    public void softDeleteById(Long id) {
        try {
            Doctor doctor = doctorRepository.findById(id).orElseThrow(NotFoundException::new);

            doctor.setHasRemoved(true);
            from(doctorRepository.save(doctor));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }
}
