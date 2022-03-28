package ru.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tim.model.User;

import java.util.List;

@Repository(value = "jdbcImpl")
public class JdbcUserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class)); //
    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM user WHERE id=?", new BeanPropertyRowMapper<>(User.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user VALUES(?, ?, ?, ?)", user.getId(), user.getName(), user.getAge(), user.getEmail());
    }

    @Override
    public void update(int id, User updatedUser) {
        jdbcTemplate.update("UPDATE user SET name=?, age=?, email=? WHERE id=?", updatedUser.getName(),
                updatedUser.getAge(), updatedUser.getEmail(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);

    }
}
