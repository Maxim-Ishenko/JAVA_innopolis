package ru.innopolis.finalproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.finalproject.dto.PatientDto;
import ru.innopolis.finalproject.models.Patient;
import ru.innopolis.finalproject.repositories.PatientRepository;
import ru.innopolis.finalproject.services.impl.PatientServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@DisplayName(value = "PatientServiceTest")
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
                .patronymic("Gena")
                .birthdate(LocalDate.parse("1987-11-15"))
                .phoneNumber(3234)
                .hasRemoved(false)
                .build();

        secondPatient = Patient.builder()
                .id(1L)
                .firstName("Zina")
                .lastName("Zina")
                .patronymic("Gena")
                .birthdate(LocalDate.parse("1987-11-15"))
                .phoneNumber(3234)
                .hasRemoved(false)
                .build();

        patients.add(firstPatient);
        patients.add(secondPatient);
    }

    @AfterEach
    public void tearDown() {
        patients.clear();
    }

    @DisplayName(value = "Метод findAll")
    @Test
    public void findAll_ShouldReturnPatientsList() {
        when(patientRepository.findAllByHasRemovedFalse()).thenReturn(patients);

        List<PatientDto> expected = patientService.findAll();

        assertEquals(expected, PatientDto.from(patients));
    }

    @DisplayName(value = "Метод findById")
    @Test
    public void findById_ShouldReturnSpecificPatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(firstPatient));

        PatientDto expectedPatient = patientService.findById(1L);

        assertNotNull(expectedPatient);
        assertEquals(expectedPatient, PatientDto.from(firstPatient));
    }
}
