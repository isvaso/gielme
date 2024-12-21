package com.isvaso.service;

import com.isvaso.model.Task;
import com.isvaso.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskService {

    private TaskRepository repository;

    public void add(Task task) {
        repository.add(task);
    }

    public List<Task> get() {
        return repository.get();
    }

    public boolean delete(Task task) {
        return repository.delete(task);
    }

}
