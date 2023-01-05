package com.HogwartsForum.controller;

import com.HogwartsForum.model.Comment;
import com.HogwartsForum.model.Question;
import com.HogwartsForum.services.CommentService;
import com.HogwartsForum.services.QuestionService;
import com.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("post-comment-on-specific-question/{questionId}/{userId}/{commentText}")
    public HttpEntity<Void> commentOnSpecificQuestion(@PathVariable String questionId,
                                                      @PathVariable String userId,
                                                      @PathVariable String commentText) {
        // TODO picture from frontend
        // TODO backend validation of fields
        String uploadedPicture = null;
        Comment comment = new Comment(commentText, uploadedPicture);
        commentService.saveNewComment(comment);
        questionService.addCommentToQuestion(Integer.parseInt(questionId), comment);
        userService.addCommentToUser(Integer.parseInt(userId), comment);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
