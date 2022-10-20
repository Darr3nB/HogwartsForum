package com.example.HogwartsForum.Repositories;

import com.example.HogwartsForum.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
