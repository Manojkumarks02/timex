package com.timex.controller;

import com.timex.model.Task;
import com.timex.payload.TaskResponse;
import com.timex.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create-task") // http://localhost:9494/api/create-task
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/getAllTask")// http://localhost:9494/api/getAllTask
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @GetMapping("{id}") // http://localhost:9494/api/getById/2
    @ResponseStatus(HttpStatus.OK)
    public Optional<Task> getTaskId(@PathVariable Long id){
        return taskService.getTaskId(id);
    }

    @DeleteMapping("/{id}") //  http://localhost:9494/api/2
    @ResponseStatus(HttpStatus.OK)
    public String deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/{id}")// http://localhost:9494/api/2
    @ResponseStatus(HttpStatus.CREATED)
    public Task updateTask(@RequestBody Task task, @PathVariable Long id){
        return taskService.updateTask(task, id);
    }

    //Pagination
    @GetMapping("/tasks-pagination") //http://localhost:9494/api/tasks?pageNumber=0&pageSize=5(for pagination), http://localhost:9494/api/tasks-pagination?pageNumber=0&pageSize=5&sortDir=asc&sortBy=project&search=abc
    public ResponseEntity<TaskResponse> getAllTasks(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10    ", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "taskId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        TaskResponse taskResponse = taskService.getAllTasks(search,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

}