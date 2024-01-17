package ru.innopolis.java.attestation01.repository;

import ru.innopolis.java.attestation01.model.User;
import java.util.List;

public interface UsersRepository {
    String ID_REQUIREMENTS = "^[a-zA-Za-zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9]{1,20}$";
    String LOCAL_DATE_REQUIREMENTS = "^[0-9]{4}-((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(02)-(0[1-9]|[12][0-9]))T(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])\\.[0-9]{3}$";
    String LOGIN_REQUIREMENTS = "^[a-zA-Za-zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_]{1,20}$";
    String PASSWORD_REQUIREMENTS = "^[a-zA-Za-zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_]{1,20}$";
    String SURNAME_NAME_PATRONYMIC_REQUIREMENTS = "^[a-zA-Za-zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ]{1,20}$";
    Integer ID_INDEX = 0;
    Integer LOCAL_DATE_INDEX = 1;
    Integer LOGIN_INDEX = 2;
    Integer PASSWORD_INDEX = 3;
    Integer CONFIRM_PASSWORD_INDEX = 4;
    Integer SURNAME_INDEX = 5;
    Integer NAME_INDEX = 6;
    Integer PATRONYMIC_INDEX = 7;
    Integer AGE_INDEX = 8;
    Integer IS_WORKER_INDEX = 9;
    Integer AGE_EDGE_VALUE = 0;

    /**
    * Общие методы по ТЗ
    */
    // Создание пользователя и запись его в файл
    void create(User user);

    // Поиск пользователя в файле по идентификатору
    User findById(String id);

    // Выгрузка всех пользователей из файла
    List<User> findAll();

    // Обновление полей существующего в файле пользователя
    void update(User user);

    // Удаление пользователя по идентификатору
    void deleteById(String id);

    // Удаление всех пользователей
    void deleteAll();

    /**
    * Получение коллекции пользователей
    */


    // String[] getParsedInfoString(String infoString);

    /**
     * Методы получения сепарированных полей пользователя из строки
     */
    String getIdFromInfoString(String[] infoStringCollection);
    String getLocalDateTimeFromInfoString(String[] infoStringCollection);
    String getLoginFromInfoString(String[] infoStringCollection);
    String getPasswordFromInfoString(String[] infoStringCollection);
    String getConfirmPasswordFromInfoString(String[] infoStringCollection);
    String getSurNameFromInfoString(String[] infoStringCollection);
    String getNameFromInfoString(String[] infoStringCollection);
    String getPatronymicFromInfoString(String[] infoStringCollection);
    Integer getAgeFromInfoString(String[] infoStringCollection);
    Boolean getIsWorkerFromInfoString(String[] infoStringCollection);

    /**
    * Проверка кредов авторизации на соответствие требованиям
    */
    boolean isLoginCorrect(String login);
    boolean isPasswordCorrect(String password);
    boolean isPasswordEqualsConfirmPassword(String password, String confirmPassword);
    boolean isAuthorizationCredentialsCorrect(String login, String password, String confirmPassword);
}
