package com.demo.ngsoft.controllers;

import com.demo.ngsoft.entities.Comment;
import com.demo.ngsoft.requestObjects.AddUserComment;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import com.demo.ngsoft.requestObjects.UpdateCommentRequest;
import com.demo.ngsoft.responseObjects.CommentsResponse;
import com.demo.ngsoft.dao.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/comment/")
@RestController
public class CommentController {

    private final CommentService commentService;

    /**
     * Admin privilege
     * Create comment -  comment on any task that belong to user
     * @param commentObj
     * @return
     */
    @PostMapping("admin/createComment")
    public Comment create(@Valid @RequestBody() CreateCommentRequest commentObj) {
        return commentService.createComment(commentObj);
    }

    /**
     * Admin privilege
     * Update Comment by comment ID
     * @param commentObj
     * @return
     */
    @PutMapping("admin/updateComment")
    public Comment update(@Valid @RequestBody() UpdateCommentRequest commentObj) {
        return commentService.updateComment(commentObj);
    }

/**
 * Admin privilege
 * Get list of all comments in DB
 */
    @GetMapping("admin/allCommentList")
    public List<Comment> getAllCommentList(){
        return commentService.getAllCommentList();
    }


    /**
     * USER privilege
     * User can comment his tasks - comment specific task
     * @param commentObj
     * @return
     */
    @PostMapping("user/commentMyTask")
    public CommentsResponse userCommentOnTask(@Valid @RequestBody() AddUserComment commentObj) {
        return commentService.userCommentOnTask(commentObj);
    }


    /**
     * USER privilege
     * Get list of user tasks and their comments
     *
     * @param userId
     * @return
     */
    @GetMapping("user/userCommentList/{userId}")
    public List<CommentsResponse> getAllUserCommentList( @PathVariable("userId") long userId){
        return commentService.getAllUserCommentList(userId);
    }



}
