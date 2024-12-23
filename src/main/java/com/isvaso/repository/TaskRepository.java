package com.isvaso.repository;

import com.isvaso.model.Task;
import com.isvaso.storage.TaskStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskRepository {

    private final TaskStorage storage = new TaskStorage();

    public void add(Task task) {
        storage.add(task);
    }

    public List<Task> get() {
        return storage.get();
    }

    public Task get(int index) {
        return storage.get(index);
    }

    public Task delete(int index) {
        return storage.delete(index);
    }
}
