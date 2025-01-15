package com.isvaso.storage;

import com.isvaso.exception.FileManagerException;
import com.isvaso.model.Task;
import com.isvaso.serialization.TaskSerializer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TaskStorage {

    private final FileManager fileManager = new FileManager();
    private final TaskSerializer serializer = new TaskSerializer();

    public void add(Task task) {
        List<Task> tasks = get();
        tasks.add(task);
        String serializedTasks = serializer.serializeList(tasks);
        try {
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (FileManagerException exception) {
            log.error("Error while adding task", exception);
        }
    }

    public List<Task> get() {
        try {
            String dataFromFile = fileManager.read(Configuration.TASKS_FILE_PATH);
            return serializer.deserializeList(dataFromFile);
        } catch (FileManagerException exception) {
            log.error("Error while getting tasks", exception);
        }
        return new ArrayList<>();
    }

    public void update(List<Task> tasks) {
        try {
            String serializedTasks = serializer.serializeList(tasks);
            fileManager.write(Configuration.TASKS_FILE_PATH, serializedTasks);
        } catch (FileManagerException exception) {
            log.error("Error while updating tasks", exception);
        }
    }
}
