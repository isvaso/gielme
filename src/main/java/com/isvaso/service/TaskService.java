package com.isvaso.service;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.Task;
import com.isvaso.repository.TaskRepository;
import lombok.Getter;

import java.util.List;

public class TaskService {

    @Getter
    private static final TaskService instance = new TaskService();

    private final TaskRepository repository = new TaskRepository();

    private TaskService() {
    }

    public void add(Task task) {
        try {
            repository.add(task);
        } catch (RepositoryException exception) {
            // log exception
        }
    }

    public List<Task> get() {
        return repository.get();
    }

    public void update(List<Task> tasks) {
        try {
            repository.update(tasks);
        } catch (RepositoryException e) {
            // log exception
        }
    }
}
