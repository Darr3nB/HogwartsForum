package com.HogwartsForum.services;

import com.HogwartsForum.dao.CommentDao;
import com.HogwartsForum.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    CommentDao commentDao;

    public void saveNewComment(Comment newComment){
        // TODO handle null fields
        commentDao.save(newComment);
    }
}
