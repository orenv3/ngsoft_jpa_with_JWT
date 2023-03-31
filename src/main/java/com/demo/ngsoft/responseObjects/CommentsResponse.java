package com.demo.ngsoft.responseObjects;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;

import java.util.Date;

public record CommentsResponse(

        Date timestamp,

        String comment,

        long userId,

        long taskId,

        String title,

        String err

) {


}
