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
import ru.innopolis.finalproject.dto.TimeSlotDto;
import ru.innopolis.finalproject.models.Doctor;
import ru.innopolis.finalproject.models.TimeSlot;
import ru.innopolis.finalproject.services.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.innopolis.finalproject.utils.Helper.asJsonString;

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
        TimeSlotDto firstTimeSlot = TimeSlotDto.builder()
                .id(1L)
                .doctorId(1L)
                .doctorFullName("Тестов Доктор Докторович")
                .date(LocalDate.parse("2024-03-28"))
                .startTime(LocalTime.parse("10:00"))
                .endTime(LocalTime.parse("11:00"))
                .availability(true)
                .build();
        TimeSlotDto secondTimeSlot = TimeSlotDto.builder()
                .id(2L)
                .doctorId(2L)
                .doctorFullName("Тестова Фекла Павловна")
                .date(LocalDate.parse("2024-04-29"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("12:00"))
                .availability(true)
                .build();

        timeSlots.add(firstTimeSlot);
        timeSlots.add(secondTimeSlot);
    }

    @AfterEach
    public void tearDown() {
        timeSlots.clear();
    }

    @DisplayName(value = "Метод findAll")
    @Test
    void findAll_ShouldReturnAllTimeSlots() throws Exception {
        Mockito.when(this.timeSlotService.findAll()).thenReturn(timeSlots);

        mockMvc.perform(get("/timeSlots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @DisplayName(value = "Метод findById")
    @Test
    void findById_ShouldReturnSpecificValidTimeSlot() throws Exception {
        Mockito.when(this.timeSlotService.findById(FIRST_TEST_TIME_SLOT_ID))
                .thenReturn(timeSlots.get(0));

        mockMvc.perform(get("/timeSlots/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.doctorFullName").value("Тестов Доктор Докторович"));
    }

    @DisplayName(value = "Метод findAllByDoctorId")
    @Test
    void findAllByDoctorId_ShouldReturnTimeSlots_ReferencedWithSpecificDoctor() throws Exception {
        Mockito.when(this.timeSlotService.findAllByDoctorId(FIRST_TEST_DOCTOR_ID))
                .thenReturn(timeSlots.stream()
                        .filter(currentTimeSlot ->
                                Objects.equals(currentTimeSlot.getDoctorId(), FIRST_TEST_DOCTOR_ID))
                        .toList());

        mockMvc.perform(get("/timeSlots/doctors/{doctorId}", FIRST_TEST_DOCTOR_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @DisplayName(value = "Метод findAllAvailableByDoctorId")
    @Test
    void findAllAvailableByDoctorId_ShouldReturnAvailableTimeSlots_ReferencedWithSpecificDoctor()
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

    @DisplayName(value = "Метод findAllAllAvailableByDoctorIdAndDate")
    @Test
    void findAllAllAvailableByDoctorIdAndDate_RangeShouldReturnCorrectCollection()
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

    @DisplayName(value = "Метод create")
    @Test
    void create_ShouldAddValidDoctor_ToCollection() throws Exception {
        Doctor testDoctor = Doctor.builder()
                .id(FIRST_TEST_DOCTOR_ID)
                .speciality(1)
                .firstName("Доктор")
                .lastName("Тестов")
                .patronymic("Докторович")
                .phoneNumber(4558L)
                .build();
        TimeSlot testTimeSlot = TimeSlot.builder()
                .id(3L)
                .doctor(testDoctor)
                .date(LocalDate.parse("2024-04-28"))
                .startTime(LocalTime.parse("12:00"))
                .endTime(LocalTime.parse("13:00"))
                .availability(true)
                .hasRemoved(false)
                .build();

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

    @DisplayName(value = "Метод edit")
    @Test
    void edit_ShouldUpdateTimeSlot_FromCollection() throws Exception {
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

    @DisplayName(value = "Метод delete")
    @Test
    void delete_ShouldDeleteTimeSlot_FromCollection() throws Exception {
        willDoNothing().given(this.timeSlotService).deleteById(FIRST_TEST_TIME_SLOT_ID);

        mockMvc.perform(delete("/timeSlots/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted());
    }

    @DisplayName(value = "Метод softDelete")
    @Test
    void softDelete_ShouldUpdateHasRemovedFlag() throws Exception {
        willDoNothing().given(this.timeSlotService).softDeleteById(FIRST_TEST_TIME_SLOT_ID);

        mockMvc.perform(
                        delete("/timeSlots/softDelete/{timeSlotId}", FIRST_TEST_TIME_SLOT_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
