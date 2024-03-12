package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.models.TimeSlot;

import java.io.IOException;
import java.util.List;

public interface TimeSlotService {
    /**
     * Выгрузка всех окон записи из БД
     * @return List<TimeSlot>
     */
    List<TimeSlot> findAll();

    /**
     * Поиск окна записи в БД по идентификатору
     * @return TimeSlot
     */
    TimeSlot findById(String id);

    /**
     * Создание сущности окна записи и запись его в БД
     * @return void
     */
    void create(TimeSlot timeSlot);

    /**
     * Обновление полей существующего в БД окна записи
     * @return void
     */
    void update(TimeSlot timeSlot);

    /**
     * Удаление окна записи из БД по идентификатору
     * @return void
     */
    void deleteById(String id);

    /**
     * Удаление всех окон записи из БД
     * @return void
     */
    void deleteAll() throws IOException;
}
