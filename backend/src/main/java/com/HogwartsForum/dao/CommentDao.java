package com.HogwartsForum.dao;

import com.HogwartsForum.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    Comment findCommentById(int commentId);
}
