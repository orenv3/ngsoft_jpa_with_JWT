package com.demo.ngsoft.requestObjects;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.ValidationErrorException;
import com.demo.ngsoft.utils.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(@NotBlank @Size(max=15) String title,
                                @Size(max=40) String description,
                                String status//(pending/completed/archived)


) {

    public CreateTaskRequest {
        TaskStatus taskStatus = new TaskStatus();
        if(status == null || status.isBlank())
            status = taskStatus.getPENDING();
        else if (!(taskStatus.isValidStatus(status)))
            throw new ValidationErrorException("The status:" + status +
                    " is not valid. \n Please enter one of the following: " + taskStatus.getStatusOptions());
    }
}
