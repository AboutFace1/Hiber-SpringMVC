package ru.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tim.model.User;

import java.util.List;

@Repository(value = "jpaRep")
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("update User u set u.name = ?1, u.age = ?2, u.email = ?3 WHERE u.id = ?4")
    void updateUser(String name, Byte age, String email, Integer id);

}
