package ru.innopolis.java.attestation01.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.utils.CustomExceptions.UserNotFoundException;
import ru.innopolis.java.attestation01.utils.Helpers.FileWorkHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ru.innopolis.java.attestation01.utils.Helpers.DataHandlerParserHelper.setUsersCollectionFromTheFile;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersRepositoryImplTest {
    private final String ABSOLUTE_FILE_PATH =
            "C:/Users/Максим/Documents/Java_School_Innopolis/JavaProjects/JAVA_innopolis/Attestations/src/ru/innopolis/java/attestation01/resources/users.txt";
    private final String TEST_USER_STRING = "testId|2021-11-21T12:14:16.000|testLogin|testPassword|testPassword|testSur|testName|testPatr|37|true";
    private final String TEST_WRONG_USER_STRING = "testId|null|testLogin|testPassword|testPassword|testSur|testName|testPatr|37|true";
    private final String TEST_USER_ID = "testId";
    private final User TEST_USER = new User(
            "testId",
            LocalDateTime.of(2021, 11, 21, 12, 14, 16, 0),
            "testLogin",
            "testPassword",
            "testPassword",
            "testSur",
            "testName",
            "testPatr",
            37,
            true
    );
    private final User TEST_UPDATED_USER = new User(
            "testId",
            LocalDateTime.of(2021, 11, 21, 12, 14, 16, 0),
            "testLogin",
            "testPassword",
            "testPassword",
            "testUpdateSur",
            "testUpdateName",
            "testUpdatePatr",
            37,
            true
    );

    private final UsersRepositoryImpl USER_METHODS_STORE = new UsersRepositoryImpl();

    @AfterEach
    void tearDown(TestInfo info) throws IOException {
        if(!info.getTags().contains("cleanItUp")) return;

        ArrayList<User> usersCollection = setUsersCollectionFromTheFile(ABSOLUTE_FILE_PATH);
        List<User> cleanedUsersCollection = usersCollection.stream().filter(
                user -> !Objects.equals(TEST_USER_ID, user.getId())
        ).toList();
        new FileOutputStream(ABSOLUTE_FILE_PATH).close();
        for (User user : cleanedUsersCollection) {
            FileWorkHelper.writeTheUserToTheFile(user.toString(), ABSOLUTE_FILE_PATH, false);
        }
    }

    @Test
    @Tag("cleanItUp")
    @DisplayName("Тест записи нового пользователя в файл")
    void create() {
        USER_METHODS_STORE.create(TEST_USER, ABSOLUTE_FILE_PATH);
        ArrayList<User> usersCollection = setUsersCollectionFromTheFile(ABSOLUTE_FILE_PATH);
        User targetUser = usersCollection.stream().filter(user -> Objects.equals(TEST_USER_ID, user.getId()))
                .findAny()
                .orElse(null);

        assertNotNull(targetUser);
        assertEquals(targetUser, TEST_USER);
    }

    @ParameterizedTest
    @CsvSource({ TEST_USER_ID })
    @Tag("cleanItUp")
    @DisplayName("Тест поиска пользователя по идентификатору")
    void findById(String testId) {
        FileWorkHelper.writeTheUserToTheFile(
                TEST_USER_STRING,
                ABSOLUTE_FILE_PATH,
                true
        );
        User targetUser = USER_METHODS_STORE.findById(testId, ABSOLUTE_FILE_PATH);

        assertNotNull(targetUser);
        assertEquals(targetUser, TEST_USER);

        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
            USER_METHODS_STORE.findById(TEST_USER_ID + "8", ABSOLUTE_FILE_PATH);
        });
        Assertions.assertEquals(
                "Пользователя с заданным идентификатором не существует",
                thrown.getMessage()
        );
    }

    @Test
    @Tag("cleanItUp")
    @DisplayName("Тест получения всех пользователей из файла")
    void findAll() {
        FileWorkHelper.writeTheUserToTheFile(
                TEST_USER_STRING,
                ABSOLUTE_FILE_PATH,
                true
        );
        List<User> usersList = USER_METHODS_STORE.findAll(ABSOLUTE_FILE_PATH);

        assertFalse(usersList.isEmpty());
    }

    @Test
    @Tag("cleanItUp")
    @DisplayName("Тест обновления пользователя")
    void update() {
        FileWorkHelper.writeTheUserToTheFile(
                TEST_USER_STRING,
                ABSOLUTE_FILE_PATH,
                true
        );
        USER_METHODS_STORE.update(TEST_UPDATED_USER, ABSOLUTE_FILE_PATH);
        User targetUser = USER_METHODS_STORE.findById(TEST_USER_ID, ABSOLUTE_FILE_PATH);

        assertNotNull(targetUser);
        assertEquals(targetUser, TEST_UPDATED_USER);
    }

    @Test
    @DisplayName("Тест удаления пользователя по идентификатору")
    void deleteById() {
        FileWorkHelper.writeTheUserToTheFile(
                TEST_USER_STRING,
                ABSOLUTE_FILE_PATH,
                true
        );
        USER_METHODS_STORE.deleteById(TEST_USER_ID, ABSOLUTE_FILE_PATH);

        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
            USER_METHODS_STORE.findById(TEST_USER_ID, ABSOLUTE_FILE_PATH);
        }, "Ожидается, что после удаления пользователя при обращении к нему по id будет исключение, но этого не произошло");
        Assertions.assertEquals(
                "Пользователя с заданным идентификатором не существует",
                thrown.getMessage()
        );
    }

    @Test
    @DisplayName("Тест очистки файла")
    void deleteAll() {
        FileWorkHelper.writeTheUserToTheFile(
                TEST_USER_STRING,
                ABSOLUTE_FILE_PATH,
                true
        );
        USER_METHODS_STORE.deleteAll(ABSOLUTE_FILE_PATH);
        List<User> usersList = USER_METHODS_STORE.findAll(ABSOLUTE_FILE_PATH);

        assertTrue(usersList.isEmpty());
    }
}
