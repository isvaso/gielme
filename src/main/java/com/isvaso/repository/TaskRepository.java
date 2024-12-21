package com.isvaso.repository;

import com.isvaso.model.Task;
import com.isvaso.storage.TaskStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskRepository {

    private TaskStorage storage;

    public void add(Task task) {
        storage.add(task);
    }

    public List<Task> get() {
        return storage.get();
    }

    public boolean delete(Task task) {
        return storage.delete(task);
    }
}
