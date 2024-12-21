package com.isvaso.storage;

import com.isvaso.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

    // Init data from source (file)
    private final List<Task> data = new ArrayList<>();

    public void add(Task task) {
        data.add(task);
    }

    public List<Task> get() {
        return new ArrayList<>(data);
    }

    public boolean delete(Task task) {
        return data.remove(task);
    }
}
