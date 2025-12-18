package com.isvaso.storage;

import com.isvaso.domain.model.Task;
import com.isvaso.encryption.Encryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.exception.StorageException;
import com.isvaso.files.FileManager;
import com.isvaso.serialization.TaskSerializer;
import com.isvaso.util.StringValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

import static com.isvaso.storage.StorageProperties.TASKS_FILE_PATH;

@Slf4j
@AllArgsConstructor
public class TaskStorage {

    private final FileManager fileManager;
    private final TaskSerializer serializer;
    private final Encryptor encryptor;

    public void add(Task task) throws StorageException {
        if(task == null)
            throw new StorageException("Task cannot be null null");
        List<Task> tasks = get();
        tasks.add(task);
        try {
            String serializedTasks = serializer.serializeList(tasks);
            String encryptedTasks = encryptor.encrypt(serializedTasks);
            fileManager.write(TASKS_FILE_PATH, encryptedTasks);
        } catch (FileManagerException | SerializerException exception) {
            throw new StorageException("Error while adding task", exception);
        }
    }

    public List<Task> get() throws StorageException {
        try {
            String dataFromFile = fileManager.read(TASKS_FILE_PATH);
            if(StringValidator.isBlankOrNull(dataFromFile))
                return Collections.emptyList();
            String decryptedData = encryptor.decrypt(dataFromFile);
            return serializer.deserializeList(decryptedData);
        } catch (FileManagerException | SerializerException exception) {
            throw new StorageException("Error while getting tasks", exception);
        }
    }

    public void update(List<Task> tasks) throws StorageException {
        if(tasks == null)
            throw new StorageException("Tasks cannot be null null");
        try {
            String serializedTasks = serializer.serializeList(tasks);
            String encryptedData = encryptor.encrypt(serializedTasks);
            fileManager.write(TASKS_FILE_PATH, encryptedData);
        } catch (FileManagerException | SerializerException exception) {
            throw new StorageException("Error while updating task", exception);
        }
    }
}
