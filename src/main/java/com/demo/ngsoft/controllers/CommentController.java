package com.demo.ngsoft.controllers;

import com.demo.ngsoft.entities.Comment;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import com.demo.ngsoft.requestObjects.UpdateCommentRequest;
import com.demo.ngsoft.services.implementations.CommentImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RestController("/api/comment/")
public class CommentController {

    @Autowired
    CommentImpl commentImpl;

    @PostMapping("admin/createComment")
    public Comment create(@Valid @RequestBody() CreateCommentRequest commentObj) {
        return commentImpl.createComment(commentObj);
    }


    @PutMapping("admin/updateComment")
    public Comment update(@Valid @RequestBody() UpdateCommentRequest commentObj) {
        return commentImpl.updateComment(commentObj);
    }

    @GetMapping("admin/allCommentList")
    public List<Comment> getAllCommentList(){
        return commentImpl.getAllCommentList();
    }


}
