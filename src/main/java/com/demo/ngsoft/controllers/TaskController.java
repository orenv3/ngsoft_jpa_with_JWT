package com.demo.ngsoft.controllers;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateTaskRequest;
import com.demo.ngsoft.requestObjects.UpdateTaskRequest;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import com.demo.ngsoft.responseObjects.TaskTableResponse;
import com.demo.ngsoft.services.implementations.TaskImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RestController("/api/task/")
public class TaskController {

    @Autowired
    TaskImpl taskImpl;

    @PostMapping("admin/createTask")
    public Task create(@Valid @RequestBody() CreateTaskRequest taskObj) {
        return taskImpl.createTask(taskObj);
    }

    @DeleteMapping("admin/deleteTask/{id}")
    public String delete(@NotNull @PathVariable("id") long id) {
        return taskImpl.deleteTask(id);
    }

    @PutMapping("admin/updateTask")
    public Task update(@Valid @RequestBody() UpdateTaskRequest taskObj) {
        return taskImpl.updateTask(taskObj);
    }

    @GetMapping("admin/allTaskList")
    public List<Task> getAllTaskList(){
        return taskImpl.getAllTaskList();
    }

    @PutMapping("admin/assignUser{taskId}/{userId}")
    public TaskTableResponse assignUserToTask(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId){
        return taskImpl.assignUserToTask(taskId,userId);
    }

    @PutMapping("admin/removeUserFromTask/{taskId}")
    public TaskTableResponse removeUserFromTask(@PathVariable("taskId") long taskId){
        return taskImpl.un_assignUserFromTask(taskId);
    }


}
