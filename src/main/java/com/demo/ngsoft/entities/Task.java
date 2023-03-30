package com.demo.ngsoft.entities;

import com.demo.ngsoft.requestObjects.CreateTaskRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "task")
public final class Task {

    public Task(CreateTaskRequest taskObj){
        this.description = taskObj.description();
        this.status = taskObj.status();
        this.title = taskObj.title();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String status; //(pending/completed/archived)

    @ManyToOne
    @JoinColumn(name = "assignee")
    private User assignee;

  // @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskId")
    @Transient
    private List<Comment> taskCommentsList;


}
