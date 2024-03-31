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
import ru.innopolis.finalproject.dto.TimeSlotDto;
import ru.innopolis.finalproject.exceptions.NotFoundException;
import ru.innopolis.finalproject.models.TimeSlot;
import ru.innopolis.finalproject.services.TimeSlotService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/timeSlots"))
@Tag(name = "Контроллер временных слотов", description = "Контроллер операций с временными слотами")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение списка временных слотов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список временных слотов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TimeSlotDto.class)))
                    }
            )
    })
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsList() {
        return ResponseEntity.ok(timeSlotService.findAll());
    }

    @GetMapping("/{timeSlotId}")
    @ResponseBody
    @Operation(
            summary = "Запрос информации по конкретному временному слоту",
            description = "Задать идентификатор временного слота"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация по временному слоту получена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = TimeSlotDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Временной слот с данным идентификатором не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<TimeSlotDto> getTimeSlot(
            @PathVariable("timeSlotId")
            @Parameter(
                    name = "timeSlotId",
                    description = "Идентификатор временного слота",
                    example = "1"
            ) Long timeSlotId
    ) {
        return ResponseEntity.ok(timeSlotService.findById(timeSlotId));
    }

    @GetMapping("/doctors/{doctorId}")
    @ResponseBody
    @Operation(
            summary = "Запрос списка временных слотов по конкретному доктору",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список временных слотов по конкретному доктору получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TimeSlotDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Временных слотов по заданному доктору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<TimeSlotDto>> getAllByDoctorId(
            @PathVariable (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId
    ) {
        return ResponseEntity.ok(timeSlotService.findAllByDoctorId(doctorId));
    }

    @GetMapping("/doctors/{doctorId}/available")
    @ResponseBody
    @Operation(
            summary = "Запрос списка доступных временных слотов по конкретному доктору",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список доступных временных слотов по конкретному доктору получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TimeSlotDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доступных временных слотов по заданному доктору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<TimeSlotDto>> getAllAvailableByDoctorId(
            @PathVariable (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId
    ) {
        return ResponseEntity.ok(timeSlotService.findAllAvailableByDoctorId(doctorId));
    }

    @GetMapping("/doctors")
    @ResponseBody
    @Operation(
            summary = "Запрос списка доступных временных слотов по конкретному доктору и диапазону дат",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список доступных временных слотов по конкретному доктору и диапазону дат получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TimeSlotDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Доступных временных слотов по заданному доктору и диапазону дат не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<List<TimeSlotDto>> getAllAvailableByDoctorIdAndDateRange(
            @RequestParam (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId,
            @RequestParam (value = "to")
            @Parameter(
                    name = "to",
                    description = "Даты до",
                    example = "2024-03-31"
            ) LocalDate to,
            @RequestParam (value = "from")
            @Parameter(
                    name = "from",
                    description = "Даты от",
                    example = "2024-03-11"
            ) LocalDate from
    ) {
        return ResponseEntity.ok(
                timeSlotService.findAllAvailableByDoctorIdAndDateRange(doctorId, to, from)
        );
    }

    @PostMapping("/doctors/{doctorId}")
    @Operation(
            summary = "Создание нового временного слота",
            description = "Задать идентификатор доктора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Новый временной слот успешно создан",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = TimeSlotDto.class)) }
            )
    })
    public ResponseEntity<TimeSlotDto> addTimeSlot(
            @PathVariable (value = "doctorId")
            @Parameter(
                    name = "doctorId",
                    description = "Идентификатор доктора",
                    example = "1"
            ) Long doctorId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Информация о новом временном слоте",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = TimeSlot.class),
                            mediaType = "application/json")
            )
            @Valid @RequestBody TimeSlot timeSlot
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(timeSlotService.create(doctorId, timeSlot));
    }

    @PutMapping("/{timeSlotId}")
    @Operation(
            summary = "Обновление информации о временном слоте",
            description = "Задать идентификатор временного слота"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация о временном слоте успешно обновлена",
                    content =
                            { @Content(mediaType = "application/json", schema =
                            @Schema(implementation = TimeSlotDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Временной слот по заданному идентификатору не найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<TimeSlotDto> editTimeSlot(
            @PathVariable("timeSlotId")
            @Parameter(
                    name = "timeSlotId",
                    description = "Идентификатор временного слота",
                    example = "1"
            ) Long timeSlotId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description ="Информация о новом временном слоте",
                    required = true,
                    content = @Content(
                            schema=@Schema(implementation = TimeSlotDto.class),
                            mediaType = "application/json")
            )
            @RequestBody TimeSlotDto editedDoctorEntity
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(timeSlotService.update(timeSlotId, editedDoctorEntity));
    }

    @DeleteMapping("/{timeSlotId}")
    @Operation(
            summary = "Удаление записи о временном слоте",
            description = "Задать идентификатор целевого временного слота"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись о временном слоте удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TimeSlotDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<TimeSlotDto> deleteTimeSlot(
            @PathVariable("timeSlotId")
            @Parameter(
                    name = "timeSlotId",
                    description = "Идентификатор временного слота",
                    example = "1"
            ) Long timeSlotId
    ) {
        timeSlotService.deleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/softDelete/{timeSlotId}")
    @Operation(
            summary = "Обратимое удаление записи о временном слоте",
            description = "Задать идентификатор целевого временного слота"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись о временном слоте удалена",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TimeSlotDto.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Записей по заданному идентификатору не найдено",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = NotFoundException.class)) }
            )
    })
    public ResponseEntity<?> softDeleteTimeSlot(
            @PathVariable("timeSlotId")
            @Parameter(
                    name = "timeSlotId",
                    description = "Идентификатор временного слота",
                    example = "1"
            ) Long timeSlotId
    ) {
        timeSlotService.softDeleteById(timeSlotId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
