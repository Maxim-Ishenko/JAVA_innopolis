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
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.TimeSlot;
import ru.innopolis.attestation03.services.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.innopolis.attestation03.utils.Helper.asJsonString;

@DisplayName("TimeSlotController testing")
@WebMvcTest(TimeSlotController.class)
public class TimeSlotControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TimeSlotService timeSlotService;

    private final List<TimeSlotDto> timeSlots = new ArrayList<>();
    private final Long FIRST_TEST_DOCTOR_ID = 1L;
    private final Long FIRST_TEST_TIME_SLOT_ID = 1L;

    @BeforeEach
    public void init() {
        TimeSlotDto firstTimeSlot = new TimeSlotDto();
        firstTimeSlot.setId(1L);
        firstTimeSlot.setDoctorId(1L);
        firstTimeSlot.setDoctorFullName("Тестов Доктор Докторович");
        firstTimeSlot.setDate(LocalDate.parse("2024-03-28"));
        firstTimeSlot.setStartTime(LocalTime.parse("10:00"));
        firstTimeSlot.setEndTime(LocalTime.parse("11:00"));
        firstTimeSlot.setAvailability(true);

        TimeSlotDto secondTimeSlot = new TimeSlotDto();
        secondTimeSlot.setId(2L);
        secondTimeSlot.setDoctorId(2L);
        secondTimeSlot.setDoctorFullName("Тестова Фекла Павловна");
        firstTimeSlot.setDate(LocalDate.parse("2024-04-29"));
        secondTimeSlot.setStartTime(LocalTime.parse("11:00"));
        secondTimeSlot.setEndTime(LocalTime.parse("12:00"));
        secondTimeSlot.setAvailability(true);

        timeSlots.add(firstTimeSlot);
        timeSlots.add(secondTimeSlot);
    }

    @AfterEach
    public void tearDown() {
        timeSlots.clear();
    }

    @Test
    void findAllShouldReturnAllTimeSlots() throws Exception {
        Mockito.when(this.timeSlotService.findAll()).thenReturn(timeSlots);

        mockMvc.perform(get("/timeSlots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findByIdShouldReturnSpecificValidTimeSlot() throws Exception {
        Mockito.when(this.timeSlotService.findById(FIRST_TEST_TIME_SLOT_ID))
                .thenReturn(timeSlots.get(0));

        mockMvc.perform(get("/timeSlots/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.doctorFullName").value("Тестов Доктор Докторович"))
                .andExpect(jsonPath("$.date").value("2024-03-28"))
                .andExpect(jsonPath("$.startTime").value("10:00"))
                .andExpect(jsonPath("$.endTime").value("11:00"))
                .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    void findAllByDoctorIdShouldReturnTimeSlotsReferencedWithSpecificDoctor() throws Exception {
        Mockito.when(this.timeSlotService.findAllByDoctorId(FIRST_TEST_DOCTOR_ID))
                .thenReturn(timeSlots.stream()
                        .filter(currentTimeSlot ->
                                Objects.equals(currentTimeSlot.getDoctorId(), FIRST_TEST_DOCTOR_ID))
                        .toList());

        mockMvc.perform(get("/timeSlots/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void findAllAvailableByDoctorIdShouldReturnAvailableTimeSlotsReferencedWithSpecificDoctor()
            throws Exception {
        Mockito.when(this.timeSlotService.findAllByDoctorId(FIRST_TEST_DOCTOR_ID))
                .thenReturn(timeSlots.stream()
                        .filter(currentTimeSlot ->
                                Objects.equals(currentTimeSlot.getDoctorId(), FIRST_TEST_DOCTOR_ID)
                                && currentTimeSlot.getAvailability()
                        )
                        .toList());

        mockMvc.perform(get("/timeSlots/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void findAllAllAvailableByDoctorIdAndDateRangeShouldReturnCorrectCollection()
            throws Exception {
        List<TimeSlotDto> testedCollection = new ArrayList<>();
        testedCollection.add(timeSlots.get(0));

        Mockito.when(
                this.timeSlotService.findAllAvailableByDoctorIdAndDateRange(
                        FIRST_TEST_DOCTOR_ID,
                        LocalDate.parse("2024-03-30"),
                        LocalDate.parse("2024-03-25")
                    )).thenReturn(testedCollection);

        mockMvc.perform(get("/timeSlots/doctors")
                        .param("doctorId", String.valueOf(FIRST_TEST_DOCTOR_ID))
                        .param("to", "2024-03-30")
                        .param("from", "2024-03-25")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void createShouldAddValidDoctorToCollection() throws Exception {
        Doctor testDoctor = new Doctor();
        testDoctor.setId(FIRST_TEST_DOCTOR_ID);
        testDoctor.setSpeciality(1);
        testDoctor.setFirstName("Доктор");
        testDoctor.setLastName("Тестов");
        testDoctor.setPatronymic("Докторович");
        testDoctor.setPhoneNumber(4558L);

        TimeSlot testTimeSlot = new TimeSlot();
        testTimeSlot.setId(3L);
        testTimeSlot.setDoctor(testDoctor);
        testTimeSlot.setDate(LocalDate.parse("2024-04-28"));
        testTimeSlot.setStartTime(LocalTime.parse("12:00"));
        testTimeSlot.setEndTime(LocalTime.parse("13:00"));
        testTimeSlot.setAvailability(true);
        testTimeSlot.setHasRemoved(false);

        Mockito.when(this.timeSlotService.create(1L, testTimeSlot))
                .thenReturn(TimeSlotDto.from(testTimeSlot));

        mockMvc.perform(
                        post("/timeSlots/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID)
                                .content(asJsonString(testTimeSlot))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void editShouldUpdateTimeSlotFromCollection() throws Exception {
        TimeSlotDto editedTimeSlot = timeSlots.get(0);
        editedTimeSlot.setAvailability(false);

        Mockito.when(this.timeSlotService.update(FIRST_TEST_TIME_SLOT_ID, editedTimeSlot))
                .thenReturn(editedTimeSlot);

        mockMvc.perform(put("/timeSlots/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID)
                        .content(asJsonString(editedTimeSlot))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.availability").value(false));

    }

    @Test
    void deleteShouldDeleteTimeSlotFromCollection() throws Exception {
        willDoNothing().given(this.timeSlotService).deleteById(FIRST_TEST_TIME_SLOT_ID);

        mockMvc.perform(delete("/timeSlots/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted());
    }

    @Test
    void softDeleteShouldUpdateHasRemovedFlag() throws Exception {
        willDoNothing().given(this.timeSlotService).softDeleteById(FIRST_TEST_TIME_SLOT_ID);

        mockMvc.perform(
                        delete("/timeSlots/softDelete/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
