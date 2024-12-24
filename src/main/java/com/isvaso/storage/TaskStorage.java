package com.isvaso.storage;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import com.isvaso.serialization.TaskSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskStorage {

    private final FileManager fileManager = new FileManager();
    private final TaskSerializer serializer = new TaskSerializer();
    private final List<Task> data = new ArrayList<>();

    public void add(Task task) {
        try {
            List<String> stringData = fileManager.read(Configuration.TASKS_FILE_PATH);
            List<Task> tasks = serializer.deserialize(stringData);
            tasks.add(task);
            String serializedTasks = serializer.serialize(tasks);
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (IOException exception) {
            // exception about absent file error
        }
    }

    public List<Task> get() {
        try {
            List<String> stringData = fileManager.read(Configuration.TASKS_FILE_PATH);
            return serializer.deserialize(stringData);
        } catch (IOException exception) {
            // exception about absent file error
        }
        return Collections.emptyList();
    }

    public void update(List<Task> tasks) {
        try {
            String serializedTasks = serializer.serialize(tasks);
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (IOException exception) {
            // exception about absent file error
        }
    }

    public Task delete(int index) {
        return data.remove(index);
    }
}
