package com.demo.ngsoft.services.implementations;


import com.demo.ngsoft.entities.Comment;
import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.errorHandler.CommentGeneralErrorException;
import com.demo.ngsoft.repositories.CommentRepo;
import com.demo.ngsoft.repositories.TaskRepo;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import com.demo.ngsoft.requestObjects.UpdateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentImpl")
public class CommentImpl {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    UserRepo userRepo;

    public Comment createComment(CreateCommentRequest commentObj) {
        Task taskToComment = taskRepo.getReferenceById(commentObj.TaskId());
        if (taskToComment.getAssignee() == null)
            throw new CommentGeneralErrorException("Only the assigned user can comment the task: " + taskToComment);

        Comment comment = new Comment(commentObj);
        comment.setTaskId(taskToComment);
        comment.setUserId(taskToComment.getAssignee());
        return commentRepo.save(comment);
    }

    public Comment updateComment(UpdateCommentRequest commentObj) {
        Comment comment = commentRepo.getReferenceById(commentObj.id());
        comment = commentObj.updateCommentParameters(commentObj, comment);
        return commentRepo.save(comment);
    }

    public List<Comment> getAllCommentList() {
        List<Comment> commentList = commentRepo.findAll();
        return commentList;
    }
}
