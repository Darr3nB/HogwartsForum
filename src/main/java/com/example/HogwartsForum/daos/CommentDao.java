package com.example.HogwartsForum.daos;

import com.example.HogwartsForum.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comments, Integer> {
}
