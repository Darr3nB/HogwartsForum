package com.example.HogwartsForum.daos;

import com.example.HogwartsForum.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {
}
