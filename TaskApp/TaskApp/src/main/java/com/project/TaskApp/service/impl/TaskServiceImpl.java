package com.project.TaskApp.service.impl;



import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.TaskRequest;
import com.project.TaskApp.entity.Task;
import com.project.TaskApp.entity.AppUser;
import com.project.TaskApp.exceptions.NotFoundException;
import com.project.TaskApp.repo.TaskRepository;
import com.project.TaskApp.service.TaskService;
import com.project.TaskApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    private final UserService userService;




    @Override//becaus implement from interface
    public Response<Task> createTask(TaskRequest taskRequest) {
        log.info("INSIDE createTask()");

        AppUser user = userService.getCurrentLoggedInUser();
//curnt log user
        Task taskToSave = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .completed(taskRequest.getCompleted())

                .dueDate(taskRequest.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
//new task use builder pattern
        Task savedTask = taskRepository.save(taskToSave);
//        taskRepository.
//db save
        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task Created Successfully")
                .data(savedTask)
                .build();
        //return response
    }

    @Override
    @Transactional//Ensure db operation inside method execut as single transaction.

        public Response<List<Task>> getAllMyTasks() {
        log.info("inside getAllMyTasks()");
        AppUser currentUser = userService.getCurrentLoggedInUser();

        List<Task> tasks = taskRepository.findByUser(currentUser, Sort.by(Sort.Direction.DESC, "id"));
        return Response.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Tasks retrieved successfully")
                .data(tasks)
                .build();
    }

    @Override

    public Response<Task> getTaskById(Long id) {
        log.info("inside getTaskById()");

        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Tasks not found"));

        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task retrieved successfully")
                .data(task)
                .build();
    }

    @Override
    public Response<Task> updateTask(TaskRequest taskRequest) {
        log.info("inside updateTask()");

        Task task = taskRepository.findById(taskRequest.getId())
                .orElseThrow(()-> new NotFoundException("Tasks not found"));

        if (taskRequest.getTitle() != null) task.setTitle(taskRequest.getTitle());
        if (taskRequest.getDescription() != null) task.setDescription(taskRequest.getDescription());
        if (taskRequest.getCompleted() != null) task.setCompleted(taskRequest.getCompleted());

        if (taskRequest.getDueDate() != null) task.setDueDate(taskRequest.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        //update the task in the database
        Task updatedTask = taskRepository.save(task);

        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task updated successfully")
                .data(updatedTask)
                .build();

    }

    @Override
    public Response<Void> deleteTask(Long id) {
        log.info("inside delete task");
        if (!taskRepository.existsById(id)){
            throw new NotFoundException("Task does not exists");
        }
        taskRepository.deleteById(id);

        return Response.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("task deleted successfully")
                .build();
    }

    @Override
    @Transactional
    public Response<List<Task>> getMyTasksByCompletionStatus(boolean completed) {
        log.info("inside getMyTasksByCompletionStatus()");

        AppUser currentUser = userService.getCurrentLoggedInUser();

        List<Task> tasks = taskRepository.findByCompletedAndUser(completed, currentUser);

        return Response.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Tasks filtered by completion status for user")
                .data(tasks)
                .build();
    }
}
