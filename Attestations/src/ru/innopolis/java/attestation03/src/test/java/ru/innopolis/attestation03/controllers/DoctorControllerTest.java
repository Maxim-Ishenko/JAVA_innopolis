package ru.innopolis.attestation03.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.services.DoctorService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.innopolis.attestation03.utils.Helper.asJsonString;

@DisplayName("DoctorController testing")
@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DoctorService doctorService;

    private final List<DoctorDto> doctors = new ArrayList<>();
    private final Long FIRST_TEST_DOCTOR_ID = 1L;

    @BeforeEach
    public void init() {
        DoctorDto firstDoctor = new DoctorDto();
        firstDoctor.setId(FIRST_TEST_DOCTOR_ID);
        firstDoctor.setSpeciality(1);
        firstDoctor.setFirstName("Геннадий");
        firstDoctor.setLastName("Малахоу");
        firstDoctor.setPhoneNumber(4558L);

        DoctorDto secondDoctor = new DoctorDto();
        secondDoctor.setId(2L);
        secondDoctor.setSpeciality(2);
        secondDoctor.setFirstName("Зинаида");
        secondDoctor.setLastName("Семенова");
        secondDoctor.setPatronymic("Петровна");
        secondDoctor.setPhoneNumber(1238L);

        doctors.add(firstDoctor);
        doctors.add(secondDoctor);
    }

    @AfterEach
    public void tearDown() {
        doctors.clear();
    }

    @Test
    void findAllShouldReturnAllDoctors() throws Exception {
        Mockito.when(this.doctorService.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findByIdShouldReturnSpecificValidDoctor() throws Exception {
        Mockito.when(this.doctorService.findById(FIRST_TEST_DOCTOR_ID))
                .thenReturn(doctors.get(0));

        mockMvc.perform(get("/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.speciality").value(1))
                .andExpect(jsonPath("$.firstName").value("Геннадий"))
                .andExpect(jsonPath("$.lastName").value("Малахоу"))
                .andExpect(jsonPath("$.phoneNumber").value(4558));
    }

    @Test
    void createShouldAddValidDoctorToCollection() throws Exception {
        DoctorDto newDoctor = new DoctorDto();
        newDoctor.setId(3L);
        newDoctor.setSpeciality(3);
        newDoctor.setFirstName("Геннадий");
        newDoctor.setLastName("Иванов");
        newDoctor.setPatronymic("Иванович");
        newDoctor.setPhoneNumber(3218L);
        doctors.add(newDoctor);

        Mockito.when(this.doctorService.create(newDoctor)).thenReturn(newDoctor);

        mockMvc.perform(
                post("/doctors/addDoctor")
                    .content(asJsonString(newDoctor))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.speciality").value(3))
                .andExpect(jsonPath("$.firstName").value("Геннадий"))
                .andExpect(jsonPath("$.lastName").value("Иванов"))
                .andExpect(jsonPath("$.patronymic").value("Иванович"))
                .andExpect(jsonPath("$.phoneNumber").value(3218));
    }

    @Test
    void editShouldUpdateDoctorFromCollection() throws Exception {
        DoctorDto editedDoctor = doctors.get(0);
        editedDoctor.setPhoneNumber(77777L);

        Mockito.when(this.doctorService.update(FIRST_TEST_DOCTOR_ID, editedDoctor))
                .thenReturn(editedDoctor);

        mockMvc.perform(put("/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID)
                    .content(asJsonString(editedDoctor))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phoneNumber").value(77777));

    }

    @Test
    void deleteShouldDeleteDoctorFromCollection() throws Exception {
        willDoNothing().given(this.doctorService).deleteById(FIRST_TEST_DOCTOR_ID);

        mockMvc.perform(delete("/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted());
    }

    @Test
    void softDeleteShouldUpdateHasRemovedFlag() throws Exception {
        willDoNothing().given(this.doctorService).softDeleteById(FIRST_TEST_DOCTOR_ID);

        mockMvc.perform(
                        delete("/doctors/softDelete/{doctorId}", FIRST_TEST_DOCTOR_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
