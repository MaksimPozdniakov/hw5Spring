package com.example.hw5.controller;

import com.example.hw5.model.Task;
import com.example.hw5.model.TaskStatus;
import com.example.hw5.service.ServiceTask;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class ControllerTask {

    private final ServiceTask serviceTask;

    @GetMapping()
    public List<Task> getAllTasks() {
        return serviceTask.getAllTasks();
    }

    @PostMapping("/task-create")
    public Task addTask(@RequestBody Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.NOT_STARTED);
        }
        return serviceTask.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        serviceTask.deleteTask(id);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable TaskStatus status) {
        return serviceTask.getTasksForStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return serviceTask.updateTaskStatus(id, task);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return serviceTask.updateTask(id, task);
    }

}
