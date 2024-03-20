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
import ru.innopolis.attestation03.dto.PatientDto;
import ru.innopolis.attestation03.services.PatientService;
import ru.innopolis.attestation03.utils.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.innopolis.attestation03.utils.Helper.asJsonString;

@DisplayName("PatientController testing")
@WebMvcTest(PatientController.class)
public class PatientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    private final List<PatientDto> patients = new ArrayList<>();
    Address testAddress = new Address();
    private final Long FIRST_TEST_PATIENT_ID = 1L;



    @BeforeEach
    public void init() {
        testAddress.setPermanentAddress("Воронеж");
        testAddress.setResidentialAddress("Мытищи");

        PatientDto firstPatient = new PatientDto();
        firstPatient.setId(1L);
        firstPatient.setFirstName("Петр");
        firstPatient.setLastName("Петров");
        firstPatient.setPatronymic("Петрович");
        firstPatient.setBirthdate(LocalDate.parse("1984-05-11"));
        firstPatient.setPhoneNumber(13244);
        firstPatient.setAddress(testAddress);

        PatientDto secondPatient = new PatientDto();
        secondPatient.setId(2L);
        secondPatient.setFirstName("Янина");
        secondPatient.setLastName("Абдурахманова");
        secondPatient.setPatronymic("Мафусаиловна");
        secondPatient.setBirthdate(LocalDate.parse("1974-03-18"));
        secondPatient.setPhoneNumber(898989);
        secondPatient.setAddress(testAddress);

        patients.add(firstPatient);
        patients.add(secondPatient);
    }

    @AfterEach
    public void tearDown() {
        patients.clear();
    }

    @Test
    void findAllShouldReturnAllPatients() throws Exception {
        Mockito.when(this.patientService.findAll()).thenReturn(patients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findByIdShouldReturnSpecificValidPatient() throws Exception {
        Mockito.when(this.patientService.findById(FIRST_TEST_PATIENT_ID))
                .thenReturn(patients.get(0));

        mockMvc.perform(get("/patients/{patientId}", FIRST_TEST_PATIENT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Петр"))
                .andExpect(jsonPath("$.lastName").value("Петров"))
                .andExpect(jsonPath("$.patronymic").value("Петрович"))
                .andExpect(jsonPath("$.birthdate").value("1984-05-11"))
                .andExpect(jsonPath("$.phoneNumber").value(13244))
                .andExpect(jsonPath("$.address.residentialAddress").value("Мытищи"))
                .andExpect(jsonPath("$.address.permanentAddress").value("Воронеж"));
    }

    @Test
    void createShouldAddValidPatientToCollection() throws Exception {
        PatientDto newPatient = new PatientDto();
        newPatient.setId(3L);
        newPatient.setFirstName("Петро");
        newPatient.setLastName("Петровкин");
        newPatient.setPatronymic("Петровичус");
        newPatient.setBirthdate(LocalDate.parse("1989-01-12"));
        newPatient.setPhoneNumber(1111);
        newPatient.setAddress(testAddress);
        patients.add(newPatient);

        Mockito.when(this.patientService.create(newPatient)).thenReturn(newPatient);

        mockMvc.perform(
                        post("/patients/addPatient")
                                .content(asJsonString(newPatient))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.firstName").value("Петро"))
                .andExpect(jsonPath("$.lastName").value("Петровкин"))
                .andExpect(jsonPath("$.patronymic").value("Петровичус"))
                .andExpect(jsonPath("$.birthdate").value("1989-01-12"))
                .andExpect(jsonPath("$.phoneNumber").value(1111))
                .andExpect(jsonPath("$.address.residentialAddress").value("Мытищи"))
                .andExpect(jsonPath("$.address.permanentAddress").value("Воронеж"));
    }

    @Test
    void editShouldUpdatePatientFromCollection() throws Exception {
        PatientDto editedPatient = patients.get(0);
        editedPatient.setPhoneNumber(77777);

        Mockito.when(this.patientService.update(FIRST_TEST_PATIENT_ID, editedPatient))
                .thenReturn(editedPatient);

        mockMvc.perform(put("/patients/{patientId}", FIRST_TEST_PATIENT_ID)
                    .content(asJsonString(editedPatient))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phoneNumber").value(77777));

    }

    @Test
    void deleteShouldDeletePatientFromCollection() throws Exception {
        willDoNothing().given(this.patientService).deleteById(FIRST_TEST_PATIENT_ID);

        mockMvc.perform(delete("/patients/{patientId}", FIRST_TEST_PATIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted());
    }

    @Test
    void softDeleteShouldUpdateHasRemovedFlag() throws Exception {
        willDoNothing().given(this.patientService).softDeleteById(FIRST_TEST_PATIENT_ID);

        mockMvc.perform(
                        delete("/patients/softDelete/{patientId}", FIRST_TEST_PATIENT_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
