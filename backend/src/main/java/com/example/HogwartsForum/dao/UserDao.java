package com.example.HogwartsForum.dao;

import com.example.HogwartsForum.model.HogwartsUser;

import java.util.List;

public interface UserDao {
    void add(HogwartsUser hogwartsUser);

    void update(HogwartsUser hogwartsUser);

    HogwartsUser get(int id);

    List<HogwartsUser> getAll();
}
