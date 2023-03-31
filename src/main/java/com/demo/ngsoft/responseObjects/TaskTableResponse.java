package com.demo.ngsoft.responseObjects;

public record TaskTableResponse(

        Long task_id,
        String title,

        String description,

        String task_status, //(pending/completed/archived)

        Long task_assignee,

        String err

) {
}
