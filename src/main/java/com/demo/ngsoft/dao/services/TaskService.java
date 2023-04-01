package com.demo.ngsoft.dao.services;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.TaskGeneralErrorException;
import com.demo.ngsoft.repositories.TaskRepo;
import com.demo.ngsoft.requestObjects.*;
import com.demo.ngsoft.responseObjects.TaskTableResponse;
import com.demo.ngsoft.utils.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("TaskImpl")
public class TaskService {


    private final TaskRepo taskRepo;
    private final UserService userService;
    private TaskStatus taskStatus = new TaskStatus();
    public Task createTask(CreateTaskRequest taskObj){
        Task task = new Task(taskObj);
        Optional<Task> checkDuplication = taskRepo.findByTitle(task.getTitle());
        if(checkDuplication.isPresent())
            throw new TaskGeneralErrorException("The Task already exists. Can not create task with the same title.");
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

    public List<TaskTableResponse> getAllUserTaskList(long assignee){
        List<Task> taskList = taskRepo.getAllByAssignee(assignee,taskStatus.getARCHIVED());
        List<TaskTableResponse> response = new LinkedList<>();
                taskList.stream()
                .parallel()
                .forEach(tsk ->  response.add(new TaskTableResponse(
                        tsk.getId(),
                        tsk.getTitle(),
                        tsk.getDescription(),
                        tsk.getStatus(),
                        tsk.getAssignee().getId(),
                        ""
                )));

        return response;
    }

    public TaskTableResponse assignUserToTask(long taskId, long userId){
        User user = userService.getUserById(userId);
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

    public Task getTaskById(long id){
       return  taskRepo.getReferenceById(id);
    }

    public String setTaskComplete(Long taskId) {
       int check = taskRepo.updateTaskToComplete(taskId,taskStatus.getCOMPLETED());

       if(check==0)
        return "The update did not occurred. ";

       return "Update successfully";
    }
}
