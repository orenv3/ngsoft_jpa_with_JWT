package com.demo.ngsoft.controllers;

import com.demo.ngsoft.entities.Comment;
import com.demo.ngsoft.requestObjects.AddUserComment;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import com.demo.ngsoft.requestObjects.UpdateCommentRequest;
import com.demo.ngsoft.responseObjects.CommentsResponse;
import com.demo.ngsoft.services.implementations.CommentImpl;
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

    private final CommentImpl commentImpl;

    /**
     * Admin privilege
     * Create comment -  comment on any task that belong to user
     * @param commentObj
     * @return
     */
    @PostMapping("admin/createComment")
    public Comment create(@Valid @RequestBody() CreateCommentRequest commentObj) {
        return commentImpl.createComment(commentObj);
    }

    /**
     * Admin privilege
     * Update Comment by comment ID
     * @param commentObj
     * @return
     */
    @PutMapping("admin/updateComment")
    public Comment update(@Valid @RequestBody() UpdateCommentRequest commentObj) {
        return commentImpl.updateComment(commentObj);
    }

/**
 * Admin privilege
 * Get list of all comments in DB
 */
    @GetMapping("admin/allCommentList")
    public List<Comment> getAllCommentList(){
        return commentImpl.getAllCommentList();
    }


    /**
     * USER privilege
     * User can comment his tasks - comment specific task
     * @param commentObj
     * @return
     */
    @PostMapping("user/commentMyTask")
    public CommentsResponse userCommentOnTask(@Valid @RequestBody() AddUserComment commentObj) {
        return commentImpl.userCommentOnTask(commentObj);
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
        return commentImpl.getAllUserCommentList(userId);
    }



}
