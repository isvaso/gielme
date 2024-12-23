package com.isvaso.service;

import com.isvaso.model.Task;
import com.isvaso.repository.TaskRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class TaskService {

    @Getter
    private static final TaskService instance = new TaskService();

    private final TaskRepository repository = new TaskRepository();

    private TaskService() {}

    public void add(Task task) {
        repository.add(task);
    }

    public List<Task> get() {
        return repository.get();
    }

    public Task get(int index) {
        return repository.get(index);
    }

    public Task delete(int index) {
        return repository.delete(index);
    }

}
