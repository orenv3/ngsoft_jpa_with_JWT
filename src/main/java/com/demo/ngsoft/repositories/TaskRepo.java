package com.demo.ngsoft.repositories;

import com.demo.ngsoft.entities.Task;
import com.demo.ngsoft.entities.User;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("From Task where assignee.id = :assignee and status != :arch")
    List<Task> getAllByAssignee(@Param("assignee")long assignee,@Param("arch") String status);
    Optional<Task> findByTitle(String title);
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.status = :completed WHERE t.id = :taskId")
    Integer  updateTaskToComplete(Long taskId, String completed);
}
