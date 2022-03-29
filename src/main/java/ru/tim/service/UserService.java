package ru.tim.service;

import ru.tim.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}
