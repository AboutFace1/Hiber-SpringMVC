package ru.tim.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tim.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "jpaImpl")
@Transactional(readOnly = true)
public class JpaUserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "emf")
    EntityManager entityManager;


    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        Query q = entityManager.createQuery("UPDATE User u SET u.name = :name, u.age = :age, u.email = :email WHERE u.id = :id");
       q.setParameter("name", updatedUser.getName())
        .setParameter("age", updatedUser.getAge())
        .setParameter("email", updatedUser.getEmail())
        .setParameter("id", id)
               .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = entityManager.getReference(User.class, id);
        entityManager.remove(user);
    }
}
