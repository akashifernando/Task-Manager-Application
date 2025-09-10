package com.project.TaskApp.controller;

import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.TaskRequest;
import com.project.TaskApp.dto.UserRequest;
import com.project.TaskApp.entity.Task;
import com.project.TaskApp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor//auto inject use consinjectiom
public class TaskController {
    private final TaskService taskService;


    @PostMapping//behave like post req
    public ResponseEntity<Response<Task>> // cntrl both response body and hhtp status code
    createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }


    @PutMapping
    public ResponseEntity<Response<Task>> updateTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(taskRequest));
    }

    @GetMapping
    public ResponseEntity<Response<List<Task>>> getAllMyTasks() {
        return ResponseEntity.ok(taskService.getAllMyTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Task>> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteTask(@PathVariable ///extract dunamic vlu  from the url
                                                         Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }


    @GetMapping("/status")
    public ResponseEntity<Response<List<Task>>> getMyTasksByCompletionStatus(
            @RequestParam boolean completed
    ) {
        return ResponseEntity.ok(taskService.getMyTasksByCompletionStatus(completed));
    }


}
