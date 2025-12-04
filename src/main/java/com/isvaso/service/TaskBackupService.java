package com.isvaso.service;

import com.isvaso.storage.TaskStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskBackupService {

    private final TaskStorage taskStorage;

    public boolean backup()
    {
        return taskStorage.backup();
    }

    public boolean restore()
    {
        return taskStorage.restore();
    }
}
