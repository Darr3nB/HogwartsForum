package com.example.HogwartsForum.Repositories;

import com.example.HogwartsForum.Model.RegisteredUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<RegisteredUsers, Integer> {
}
