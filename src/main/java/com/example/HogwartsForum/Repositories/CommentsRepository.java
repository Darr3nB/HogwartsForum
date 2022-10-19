package com.example.HogwartsForum.Repositories;

import com.example.HogwartsForum.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}
