package com.example.hw5.service;

import com.example.hw5.model.Task;
import com.example.hw5.model.TaskStatus;
import com.example.hw5.repository.RepositoryTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceTask {
    private final RepositoryTask repository;

    /**
     * Получаем все задачи
     * @return возвращаем все задачи
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }


    /**
     * Получаем задачу по id
     * @param id id задачи
     * @return возвращаем задачу
     */
    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }


    /**
     * Создаем задачу
     * @param task объект задачи
     * @return возвращаем задачу
     */
    public Task createTask(Task task) {
        return repository.save(task);
    }

    // TODO дописать метод изменения задачи
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            LocalDateTime dateChanged = LocalDateTime.now();
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setDateCreated(dateChanged);
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
    }

    public Task updateTaskStatus(Long id, Task newStatus) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newStatus.getStatus());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    /**
     * Выполняем поиск по статусу
     * @param status статус
     * @return
     */
    public List<Task> getTasksForStatus(TaskStatus status) {
        List<Task> tasksForStatus = new ArrayList<>();

        for (Task task : getAllTasks()) {
            if (task.getStatus() == status) {
                tasksForStatus.add(task);
            }
        }

        return tasksForStatus;
    }


    /**
     * Удаляем задачу
     * @param id id задачи
     */
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
