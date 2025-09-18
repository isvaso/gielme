package com.isvaso.repository;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.Task;
import com.isvaso.storage.TaskStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskRepository {

    private final TaskStorage storage = new TaskStorage();

    public void add(Task task) throws RepositoryException {
        if(task == null) {
            throw new RepositoryException("Task is null");
        }
        storage.add(task);
    }

    public List<Task> get() {
        return storage.get();
    }

    public void update(List<Task> tasks) throws RepositoryException {
        if(tasks == null) {
            throw new RepositoryException("Tasks is null");
        }
        storage.update(tasks);
    }
}
