package ru.innopolis.finalproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.finalproject.dto.AppointmentDto;
import ru.innopolis.finalproject.dto.TimeSlotDto;
import ru.innopolis.finalproject.exceptions.NotFoundException;
import ru.innopolis.finalproject.models.Appointment;
import ru.innopolis.finalproject.services.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/appointments"))
@Tag(name = "Контроллер приемов", description = "Контроллер операций с зарегистрированными приемами")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение списка записей на прием")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список записей на прием получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            )
    })
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{appointmentId}")
    @ResponseBody
    @Operation(
            summary = "Запрос информации по конкретному приему",
            description = "Задать идентификатор приема"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записи получена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Запись с данным идентификатором не найдена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<AppointmentDto> getAppointment(
            @PathVariable("appointmentId")
            @Parameter(
                    name = "id",
                    description = "Идентификатор назначенного приема",
                    example = "1"
            ) Long appointmentId
    ) {
        return ResponseEntity.ok(appointmentService.findById(appointmentId));
    }

    @GetMapping("/byDoctor/{doctorId}")
    @ResponseBody
    @Operation(
            summary = "Запрос списка приемов конретного доктора",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору доктора не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<AppointmentDto>> getAppointmentByDoctor(
            @PathVariable("doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId
    ) {
        return ResponseEntity.ok(appointmentService.findAllByDoctorId(doctorId));
    }

    @GetMapping("/byPatient/{patientId}")
    @ResponseBody
    @Operation(
            summary = "Запрос списка приемов конретного пациента",
            description = "Задать идентификатор пациента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору пациента не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<AppointmentDto>> getAppointmentByPatient(
            @PathVariable("patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId
    ) {
        return ResponseEntity.ok(appointmentService.findAllByPatientId(patientId));
    }

    @GetMapping("/byTimeSlot/{timeSlotId}")
    @ResponseBody
    @Operation(
            summary = "Запрос информации о приеме по конкретному временному слоту",
            description = "Задать идентификатор временного слота"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору временного слота не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<Appointment> getAppointmentByTimeSlot(
            @PathVariable("timeSlotId")
            @Parameter(
                    name = "timeSlotId",
                    description = "Идентификатор временного слота",
                    example = "1"
            ) Long timeSlotId
    ) {
        return ResponseEntity.ok(appointmentService.findByTimeSlotId(timeSlotId));
    }

    @GetMapping("/byDoctorAndDate")
    @ResponseBody
    @Operation(
            summary = "Запрос информации о спике приемов конкретного доктора в определенную дату",
            description = "Задать идентификатор доктора и целевую дату"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору доктора и дате не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndDate(
            @RequestParam (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId,
            @RequestParam (value = "date")
            @Parameter(
                    name = "date",
                    description = "Целевая дата",
                    example = "YYYY-MM-DD"
            ) LocalDate date
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByDoctorIdAndDate(doctorId, date)
        );
    }

    @GetMapping("/byPatientAndDate")
    @ResponseBody
    @Operation(
            summary = "Запрос информации о спике приемов конкретного пациента в определенную дату",
            description = "Задать идентификатор пациента и целевую дату"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору доктора и дате не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientAndDate(
            @RequestParam (value = "patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId,
            @RequestParam (value = "date")
            @Parameter(
                    name = "date",
                    description = "Целевая дата",
                    example = "YYYY-MM-DD"
            ) LocalDate date
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByPatientIdAndDate(patientId, date)
        );
    }

    @GetMapping("/byDoctorAndPatient")
    @ResponseBody
    @Operation(
            summary = "Запрос информации о спике приемов конкретного пациента к определенному доктору",
            description = "Задать идентификаторы пациента и доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по записям получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданным идентификаторам доктора и пациента не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorAndPatient(
            @RequestParam (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId,
            @RequestParam (value = "patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId
    ) {
        return ResponseEntity.ok(
                appointmentService.findAllByDoctorIdAndPatientId(doctorId, patientId)
        );
    }

    @PostMapping("/appointment")
    @Operation(summary = "Создание новой записи о приеме")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись на прием успешно создана",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найдена одна из необходимых связанных сущностей (доктор/пациент/временной слот) по переданному идентификатору",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<AppointmentDto> addAppointment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Новая запись на прием",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = AppointmentDto.class),
                            mediaType = "application/json")
            )
            @Valid @RequestBody AppointmentDto appointment
            ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.create(appointment));
    }

    @PutMapping("/appointment/{appointmentId}")
    @Operation(
            summary = "Редактирование записи о приеме",
            description = "Задать идентификатор целевой записи на прием"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись успешно обновлена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Запись по заданному идентификатору не найдена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<AppointmentDto> editAppointment(
            @PathVariable("appointmentId")
            @Parameter(
                    description = "Идентификатор приема"
            ) Long appointmentId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Отредактированная запись на прием",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = AppointmentDto.class))
            )
            @Valid @RequestBody AppointmentDto appointment
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.update(appointmentId, appointment));
    }

    @DeleteMapping("/{appointmentId}")
    @Operation(
            summary = "Удаление записи о приеме",
            description = "Задать идентификатор целевой записи на прием"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись на прием удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<TimeSlotDto> deleteAppointment(
            @PathVariable("appointmentId")
            @Parameter(
                    name = "appointmentId",
                    description = "Идентификатор назначенного приема",
                    example = "1"
            ) Long appointmentId
    ) {
        appointmentService.deleteById(appointmentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{appointmentId}")
    @Operation(
            summary = "Обратимое удаление записи о приеме",
            description = "Задать идентификатор целевой записи на прием"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись на прием удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppointmentDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<?> softDeleteAppointment(
            @PathVariable("appointmentId")
            @Parameter(
                    name = "appointmentId",
                    description = "Идентификатор назначенного приема",
                    example = "1"
            ) Long appointmentId
    ) {
        appointmentService.softDeleteById(appointmentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
