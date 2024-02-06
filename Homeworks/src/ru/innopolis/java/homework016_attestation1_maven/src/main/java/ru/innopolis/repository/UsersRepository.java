package ru.innopolis.repository;
import ru.innopolis.model.User;

import java.io.IOException;
import java.util.List;

public interface UsersRepository {
    String USER_PARAMS_DIVIDER = "|";
    String DATA_FILE_PATH = "/users.txt";
    String ID_REQUIREMENTS = "^[a-zA-Z0-9-]{1,}$";
    String LOCAL_DATE_REQUIREMENTS = "^[0-9]{4}-((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(02)-(0[1-9]|[12][0-9]))T(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])\\.[0-9]{3}$";
    String LOGIN_REQUIREMENTS = "^[a-zA-Z0-9-_]{1,20}$";
    String PASSWORD_REQUIREMENTS = "^[a-zA-Z0-9-_]{1,20}$";
    String SURNAME_NAME_PATRONYMIC_REQUIREMENTS = "^[a-zA-Zа-яА-ЯёЁ0-9]{1,20}$";
    String LOCAL_DATE_FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
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
    Integer USERS_PARAMS_AMOUNT = 10;
    String USERS_PARAMS_INPUT_NOTIFICATION_HELP = "Введите данные пользователя в указанном формате: " + "\n" +
            "id|localDateTime|login|password|confirmPassword|surname|name|patronymic|age|isWorker" + "\n" +
            "Форматы данных: " + "\n" +
            "• id – гарантированно уникальный ID пользователя. Состоит избукв и цифр; " + "\n" +
            "• localDateTime — строка формата yyyy-mm-ddThh:mm:ss.mss; " +"\n" +
            "• login - не может быть только из цифр, содержит буквы,цифры, знак подчеркивания, меньше 20 символов; " + "\n" +
            "• password - не может быть только из букв, содержит буквы, цифры, знак подчеркивания, меньше 20символов; " + "\n" +
            "• confirmPassword - аналогично с password; " + "\n" +
            "• name, surname - строка, состоит только из букв; " + "\n" +
            "• patronymic - строка, состоит только из букв, может отсутствовать; " + "\n" +
            "• age - целое число, может отсутствовать; " + "\n" +
            "• isWorker - является ли сотрудником предприятия, по умолчанию false. " + "\n";

    /**
    * Общие методы по ТЗ
     * create - Создание пользователя и запись его в файл
     * findById - Поиск пользователя в файле по идентификатору
     * findAll - Выгрузка всех пользователей из файла
     * update - Обновление полей существующего в файле пользователя
     * deleteById - Удаление пользователя по идентификатору
     * deleteAll - Удаление всех пользователей
    */
    void create(User user);
    User findById(String id);
    List<User> findAll();
    void update(User user);
    void deleteById(String id);
    void deleteAll() throws IOException;
}
