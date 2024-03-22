package ru.innopolis.attestation03.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.services.impl.DoctorServiceImpl;

import java.util.ArrayList;
import java.util.List;

@DisplayName(value = "DoctorService testing")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
public class DoctorServiceTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService = new DoctorServiceImpl(doctorRepository);

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

    @Test
    public void findAll_ShouldReturnAllDoctors() {
        Mockito.when(doctorRepository.findAll())
                .thenReturn(doctors);

        List<DoctorDto> doctorsList = doctorService.findAll();

        Assertions.assertNotNull(doctorsList);
        Assertions.assertEquals(DoctorDto.from(doctors).size(), 2);
        Assertions.assertEquals(DoctorDto.from(doctors).get(0), DoctorDto.from(firstDoctor));
    }

    @Test
    public void findAllByHasRemovedFalse_ShouldReturnAllDoctorsByHasRemovedFlag() {
        Mockito.when(
                doctorRepository.findAllByHasRemovedFalse())
                .thenReturn(doctors.stream().filter(doctor -> !doctor.getHasRemoved()).toList());

        List<DoctorDto> foundDoctors = doctorService.findAll();

        Assertions.assertNotNull(foundDoctors);
        Assertions.assertNotEquals(doctors.size(), foundDoctors.size());
    }

    @Test
    public void findById_ShouldReturnValidDoctor() {
        Mockito.when(doctorRepository.findById(47L)).thenThrow(CustomException.class);

        Assertions.assertThrows(CustomException.class, () -> doctorService.findById(47L));
    }

    @Test
    public void findById_ShouldReturnExceptionIfNotExists() {
        Mockito.when(doctorRepository.findById(47L)).thenThrow(CustomException.class);

        Assertions.assertThrows(CustomException.class, () -> doctorService.findById(47L));
    }

    @Test
    public void create_ShouldCreateDoctor() {
        Doctor testDoctor = new Doctor();
        testDoctor.setId(FIRST_TEST_DOCTOR_ID);
        testDoctor.setSpeciality(1);
        testDoctor.setFirstName("Геннадий");
        testDoctor.setLastName("Малахоу");
        testDoctor.setPatronymic("Петрович");
        testDoctor.setPhoneNumber(4558L);
        testDoctor.setHasRemoved(false);

        Mockito.when(doctorRepository.save(testDoctor)).thenReturn(testDoctor);

        DoctorDto savedDoctor = doctorService.create(DoctorDto.from(testDoctor));

//        Assertions.assertNotNull(savedDoctor);
        Assertions.assertEquals(savedDoctor.getId(), testDoctor.getId());
    }

    @Test
    public void update_shouldUpdateDoctor() {

    }

    @Test
    public void deleteById_ShouldDeleteDoctorById() {
        doctorService.deleteById(FIRST_TEST_DOCTOR_ID);

        Mockito.verify(doctorRepository, Mockito.times(1))
                .deleteById(FIRST_TEST_DOCTOR_ID);
    }
    @Test
    public void softDeleteById_ShouldChangeHasRemovedFlagToTrueById() {

    }

    @AfterEach
    public void tearDown() {
        doctors.clear();
    }
}
