package ru.innopolis.attestation03.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.attestation03.dto.PatientDto;
import ru.innopolis.attestation03.models.Patient;
import ru.innopolis.attestation03.repositories.PatientRepository;
import ru.innopolis.attestation03.services.impl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private List<Patient> patients = new ArrayList<>();
    private Patient firstPatient;
    private Patient secondPatient;

    @BeforeEach
    public void init() {
        firstPatient = Patient.builder()
                .id(1L)
                .firstName("Gena")
                .lastName("Gena")
                .build();

        secondPatient = Patient.builder()
                .id(1L)
                .firstName("Zina")
                .lastName("Zina")
                .build();

        patients.add(firstPatient);
        patients.add(secondPatient);
    }

    @AfterEach
    public void tearDown() {
        patients.clear();
    }

//    @Before
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void findAll_ShouldReturnPatientsList() {
        doReturn(patients).when(patientRepository.findAll());

        List<PatientDto> expected = patientService.findAll();

        assertEquals(expected, PatientDto.from(patients));
    }

//    @Test
//    public PatientDto findById(Long id) {
//        return null;
//    }
//
//    @Test
//    public PatientDto create(PatientDto patient) {
//        return null;
//    }
//
//    @Test
//    public PatientDto update(Long patientId, PatientDto editedPatientEntity) {
//        return null;
//    }
//
//    @Test
//    public void deleteById(Long id) {
//
//    }
//
//    @Test
//    public void softDeleteById(Long id) {
//
//    }
}
