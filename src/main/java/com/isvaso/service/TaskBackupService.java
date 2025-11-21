package com.isvaso.service;

import com.isvaso.storage.TaskStorage;

public class TaskBackupService {

    private final TaskStorage taskStorage = new TaskStorage();

    public boolean backup()
    {
        return taskStorage.backup();
    }

    public boolean restore()
    {
        return taskStorage.restore();
    }
}
