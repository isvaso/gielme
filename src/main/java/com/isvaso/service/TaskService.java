package com.isvaso.service;

import com.isvaso.model.Task;
import com.isvaso.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskService {

    private TaskRepository repository = new TaskRepository();

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
