package com.HogwartsForum.services;

import com.HogwartsForum.dao.CommentDao;
import com.HogwartsForum.model.Comment;
import lombok.AllArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    CommentDao commentDao;

    public void saveNewComment(Comment newComment){
        // TODO handle null fields
        commentDao.save(newComment);
    }

    public boolean upvoteComment(int commentId){
        try {
            Comment commentToUpvote = commentDao.findCommentById(commentId);

            commentToUpvote.setUpVoteCount(commentToUpvote.getUpVoteCount() + 1);

            commentDao.save(commentToUpvote);
            return true;
        }catch (NotFoundException e){
            System.out.println("Comment not found: " + e);
            return false;
        }
    }

    public boolean downvoteComment(int commentId) {
        try{
            Comment commentToUpvote = commentDao.findCommentById(commentId);

            commentToUpvote.setDownVoteCount(commentToUpvote.getDownVoteCount() + 1);

            commentDao.save(commentToUpvote);
            return true;
        }catch (NotFoundException e){
            System.out.println("Comment not found: " + e);
            return false;
        }
    }

    public void removeCommentById(int commentId) {
        Comment commentToDelete = commentDao.findCommentById(commentId);

        commentDao.delete(commentToDelete);
    }
}
