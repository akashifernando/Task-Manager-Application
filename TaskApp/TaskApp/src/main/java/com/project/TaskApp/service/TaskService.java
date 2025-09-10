package com.project.TaskApp.service;
//define service should do
//clz how it work
//best practises
//tst easy
import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.TaskRequest;
import com.project.TaskApp.entity.Task;

import java.util.List;
//prototype tranpatant
public interface TaskService {
    Response<Task> createTask(TaskRequest taskRequest);
    Response<List<Task>> getAllMyTasks();
    Response<Task> getTaskById(Long id);
    Response<Task> updateTask(TaskRequest taskRequest);
    Response<Void> deleteTask(Long id);
    Response<List<Task>> getMyTasksByCompletionStatus(boolean completed);

}
