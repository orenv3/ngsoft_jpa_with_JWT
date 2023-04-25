package com.demo.ngsoft.entities;

import com.demo.ngsoft.requestObjects.AddUserComment;
import com.demo.ngsoft.requestObjects.CreateCommentRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Data
@Entity
@Table(name = "comment")
public final class Comment {

    public Comment(CreateCommentRequest commentObj){
        this.comment = commentObj.comment();
        this.timestamp = new Date();
    }

    public Comment(AddUserComment commentObj){
        this.comment = commentObj.comment();
        this.timestamp = new Date();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date timestamp;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
     //(foreign key)

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task taskId;
     //(foreign key)






}
