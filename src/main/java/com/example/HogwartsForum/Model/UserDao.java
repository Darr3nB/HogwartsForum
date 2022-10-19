package com.example.HogwartsForum.Model;

import java.util.List;

public interface UserDao {
    void add(RegisteredUsers registeredUsers);

    void update(RegisteredUsers registeredUsers);

    RegisteredUsers get(int id);

    List<RegisteredUsers> getAll();
}
