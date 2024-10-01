package com.limb0s.limb0sBoot.dao;


import com.limb0s.limb0sBoot.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoHibernate implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        try {
            User user = entityManager.getReference(User.class, id);
            entityManager.remove(user);
        } catch (EntityNotFoundException e) {
            System.out.println("User with id " + id + " not found");
        }
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = entityManager.find(User.class, id);
        if (updateUser != null) {
            updateUser.setName(user.getName());
            updateUser.setAge(user.getAge());
        }
    }
}
