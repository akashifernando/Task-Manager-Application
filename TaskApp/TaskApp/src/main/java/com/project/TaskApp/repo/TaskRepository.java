package com.project.TaskApp.repo;

import com.project.TaskApp.entity.Task;
import com.project.TaskApp.entity.AppUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//hndle crud
public interface TaskRepository extends JpaRepository //object convert to tableObject relation mapping ORM
        <Task, Long> {
    List<Task> findByUser(AppUser user, Sort sort);
    List<Task> findByCompletedAndUser(boolean completed, AppUser user);

}
