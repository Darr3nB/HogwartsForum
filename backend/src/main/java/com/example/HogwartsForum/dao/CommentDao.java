package com.example.HogwartsForum.dao;

import com.example.HogwartsForum.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {
}
