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

    public void upvoteComment(int commentId){
        Comment commentToUpvote = commentDao.findCommentById(commentId);

        commentToUpvote.setUpVoteCount(commentToUpvote.getUpVoteCount() + 1);

        commentDao.save(commentToUpvote);
    }

    public void downvoteComment(int commentId) {
        Comment commentToUpvote = commentDao.findCommentById(commentId);

        commentToUpvote.setDownVoteCount(commentToUpvote.getDownVoteCount() + 1);

        commentDao.save(commentToUpvote);
    }

    public void removeCommentById(int commentId) {
        Comment commentToDelete = commentDao.findCommentById(commentId);

        commentDao.delete(commentToDelete);
    }
}
