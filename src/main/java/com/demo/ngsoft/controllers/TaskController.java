package com.demo.ngsoft.controllers;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.requestObjects.CreateTaskRequest;
import com.demo.ngsoft.requestObjects.UpdateTaskRequest;
import com.demo.ngsoft.responseObjects.TaskTableResponse;
import com.demo.ngsoft.services.implementations.TaskImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/task/")
@RestController
public class TaskController {

    private final TaskImpl taskImpl;

    /**
     * Admin privilege
     * create Task
     * @param taskObj CreateTaskRequest object with task detail to create
     * @return
     */
    @PostMapping("admin/createTask")
    public Task create(@Valid @RequestBody() CreateTaskRequest taskObj) {
        return taskImpl.createTask(taskObj);
    }

    /**
     * Admin privilege
     * Delete Task by ID
     * @param id
     * @return
     */
    @DeleteMapping("admin/deleteTask/{id}")
    public String delete(@NotNull @PathVariable("id") long id) {
        return taskImpl.deleteTask(id);
    }

    /**
     * Admin privilege
     * Update task
     * @param taskObj UpdateTaskRequest object with task details to update
     * @return
     */
    @PutMapping("admin/updateTask")
    public Task update(@Valid @RequestBody() UpdateTaskRequest taskObj) {
        return taskImpl.updateTask(taskObj);
    }

    /**
     * Admin privilege
     * Get list of all task in the DB
     * @return
     */
    @GetMapping("admin/allTaskList")
    public List<Task> getAllTaskList(){
        return taskImpl.getAllTaskList();
    }

    /**
     * Admin privilege
     * Assign task to a user
     * @param taskId task ID for assignation
     * @param userId user ID for assignation
     * @return
     */
    @PutMapping("admin/assignUser{taskId}/{userId}")
    public TaskTableResponse assignUserToTask(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId){
        return taskImpl.assignUserToTask(taskId,userId);
    }

    /**
     * Admin privilege
     * Un-assign user from task
     * @param taskId
     * @return
     */
    @PutMapping("admin/removeUserFromTask/{taskId}")
    public TaskTableResponse removeUserFromTask(@PathVariable("taskId") long taskId){
        return taskImpl.un_assignUserFromTask(taskId);
    }

    /**
     * ***********   Below regular user privilege methods   **************
     */

    /**
     * User privilege
     * Get all task of specific user
     * @param assignee user detail
     * @return
     */
    @GetMapping("user/allTaskList/{assignee}")
    public List<TaskTableResponse> getAllUserTaskList(@PathVariable("assignee") @Min(1) long assignee){
        return taskImpl.getAllUserTaskList(assignee);
    }

    /**
     * User privilege
     * User can update his task status to 'complete'
     * @param taskId
     * @return
     */
    @PutMapping("user/updateComplete")
    public String setTaskComplete(Long taskId) {
        return taskImpl.setTaskComplete(taskId);
    }


}
