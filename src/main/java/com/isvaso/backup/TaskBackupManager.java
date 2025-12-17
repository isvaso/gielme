package com.isvaso.backup;

import com.isvaso.exception.FileManagerException;
import com.isvaso.files.FileManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.isvaso.storage.StorageProperties.*;

@Slf4j
@AllArgsConstructor
public class TaskBackupManager implements BackupManager {

    private final FileManager fileManager;

    @Override
    public boolean backup() {
        try {
            fileManager.copy(TASKS_FILE_PATH, TASK_BACKUP_FILE_PATH);
            return true;
        } catch (FileManagerException exception) {
            log.error("Error while backup task", exception);
        }
        return false;
    }

    @Override
    public boolean restore() {
        try {
            fileManager.copy(TASK_BACKUP_FILE_PATH, TASKS_FILE_PATH);
            fileManager.delete(TASK_BACKUP_FILE_PATH);
            return true;
        } catch (FileManagerException exception) {
            log.error("Error while backup task", exception);
        }
        return false;
    }
}
