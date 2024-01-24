package ru.innopolis.java.attestation01.repository;

import ru.innopolis.java.attestation01.model.User;
import ru.innopolis.java.attestation01.utils.CustomExceptions.UserNotFoundException;
import ru.innopolis.java.attestation01.utils.Helpers.FileWorkHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static ru.innopolis.java.attestation01.utils.Helpers.DataHandlerParserHelper.setUsersCollectionFromTheFile;

public class UsersRepositoryImpl implements UsersRepository {
    @Override
    public void create(User user) {
        FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
    }
    public void create(User user, String path) {
        FileWorkHelper.writeTheUserToTheFile(user.toString(), path, true);
    }

    @Override
    public User findById(String id) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();
            User targetUser = usersCollection.stream().filter(user -> Objects.equals(id, user.getId()))
                    .findAny()
                    .orElse(null);

            if (Objects.isNull(targetUser)) {
                throw new UserNotFoundException();
            }

            return targetUser;
        } catch(UserNotFoundException error) {
            throw new UserNotFoundException();
        }
    }
    public User findById(String id, String path) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile(path);
            User targetUser = usersCollection.stream().filter(user -> Objects.equals(id, user.getId()))
                    .findAny()
                    .orElse(null);

            if (Objects.isNull(targetUser)) {
                throw new UserNotFoundException();
            }

            return targetUser;
        } catch(UserNotFoundException error) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return setUsersCollectionFromTheFile();
        } catch(IllegalArgumentException error) {
            System.err.println("Ошибка при запросе списка пользователей:\n" + error.getMessage() + "\n") ;
            error.printStackTrace(System.out);
        }

        return null;
    }
    public List<User> findAll(String path) {
        try {
            return setUsersCollectionFromTheFile(path);
        } catch(IllegalArgumentException error) {
            System.err.println("Ошибка при запросе списка пользователей:\n" + error.getMessage() + "\n") ;
            error.printStackTrace(System.out);
        }

        return null;
    }

    @Override
    public void update(User user) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();

            User targetUser = usersCollection.stream().filter(
                        currentUser -> Objects.equals(currentUser.getId(), user.getId())
                    )
                    .findAny()
                    .orElse(null);

            if (Objects.isNull(targetUser)) {
                System.out.println("Пользователя с заданным идентификатором не существует - будет создан новый пользователь");
                FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
            } else {
                List<User> updatedUsersCollection = setUsersCollectionFromTheFile().stream().map(currentUser ->
                    Objects.equals(currentUser.getId(), user.getId())
                            ? user : currentUser
                    ).toList();

                deleteAll();

                for (User currentUser : updatedUsersCollection) {
                    if (Objects.isNull(currentUser)) break;

                    FileWorkHelper.writeTheUserToTheFile(currentUser.toString(), DATA_FILE_PATH, true);
                }

                System.out.println("Обновление пользователя по id прошло успешно");
            }
        } catch(Exception e) {
            System.err.println("Ошибка при обновлении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }
    public void update(User user, String path) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile(path);

            User targetUser = usersCollection.stream().filter(
                            currentUser -> Objects.equals(currentUser.getId(), user.getId())
                    )
                    .findAny()
                    .orElse(null);

            if (Objects.isNull(targetUser)) {
                System.out.println("Пользователя с заданным идентификатором не существует - будет создан новый пользователь");
                FileWorkHelper.writeTheUserToTheFile(user.toString(), path, true);
            } else {
                List<User> updatedUsersCollection = setUsersCollectionFromTheFile(path).stream().map(currentUser ->
                        Objects.equals(currentUser.getId(), user.getId())
                                ? user : currentUser
                ).toList();

                deleteAll(path);

                for (User currentUser : updatedUsersCollection) {
                    if (Objects.isNull(currentUser)) break;

                    FileWorkHelper.writeTheUserToTheFile(currentUser.toString(), path, true);
                }

                System.out.println("Обновление пользователя по id прошло успешно");
            }
        } catch(Exception e) {
            System.err.println("Ошибка при обновлении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile();
            User targetUser = findById(id);

            if (Objects.isNull(targetUser)) {
                throw new UserNotFoundException();
            }

            List<User> updatedUsersCollection = usersCollection.stream().filter(
                    user -> !Objects.equals(id, user.getId())
            ).toList();

            deleteAll();

            for (User user : updatedUsersCollection) {
                FileWorkHelper.writeTheUserToTheFile(user.toString(), DATA_FILE_PATH, true);
            }

            System.out.println("Удаление пользователя по id и обновление списка прошли успешно");
        } catch(Exception e) {
            System.err.println("Ошибка при удалении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }
    public void deleteById(String id, String path) {
        try {
            ArrayList<User> usersCollection = setUsersCollectionFromTheFile(path);
            User targetUser = findById(id, path);

            if (Objects.isNull(targetUser)) {
                throw new UserNotFoundException();
            }

            List<User> updatedUsersCollection = usersCollection.stream().filter(
                    user -> !Objects.equals(id, user.getId())
            ).toList();

            deleteAll(path);

            for (User user : updatedUsersCollection) {
                FileWorkHelper.writeTheUserToTheFile(user.toString(), path, true);
            }

            System.out.println("Удаление пользователя по id и обновление списка прошли успешно");
        } catch(Exception e) {
            System.err.println("Ошибка при удалении:\n" + e.getMessage() + "\n") ;
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void deleteAll() {
        try {
            new FileOutputStream(DATA_FILE_PATH).close();
            System.out.println("Очистка файла прошла успешно");
        } catch(IOException e) {
            System.err.println("Ошибка при удалении:\n" + e.getMessage() + "\n"); ;
            e.printStackTrace(System.out);
        }
    }
    public void deleteAll(String path) {
        try {
            new FileOutputStream(path).close();
            System.out.println("Очистка файла прошла успешно");
        } catch(IOException e) {
            System.err.println("Ошибка при удалении:\n" + e.getMessage() + "\n"); ;
            e.printStackTrace(System.out);
        }
    }
}
