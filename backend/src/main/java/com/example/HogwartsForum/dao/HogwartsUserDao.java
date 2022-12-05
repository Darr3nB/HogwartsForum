package com.example.HogwartsForum.dao;

import com.example.HogwartsForum.model.HogwartsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HogwartsUserDao extends JpaRepository<HogwartsUser, Integer> {
    boolean existsByName(String name);

    HogwartsUser getHogwartsUserByName(String name);
}
