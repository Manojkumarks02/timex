package com.timex.service;

import com.timex.model.Task;
import com.timex.payload.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);

    List<Task> getAllTask();

    Optional<Task> getTaskId(Long id);

    String deleteTask(Long id);

    Task updateTask(Task task, Long id);

    TaskResponse getAllTasks(String search, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
