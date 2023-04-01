package com.demo.ngsoft.dao.services;


import com.demo.ngsoft.entities.Comment;
import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.errorHandler.CommentGeneralErrorException;
import com.demo.ngsoft.repositories.CommentRepo;
import com.demo.ngsoft.requestObjects.AddUserComment;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import com.demo.ngsoft.requestObjects.UpdateCommentRequest;
import com.demo.ngsoft.responseObjects.CommentsResponse;
import com.demo.ngsoft.responseObjects.TaskTableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("CommentImpl")
public class CommentService {

    private final CommentRepo commentRepo;
    private final TaskService taskRepo;
    private final UserService userRepo;

    public Comment createComment(CreateCommentRequest commentObj) {
        Task taskToComment = taskRepo.getTaskById(commentObj.TaskId());
        if (taskToComment.getAssignee() == null)
            throw new CommentGeneralErrorException("No assignee in the task: " + taskToComment);

        Comment comment = new Comment(commentObj);
        comment.setTaskId(taskToComment);
        comment.setUserId(taskToComment.getAssignee());
        return commentRepo.save(comment);
    }
    public CommentsResponse userCommentOnTask(AddUserComment commentObj) {
        Task taskToComment = taskRepo.getTaskById(commentObj.TaskId());
        if (taskToComment.getAssignee() == null) {
            throw new CommentGeneralErrorException("No assignee in the task: " + taskToComment);
        }else if(taskToComment.getAssignee().getId() != commentObj.userId()){
            throw new CommentGeneralErrorException("The user with id: "+commentObj.userId()+ " can not comment on task "+taskToComment);
        }

        Comment comment = new Comment(commentObj);
        comment.setTaskId(taskToComment);
        comment.setUserId(taskToComment.getAssignee());
        Comment response = commentRepo.save(comment);
        return new CommentsResponse(
                response.getTimestamp(),
                response.getComment(),
                response.getUserId().getId(),
                response.getTaskId().getId(),
                response.getTaskId().getTitle(),
                "Comment added successfully");
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
    public List<CommentsResponse> getAllUserCommentList(long userId) {
        List<TaskTableResponse> userTasks = taskRepo.getAllUserTaskList(userId);
        List<String> taskNames = userTasks.stream().parallel().map(tsk -> tsk.title()).collect(Collectors.toList());
        List<Comment> commentList = commentRepo.findByTaskId_TitleIn(taskNames);
        List<CommentsResponse> response = new LinkedList<>();

        commentList.stream().parallel().forEach(com ->
                response.add(new CommentsResponse(
                        com.getTimestamp(),
                        com.getComment(),
                        com.getUserId().getId(),
                        com.getTaskId().getId(),
                        com.getTaskId().getTitle(),"")));
        return response;
    }
}
