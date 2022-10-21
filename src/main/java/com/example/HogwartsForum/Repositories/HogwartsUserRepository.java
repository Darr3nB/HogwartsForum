package com.example.HogwartsForum.Repositories;

import com.example.HogwartsForum.Model.HogwartsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HogwartsUserRepository extends JpaRepository<HogwartsUser, Integer> {
    byte countByName(String name);

    HogwartsUser getHogwartsUserByName(String name);
}
