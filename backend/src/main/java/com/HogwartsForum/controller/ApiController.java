package com.HogwartsForum.controller;

import com.HogwartsForum.model.Comment;
import com.HogwartsForum.model.HogwartsUser;
import com.HogwartsForum.model.Question;
import com.HogwartsForum.model.Roles;
import com.HogwartsForum.services.CommentService;
import com.HogwartsForum.services.QuestionService;
import com.HogwartsForum.services.UserService;
import com.HogwartsForum.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    UserService userService;
    QuestionService questionService;
    CommentService commentService;

    @GetMapping(value = "check-username-in-database/{name}")
    public Boolean checkUsernameInDatabase(@PathVariable String name) {
        return userService.existByName(name);
    }

    @GetMapping(value = "five-latest-question")
    public List<Question> getFiveLatestQuestions() {
        return questionService.getTop5QuestionBySubmissionTime();
    }

    @GetMapping(value = "get-all-questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions().getContent();
    }

    @GetMapping(value = "getTotalPageCount")
    public Integer getTotalPageCount() {
        return questionService.getAllQuestions().getTotalPages();
    }

    @GetMapping(value = "get-all-questions-filtered/")
    public void getAllQuestionsFiltered(@RequestParam(name = "title") String title,
                                        @RequestParam(name = "description") String description) {
        // TODO need query builder or something
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
    }

    @GetMapping("get-all-questions-filtered-page-number/")
    public Integer getAllQuestionsFilteredPageNumber(@RequestParam(name = "title") String title,
                                                     @RequestParam(name = "description") String description) {
        // TODO when query builder done, get page number as well
        return 0;
    }

    @GetMapping("get-specific-question/{id}")
    public Question getSpecificQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(Integer.parseInt(id));
    }

    @PostMapping("post-comment-on-specific-question/{questionId}/{userId}")
    public HttpEntity<Void> commentOnSpecificQuestion(@PathVariable String questionId,
                                                      @PathVariable String userId,
                                                      @RequestBody Comment commentFromFrontend) {
        Comment comment = new Comment();
        comment.setCommentText(commentFromFrontend.getCommentText());
        comment.setImage(commentFromFrontend.getImage());

        if (!comment.validText()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        commentService.saveNewComment(comment);
        questionService.addCommentToQuestion(Integer.parseInt(questionId), comment);
        userService.addCommentToUser(Integer.parseInt(userId), comment);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("upvote-comment/{commentId}")
    public HttpEntity<Void> upvoteComment(@PathVariable String commentId) {
        HogwartsUser foundUser = findUserOwnsCommentByCommentId(Integer.parseInt(commentId));

        if (foundUser == null || foundUser.getRole() == Roles.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (commentService.upvoteComment(Integer.parseInt(commentId))){
            if (userService.addReputationToUserById(foundUser.getId(), Utility.commentReputationValue)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("downvote-comment/{commentId}")
    public HttpEntity<Void> downvoteComment(@PathVariable String commentId) {
        HogwartsUser foundUser = findUserOwnsCommentByCommentId(Integer.parseInt(commentId));

        if (foundUser == null || foundUser.getRole() == Roles.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (commentService.downvoteComment(Integer.parseInt(commentId))){
            if (userService.substractReputationToUserById(foundUser.getId(), Utility.commentReputationValue)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("delete-comment/{loggedInUserId}/{questionId}/{commentId}")
    public HttpEntity<Void> deleteComment(@PathVariable String loggedInUserId,
                                          @PathVariable String questionId,
                                          @PathVariable String commentId) {

        if (userService.isUserAdmin(Integer.parseInt(loggedInUserId))) {
            try {
                HogwartsUser userOwnsComment = findUserOwnsCommentByCommentId(Integer.parseInt(commentId));

                doDeleteComment(userOwnsComment.getId(), Integer.parseInt(questionId),
                        Integer.parseInt(commentId));

                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (UsernameNotFoundException e) {
                System.out.println("An error has happened: " + e);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        if (!userService.thisUserPostedCommentWithThisId(Integer.parseInt(loggedInUserId), Integer.parseInt(commentId))
                || !questionService.thisQuestionHasCommentWithThisId(Integer.parseInt(questionId), Integer.parseInt(commentId))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        doDeleteComment(Integer.parseInt(loggedInUserId), Integer.parseInt(questionId), Integer.parseInt(commentId));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void doDeleteComment(int userId, int questionId, int commentId) {
        if (userService.removeCommentById(userId, commentId)){
            if (questionService.removeCommentById(questionId, commentId)){
                commentService.removeCommentById(commentId);
            }
        }
    }

    private HogwartsUser findUserOwnsCommentByCommentId(int commentId){
        try {
            return userService.getUserOwnsThisComment(commentId);
        }catch (UsernameNotFoundException e){
            return null;
        }
    }
}
