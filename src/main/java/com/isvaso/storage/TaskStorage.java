package com.isvaso.storage;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

    // Init data from source (file)
    private final List<Task> data = new ArrayList<>();

    {
        data.add(new Task("Уборка"));
        data.get(0).setState(TaskState.SOLVED);
        data.add(new Task("Застелить постель"));
    }

    public void add(Task task) {
        data.add(task);
    }

    public List<Task> get() {
        return new ArrayList<>(data);
    }

    public Task get(int index) {
        return data.get(index);
    }

    public Task delete(int index) {
        return data.remove(index);
    }
}
