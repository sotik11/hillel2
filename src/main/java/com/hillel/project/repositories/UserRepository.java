package com.hillel.project.repositories;

import com.hillel.project.entities.User;
import com.hillel.project.exceptions.NotFoundException;
import lombok.NonNull;

public interface UserRepository {

    static UserRepository getInstance() {
        return null; // TODO
    }

    void save(User user);
    @NonNull
    User findByLogin(String login) throws NotFoundException;
}
