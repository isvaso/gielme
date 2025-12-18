package com.isvaso.domain.repository;

import com.isvaso.exception.RepositoryException;
import com.isvaso.domain.model.Task;
import com.isvaso.exception.StorageException;
import com.isvaso.storage.TaskStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskRepository {

    private final TaskStorage storage;

    public Optional<Task> add(Task task) throws RepositoryException {
        if(task == null)
            throw new RepositoryException("Task cannot be null null");
        try {
            storage.add(task);
        } catch (StorageException exception) {
            throw new RepositoryException(exception);
        }
        return Optional.of(task);
    }

    public List<Task> get() throws RepositoryException {
        try {
            return storage.get();
        } catch (StorageException exception) {
            throw new RepositoryException(exception);
        }
    }

    public List<Task> update(List<Task> tasks) throws RepositoryException {
        if(tasks == null)
            throw new RepositoryException("Task cannot be null null");
        try {
            storage.update(tasks);
        } catch (StorageException exception) {
            throw new RepositoryException(exception);
        }
        return tasks;
    }
}
