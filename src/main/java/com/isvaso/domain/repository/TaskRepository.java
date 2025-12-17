package com.isvaso.domain.repository;

import com.isvaso.exception.RepositoryException;
import com.isvaso.domain.model.Task;
import com.isvaso.storage.TaskStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskRepository {

    private final TaskStorage storage;

    public Optional<Task> add(Task task) throws RepositoryException {
        if(task == null)
            throw new RepositoryException("Task is null");
        storage.add(task);
        return Optional.of(task);
    }

    public List<Task> get() {
        return storage.get();
    }

    public List<Task> update(List<Task> tasks) throws RepositoryException {
        if(tasks == null)
            throw new RepositoryException("Tasks is null");
        storage.update(tasks);
        return tasks;
    }
}
