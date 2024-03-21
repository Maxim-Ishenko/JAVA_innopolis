package ru.innopolis.attestation03.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.services.impl.DoctorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName(value = "DoctorService testing")
@SpringBootTest
public class DoctorServiceTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorRepository);

    private final List<Doctor> doctors = new ArrayList<>();
    private Doctor firstDoctor;
    private Doctor secondDoctor;
    private final Long FIRST_TEST_DOCTOR_ID = 1L;

    @BeforeEach
    public void init() {
        firstDoctor = new Doctor();
        firstDoctor.setId(FIRST_TEST_DOCTOR_ID);
        firstDoctor.setSpeciality(1);
        firstDoctor.setFirstName("Геннадий");
        firstDoctor.setLastName("Малахоу");
        firstDoctor.setPatronymic("Петрович");
        firstDoctor.setPhoneNumber(4558L);
        firstDoctor.setHasRemoved(false);

        secondDoctor = new Doctor();
        secondDoctor.setId(2L);
        secondDoctor.setSpeciality(2);
        secondDoctor.setFirstName("Зинаида");
        secondDoctor.setLastName("Семенова");
        secondDoctor.setPatronymic("Петровна");
        secondDoctor.setPhoneNumber(1238L);
        secondDoctor.setHasRemoved(true);

        doctors.add(firstDoctor);
        doctors.add(secondDoctor);
    }

    @AfterEach
    public void tearDown() {
        doctors.clear();
    }

    @Test
    void findAllShouldReturnAllDoctorsByHasRemovedFlag() throws Exception {
        Mockito.when(
                doctorRepository.findAllByHasRemovedFalse())
                .thenReturn(doctors.stream().filter(doctor -> !doctor.getHasRemoved()).toList());

        List<DoctorDto> foundDoctors = doctorService.findAll();

        Assertions.assertNotNull(foundDoctors);
        Assertions.assertNotEquals(doctors.size(), foundDoctors.size());
    }

    @Test
    void findByIdShouldReturnExceptionIfNotExists() {
        Mockito.when(doctorRepository.findById(47L)).thenThrow(CustomException.class);

        Assertions.assertThrows(CustomException.class, () -> doctorService.findById(47L));
    }
}
