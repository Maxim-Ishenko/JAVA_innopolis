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
import org.springframework.web.bind.annotation.*;
import ru.innopolis.finalproject.dto.AppointmentDto;
import ru.innopolis.finalproject.dto.PatientDto;
import ru.innopolis.finalproject.exceptions.NotFoundException;
import ru.innopolis.finalproject.services.PatientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
@Tag(name = "Контроллер пациентов", description = "Контроллер операций с пациентами")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение списка пациентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список пациентов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PatientDto.class)))
                    }
            )
    })
    public ResponseEntity<List<PatientDto>> getPatientsList() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{patientId}")
    @ResponseBody
    @Operation(
            summary = "Запрос информации по конкретному пациенту",
            description = "Задать идентификатор пациента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по пациенту получена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = PatientDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пациент с данным идентификатором не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<PatientDto> getPatient(
            @PathVariable("patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId
    ) {
        return ResponseEntity.ok(patientService.findById(patientId));
    }

    @PostMapping("/addPatient")
    @Operation(summary = "Создание новой записи о пациенте")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись пациента успешно создана",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = PatientDto.class)) }
            )
    })
    public ResponseEntity<PatientDto> addPatient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Информация о новом пациенте",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = PatientDto.class),
                            mediaType = "application/json")
            )
            @Valid @RequestBody PatientDto patient
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.create(patient));
    }

    @PutMapping("/{patientId}")
    @Operation(summary = "Обновление записи о пациенте")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись пациента успешно обновлена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = PatientDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пациент с заданным индентификатором не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<PatientDto> editPatient(
            @PathVariable("patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Отредактированная информация о пациенте",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = PatientDto.class))
            )
            @Valid @RequestBody PatientDto editedDoctorEntity
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(patientService.update(patientId, editedDoctorEntity));
    }

    @DeleteMapping("/{patientId}")
    @Operation(
            summary = "Удаление записи о пациенте",
            description = "Задать идентификатор пациента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись пациента удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PatientDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пациентов по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<PatientDto> deletePatient(
            @PathVariable("patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId
    ) {
        patientService.deleteById(patientId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{patientId}")
    @Operation(
            summary = "Обратимое удаление записи о пациенте",
            description = "Задать идентификатор пациента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись пациента удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = PatientDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пациентов по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<?> softDeletePatient(
            @PathVariable("patientId")
            @Parameter(
                    name = "patientId",
                    description = "Идентификатор пациента",
                    example = "1"
            ) Long patientId
    ) {
        patientService.softDeleteById(patientId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
