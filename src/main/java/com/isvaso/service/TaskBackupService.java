package com.isvaso.service;

import com.isvaso.storage.TaskStorage;

public class TaskBackupService {

    private final TaskStorage taskStorage = new TaskStorage();

    public void backup()
    {
        taskStorage.backup();
    }

    public void restore()
    {
        taskStorage.restore();
    }
}
