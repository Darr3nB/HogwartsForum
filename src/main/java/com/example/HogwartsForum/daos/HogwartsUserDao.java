package com.example.HogwartsForum.daos;

import com.example.HogwartsForum.model.HogwartsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HogwartsUserDao extends JpaRepository<HogwartsUser, Integer> {
    byte countByName(String name);

    HogwartsUser getHogwartsUserByName(String name);
}
