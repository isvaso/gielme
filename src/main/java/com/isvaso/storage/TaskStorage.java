package com.isvaso.storage;

import com.isvaso.encryption.Encryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.model.Task;
import com.isvaso.serialization.TaskSerializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
            fileManager.write(StorageProperties.TASKS_FILE_PATH, encryptedTasks);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while adding task", exception);
        }
    }

    public List<Task> get() {
        try {
            String dataFromFile = fileManager.read(StorageProperties.TASKS_FILE_PATH);
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
            fileManager.write(StorageProperties.TASKS_FILE_PATH, encryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while updating tasks", exception);
        }
    }

    public boolean backup() {
        try {
            fileManager.copy(StorageProperties.TASKS_FILE_PATH, StorageProperties.TASK_BACKUP_FILE_PATH);
            return true;
        } catch (FileManagerException exception) {
            log.error("Error while backup task", exception);
        }
        return false;
    }

    public boolean restore() {
        try {
            fileManager.delete(StorageProperties.TASKS_FILE_PATH);
            fileManager.copy(StorageProperties.TASK_BACKUP_FILE_PATH, StorageProperties.TASKS_FILE_PATH);
            fileManager.delete(StorageProperties.TASK_BACKUP_FILE_PATH);
            return true;
        } catch (FileManagerException exception) {
            log.error("Error while backup task", exception);
        }
        return false;
    }
}
