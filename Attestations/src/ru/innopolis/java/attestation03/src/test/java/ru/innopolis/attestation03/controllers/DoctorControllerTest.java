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

@DisplayName("DoctorController")
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
        DoctorDto firstDoctor = DoctorDto.builder()
                .id(FIRST_TEST_DOCTOR_ID)
                .speciality(1)
                .firstName("Геннадий")
                .lastName("Малахоу")
                .phoneNumber(4558L)
                .build();
        DoctorDto secondDoctor = DoctorDto.builder()
                .id(2L)
                .speciality(2)
                .firstName("Зинаида")
                .lastName("Семенова")
                .patronymic("Петровна")
                .phoneNumber(1238L)
                .build();

        doctors.add(firstDoctor);
        doctors.add(secondDoctor);
    }

    @AfterEach
    public void tearDown() {
        doctors.clear();
    }

    @DisplayName(value = "Метод findAll")
    @Test
    void findAll_ShouldReturnAllDoctors() throws Exception {
        Mockito.when(this.doctorService.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @DisplayName(value = "Метод findById")
    @Test
    void findById_ShouldReturnSpecificValidDoctor() throws Exception {
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

    @DisplayName(value = "Метод create")
    @Test
    void create_ShouldAddValidDoctorToCollection() throws Exception {
        DoctorDto newDoctor = DoctorDto.builder()
                .id(3L)
                .speciality(3)
                .firstName("Геннадий")
                .lastName("Иванов")
                .patronymic("Иванович")
                .phoneNumber(3218L)
                .build();

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

    @DisplayName(value = "Метод edit")
    @Test
    void edit_ShouldUpdateDoctorFromCollection() throws Exception {
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

    @DisplayName(value = "Метод delete")
    @Test
    void delete_ShouldDeleteDoctorFromCollection() throws Exception {
        willDoNothing().given(this.doctorService).deleteById(FIRST_TEST_DOCTOR_ID);

        mockMvc.perform(delete("/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted());
    }

    @DisplayName(value = "Метод softDelete")
    @Test
    void softDelete_ShouldUpdateHasRemovedFlag() throws Exception {
        willDoNothing().given(this.doctorService).softDeleteById(FIRST_TEST_DOCTOR_ID);

        mockMvc.perform(
                        delete("/doctors/softDelete/{doctorId}", FIRST_TEST_DOCTOR_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
