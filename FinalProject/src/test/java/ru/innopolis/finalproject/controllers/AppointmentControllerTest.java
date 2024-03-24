package ru.innopolis.finalproject.controllers;

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
import ru.innopolis.finalproject.dto.AppointmentDto;
import ru.innopolis.finalproject.enums.ServiceType;
import ru.innopolis.finalproject.services.AppointmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.innopolis.finalproject.utils.Helper.asJsonString;

@DisplayName("AppointmentController")
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppointmentService appointmentService;

    private final List<AppointmentDto> appointments = new ArrayList<>();
    private final Long FIRST_TEST_APPOINTMENT_ID = 1L;
    private final Long FIRST_TEST_DOCTOR_ID = 1L;
    private final Long FIRST_TEST_PATIENT_ID = 1L;
    private final Long FIRST_TEST_TIME_SLOT_ID = 1L;

    @BeforeEach
    public void init() {
        AppointmentDto firstAppointment = AppointmentDto.builder()
                .id(FIRST_TEST_APPOINTMENT_ID)
                .doctorId(FIRST_TEST_DOCTOR_ID)
                .doctorFullName("Тестов Доктор Докторович")
                .patientId(FIRST_TEST_PATIENT_ID)
                .patientFullName("Петров Петр Петрович")
                .timeSlotId(FIRST_TEST_TIME_SLOT_ID)
                .serviceType(ServiceType.LIVE)
                .build();
        AppointmentDto secondAppointment = AppointmentDto.builder()
                .id(2L)
                .doctorId(2L)
                .doctorFullName("Тестов Доктор Докторович")
                .patientId(2L)
                .patientFullName("Абдурахманова Янина Мафусоиловна")
                .timeSlotId(2L)
                .serviceType(ServiceType.ONLINE)
                .build();

        appointments.add(firstAppointment);
        appointments.add(secondAppointment);
    }

    @AfterEach
    public void tearDown() {
        appointments.clear();
    }

    @DisplayName(value = "Метод findAll")
    @Test
    void findAll_ShouldReturnAllAppointments() throws Exception {
        Mockito.when(this.appointmentService.findAll()).thenReturn(appointments);

        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @DisplayName(value = "Метод findById")
    @Test
    void findById_ShouldReturnSpecificValidAppointment() throws Exception {
        Mockito.when(this.appointmentService.findById(FIRST_TEST_APPOINTMENT_ID))
                .thenReturn(appointments.get(0));

        mockMvc.perform(get("/appointments/{appointmentId}", FIRST_TEST_APPOINTMENT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.doctorFullName").value("Тестов Доктор Докторович"))
                .andExpect(jsonPath("$.patientId").value(FIRST_TEST_PATIENT_ID))
                .andExpect(jsonPath("$.patientFullName").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$.timeSlotId").value(FIRST_TEST_TIME_SLOT_ID))
                .andExpect(jsonPath("$.serviceType").value("LIVE"));
    }

    @DisplayName(value = "Метод findAllByDoctorId")
    @Test
    void findAllByDoctorId_ShouldReturnAppointments_ReferencedWithSpecificDoctor() throws Exception {
        Mockito.when(this.appointmentService.findAllByDoctorId(FIRST_TEST_DOCTOR_ID))
                .thenReturn(appointments.stream()
                        .filter(currentAppointment ->
                                Objects.equals(currentAppointment.getDoctorId(), FIRST_TEST_DOCTOR_ID))
                        .toList());

        mockMvc.perform(get("/appointments/byDoctor/{doctorId}", FIRST_TEST_DOCTOR_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @DisplayName(value = "Метод findAllByPatientId")
    @Test
    void findAllByPatientId_ShouldReturnAppointments_ReferencedWithSpecificPatient() throws Exception {
        Mockito.when(this.appointmentService.findAllByPatientId(FIRST_TEST_PATIENT_ID))
                .thenReturn(appointments.stream()
                        .filter(currentAppointment ->
                                Objects.equals(currentAppointment.getPatientId(), FIRST_TEST_PATIENT_ID))
                        .toList());

        mockMvc.perform(get("/appointments/byPatient/{patientId}", FIRST_TEST_PATIENT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @DisplayName(value = "Метод create")
    @Test
    void create_ShouldAddValidAppointment_ToCollection() throws Exception {
        AppointmentDto testAppointment = new AppointmentDto();
        testAppointment.setId(3L);
        testAppointment.setDoctorId(FIRST_TEST_DOCTOR_ID);
        testAppointment.setDoctorFullName("Тестов Доктор Докторович");
        testAppointment.setPatientId(FIRST_TEST_PATIENT_ID);
        testAppointment.setPatientFullName("Петров Петр Петрович");
        testAppointment.setTimeSlotId(FIRST_TEST_TIME_SLOT_ID);
        testAppointment.setServiceType(ServiceType.ONLINE);

        Mockito.when(this.appointmentService.create(testAppointment))
                .thenReturn(testAppointment);

        mockMvc.perform(
                        post("/appointments/appointment")
                                .content(asJsonString(testAppointment))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));
    }
}
