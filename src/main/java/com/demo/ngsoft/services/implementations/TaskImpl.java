package com.demo.ngsoft.services.implementations;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.ValidationErrorException;
import com.demo.ngsoft.repositories.TaskRepo;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.*;
import com.demo.ngsoft.responseObjects.TaskTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("TaskImpl")
public class TaskImpl {

    @Autowired
    TaskRepo taskRepo;
    @Autowired
    UserRepo userRepo;

    public Task createTask(CreateTaskRequest taskObj){
        Task task = new Task(taskObj);
        Task taskResponse =  taskRepo.save(task);
       return taskResponse;
    }

    public Task updateTask(UpdateTaskRequest taskObj){
        Task task = taskRepo.getReferenceById(taskObj.id());
        task = taskObj.updateTaskParameters(taskObj,task);
        Task taskResponse =  taskRepo.save(task);
       return taskResponse;
    }

    public String deleteTask(long id){
        Task task = taskRepo.getReferenceById(id);
        taskRepo.deleteById(id);
        return "Deleted: "+!(taskRepo.existsById(id));
    }


    public List<Task> getAllTaskList(){
        List<Task> taskList = taskRepo.findAll();
        return taskList;
    }

    public TaskTableResponse assignUserToTask(long taskId, long userId){
        User user = userRepo.getReferenceById(userId);
        Task task = taskRepo.getReferenceById(taskId);
        String additionalMessage="";
        if(task.getAssignee() != null){
            additionalMessage = " The Task old assignee was: "+ task.getAssignee();
        }

        task.setAssignee(user);
        task = taskRepo.save(task);
        String err = "The assignation executed successfully: "+task;
        TaskTableResponse response = new TaskTableResponse(
                task.getId(),task.getTitle(),
                task.getDescription(),task.getStatus(),
                task.getAssignee().getId(), err + additionalMessage);

        return response;
    }

    public TaskTableResponse un_assignUserFromTask(long taskId){
        Task task = taskRepo.getReferenceById(taskId);
        String additionalMessage="";
        if(task.getAssignee() != null){
            additionalMessage = " The Task old assignee was: "+ task.getAssignee();

            task.setAssignee(null);
            task = taskRepo.save(task);
        }else {
            additionalMessage = " No User was assign to this task";
        }
        String err = "The user assign successfully: "+task;
        TaskTableResponse response = new TaskTableResponse(
                task.getId(),task.getTitle(),
                task.getDescription(),task.getStatus(),
                null, err + additionalMessage);

        return response;
    }
}
