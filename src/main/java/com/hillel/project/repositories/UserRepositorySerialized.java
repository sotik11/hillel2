package com.hillel.project.repositories;

import com.hillel.project.entities.User;
import com.hillel.project.exceptions.NotFoundException;
import lombok.NonNull;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepositorySerialized implements UserRepository, AutoCloseable {
    private static final String REPOSITORY_DIR = "user-repository";
    private static final String REPOSITORY_FILE = "users.ser";
    private static final String REPOSITORY_PATH = REPOSITORY_DIR + File.separator + REPOSITORY_FILE;
    private List<User> repository;

    UserRepositorySerialized() {
        loadRepository();
    }

    private void saveRepository() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(REPOSITORY_PATH)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(repository);
            }
        } catch (FileNotFoundException e) {
            try {
                File dirFile = Paths.get(REPOSITORY_DIR).toFile();
                if( ! dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File repositoryFile = new File(REPOSITORY_DIR, REPOSITORY_FILE);
                if( ! repositoryFile.exists()) {
                    repositoryFile.createNewFile();
                }

                try (FileOutputStream fileOutputStream = new FileOutputStream(REPOSITORY_PATH)) {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                        objectOutputStream.writeObject(repository);
                    }
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void loadRepository() {
        try(FileInputStream fileInputStream = new FileInputStream(REPOSITORY_PATH)) {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                repository = (List<User>) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            repository = new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        saveRepository();
    }

    @Override
    public void save(User user) {
        repository.add(user);
    }

    @Override
    public @NonNull User findByLogin(String login) throws NotFoundException {
        for(User user : repository) {
            if(login.equals(user.getLogin())) {
                return user;
            }
        }
        throw new NotFoundException();
    }
}




