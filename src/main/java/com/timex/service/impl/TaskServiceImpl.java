package com.timex.service.impl;

import com.timex.model.Task;
import com.timex.payload.TaskResponse;
import com.timex.repository.TaskRepository;
import com.timex.service.EmailService;
import com.timex.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Task createTask(Task task) {
        Task createdTask = taskRepository.save(task);
        Long taskId = createdTask.getTaskId();
        String recipientEmail = "swamesha444@gmail.com";
        String taskDescription = createdTask.getDescription();
        emailService.createMail(recipientEmail, taskId, taskDescription);
        return createdTask;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskId(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found for this id: " + id));

        return Optional.of(task);
    }

    @Override
    public String deleteTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return "Task with ID " + id + " has been successfully deleted.";
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }
    }

    @Override
    public Task updateTask(Task task, Long id) {
        Task updateTask = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("task not found for this id..!"));
        updateTask.setDescription(task.getDescription());
        updateTask.setProject(task.getProject());
        return taskRepository.save(updateTask);
    }

    @Override
    public TaskResponse getAllTasks(String search, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//        Page<Task> taskPage;
//        if (search != null && !search.isEmpty()) {
//            taskPage = taskRepository.findByProjectStartingWithIgnoreCase(search, PageRequest.of(pageNumber, pageSize, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)));
//        } else {
//            taskPage = taskRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)));
//        }
//
//        List<Task> taskList = taskPage.getContent();
//
//        TaskResponse taskResponse = new TaskResponse();
//        taskResponse.setData(taskList);
//        taskResponse.setPageNumber(taskPage.getNumber());
//        taskResponse.setPageSize(taskPage.getSize());
//        taskResponse.setTotalElements(taskPage.getTotalElements());
//        taskResponse.setTotalPage(taskPage.getTotalPages());
//        taskResponse.setLastPage(taskPage.isLast());
//
//        return taskResponse;
//    }

        Page<Task> taskPage;
        if (search != null && !search.isEmpty()) {
            taskPage = taskRepository.findByProjectContainingIgnoreCaseOrClientContainingIgnoreCaseOrTaskContainingIgnoreCase(search, search, search, PageRequest.of(pageNumber, pageSize, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)));
        } else {
            taskPage = taskRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy)));
        }

        List<Task> taskList = taskPage.getContent();

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setData(taskList);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalElements(taskPage.getTotalElements());
        taskResponse.setTotalPage(taskPage.getTotalPages());
        taskResponse.setLastPage(taskPage.isLast());

        return taskResponse;
    }
}
