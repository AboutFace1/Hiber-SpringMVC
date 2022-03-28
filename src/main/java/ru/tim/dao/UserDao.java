package ru.tim.dao;

import ru.tim.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();
    User getUserById(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);

}