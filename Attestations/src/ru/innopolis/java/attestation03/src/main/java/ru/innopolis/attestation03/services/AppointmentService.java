package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.models.Appointment;

import java.io.IOException;
import java.util.List;

public interface AppointmentService {
    /**
     * Выгрузка всех приемов из БД
     * @return List<Appointment>
     */
    List<Appointment> findAll();

    /**
     * Поиск приема в БД по идентификатору
     * @return Appointment
     */
    Appointment findById(String id);

    /**
     * Создание сущности приема и запись его в БД
     * @return void
     */
    void create(Appointment appointment);

    /**
     * Обновление полей существующего в БД приема
     * @return void
     */
    void update(Appointment appointment);

    /**
     * Удаление приема из БД по идентификатору
     * @return void
     */
    void deleteById(String id);

    /**
     * Удаление всех приемов из БД
     * @return void
     */
    void deleteAll() throws IOException;
}
