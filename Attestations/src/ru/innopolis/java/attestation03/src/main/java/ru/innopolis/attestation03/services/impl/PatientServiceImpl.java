package ru.innopolis.attestation03.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.attestation03.dto.PatientDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.exceptions.NotFoundException;
import ru.innopolis.attestation03.models.Patient;
import ru.innopolis.attestation03.repositories.PatientRepository;
import ru.innopolis.attestation03.services.PatientService;

import java.util.List;

import static ru.innopolis.attestation03.dto.PatientDto.from;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    /**
     * @return List<PatientDto>
     */
    @Override
    public List<PatientDto> findAll() {
        try {
            return from(patientRepository.findAllNotRemovedPatients());
        } catch(CustomException err) {
            throw new CustomException(ResultsMessages.DOCTOR_LIST_REQ_ERR);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PatientDto findById(Long id) {
        try {
            Patient targetPatient = patientRepository.findById(id).orElseThrow(NotFoundException::new);

            if (targetPatient.getHasRemoved()) {
                throw new NotFoundException();
            }

            return PatientDto.from(targetPatient);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param patient
     * @return
     */
    @Override
    public PatientDto create(PatientDto patient) {
        try {
            return from(patientRepository.save(
                    Patient.builder()
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .patronymic(patient.getPatronymic())
                            .birthdate(patient.getBirthdate())
                            .phoneNumber(patient.getPhoneNumber())
                            .address(patient.getAddress())
                            .appointment(patient.getAppointment())
                            .hasRemoved(false)
                            .build()
            ));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(ResultsMessages.PATIENT_CREATING_ERROR);
        }
    }

    /**
     * @param patientId
     * @param editedPatientEntity
     * @return
     */
    @Override
    public PatientDto update(Long patientId, PatientDto editedPatientEntity) {
        try {
            Patient patient = patientRepository.findById(patientId).orElseThrow(NotFoundException::new);

            patient.setFirstName(editedPatientEntity.getFirstName());
            patient.setLastName(editedPatientEntity.getLastName());
            patient.setPatronymic(editedPatientEntity.getPatronymic());
            patient.setBirthdate(editedPatientEntity.getBirthdate());
            patient.setPhoneNumber(editedPatientEntity.getPhoneNumber());
            patient.setAddress(editedPatientEntity.getAddress());
            patient.setAppointment(editedPatientEntity.getAppointment());

            return PatientDto.from(patientRepository.save(patient));
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
            PatientDto patient = findById(id);

            if (patient == null) throw new CustomException(ResultsMessages.PATIENT_NOT_FOUND);

            patientRepository.deleteById(id);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @return
     */
    @Override
    public ResultsMessages deleteAll() {
        try {
            patientRepository.deleteAll();
        } catch(CustomException err) {
            return ResultsMessages.PATIENTS_LIST_REMOVE_ERROR;
        }

        return ResultsMessages.PATIENTS_LIST_REMOVE_SUCCESS;
    }

    /**
     * @param id
     */
    @Override
    public void softDeleteById(Long id) {
        try {
            Patient patient = patientRepository.findById(id).orElseThrow(NotFoundException::new);

            patient.setHasRemoved(true);
            PatientDto.from(patientRepository.save(patient));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }
}
