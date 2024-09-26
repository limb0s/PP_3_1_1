package com.limb0s.limb0sBoot.dao;


import com.limb0s.limb0sBoot.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUser(int id);

    void saveUser(User user);

    void deleteUser(int id);

    void updateUser(int id, User user);
}
