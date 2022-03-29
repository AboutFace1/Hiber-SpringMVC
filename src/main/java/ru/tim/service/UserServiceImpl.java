package ru.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tim.model.User;
import ru.tim.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        userRepository.updateUser(updatedUser.getName(), updatedUser.getAge(), updatedUser.getEmail(), id);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
