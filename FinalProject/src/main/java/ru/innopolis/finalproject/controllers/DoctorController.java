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
import ru.innopolis.finalproject.dto.DoctorDto;
import ru.innopolis.finalproject.exceptions.NotFoundException;
import ru.innopolis.finalproject.services.DoctorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
@Tag(name = "Контроллер докторов", description = "Контроллер операций с докторами")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение списка доступных докторов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список докторов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DoctorDto.class)))
                    }
            )
    })
    public ResponseEntity<List<DoctorDto>> getDoctorsList() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{doctorId}")
    @ResponseBody
    @Operation(
            summary = "Запрос информации по конкретному доктору",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по доктору получена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = DoctorDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доктор с данным идентификатором не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<DoctorDto> getDoctor(
            @PathVariable("doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId) {
        return ResponseEntity.ok(doctorService.findById(doctorId));
    }

    @PostMapping("/addDoctor")
    @Operation(summary = "Создание новой записи о докторе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись доктора успешно создана",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = DoctorDto.class)) }
            )
    })
    public ResponseEntity<DoctorDto> addDoctor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Информация о новом докторе",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = DoctorDto.class),
                            mediaType = "application/json")
            )
            @Valid @RequestBody DoctorDto doctor
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(doctorService.create(doctor));
    }

    @PutMapping("/{doctorId}")
    @Operation(
            summary = "Редактирование записи о докторе",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись о докторе успешно обновлена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = DoctorDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доктор по заданному идентификатору не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<DoctorDto> editDoctor(
            @PathVariable("doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Отредактированная информация о докторе",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = DoctorDto.class),
                            mediaType = "application/json")
            )
            @RequestBody DoctorDto editedDoctorEntity
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(doctorService.update(doctorId, editedDoctorEntity));
    }

    @DeleteMapping("/{doctorId}")
    @Operation(
            summary = "Удаление записи о докторе",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись о докторе удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = DoctorDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доктора по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<DoctorDto> deleteDoctor(
            @PathVariable("doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId
    ) {
        doctorService.deleteById(doctorId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{doctorId}")
    @Operation(
            summary = "Обратимое удаление записи о докторе",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись о докторе удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = DoctorDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доктора по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<?> softDeleteDoctor(
            @PathVariable("doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId
    ) {
        doctorService.softDeleteById(doctorId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
