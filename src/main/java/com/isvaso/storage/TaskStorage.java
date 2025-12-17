package com.isvaso.storage;

import com.isvaso.encryption.Encryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.domain.model.Task;
import com.isvaso.files.FileManager;
import com.isvaso.serialization.TaskSerializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.isvaso.storage.StorageProperties.*;

@Slf4j
@AllArgsConstructor
public class TaskStorage {

    private final FileManager fileManager;
    private final TaskSerializer serializer;
    private final Encryptor encryptor;

    public void add(Task task) {
        List<Task> tasks = get();
        tasks.add(task);
        try {
            String serializedTasks = serializer.serializeList(tasks);
            String encryptedTasks = encryptor.encrypt(serializedTasks);
            fileManager.write(TASKS_FILE_PATH, encryptedTasks);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while adding task", exception);
        }
    }

    public List<Task> get() {
        try {
            String dataFromFile = fileManager.read(TASKS_FILE_PATH);
            String decryptedData = encryptor.decrypt(dataFromFile);
            return serializer.deserializeList(decryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while getting tasks", exception);
        }
        return new ArrayList<>();
    }

    public void update(List<Task> tasks) {
        try {
            String serializedTasks = serializer.serializeList(tasks);
            String encryptedData = encryptor.encrypt(serializedTasks);
            fileManager.write(TASKS_FILE_PATH, encryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while updating tasks", exception);
        }
    }
}
