package com.isvaso.storage;

import com.isvaso.exception.FileManagerException;
import com.isvaso.model.Task;
import com.isvaso.serialization.TaskSerializer;

import java.util.Collections;
import java.util.List;

public class TaskStorage {

    private final FileManager fileManager = new FileManager();
    private final TaskSerializer serializer = new TaskSerializer();

    public void add(Task task) {
        List<Task> tasks = get();
        tasks.add(task);
        String serializedTasks = serializer.serialize(tasks);
        try {
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (FileManagerException exception) {
            // log exception
        }
    }

    public List<Task> get() {
        try {
            List<String> stringData = fileManager.read(Configuration.TASKS_FILE_PATH);
            return serializer.deserialize(stringData);
        } catch (FileManagerException exception) {
            // log exception
        }
        return Collections.emptyList();
    }

    public void update(List<Task> tasks) {
        try {
            String serializedTasks = serializer.serialize(tasks);
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (FileManagerException exception) {
            // log exception
        }
    }
}
