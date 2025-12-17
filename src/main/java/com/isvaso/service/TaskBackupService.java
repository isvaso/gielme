package com.isvaso.service;

import com.isvaso.storage.BackupStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskBackupService {

    private final BackupStorage backupStorage;

    public boolean backup()
    {
        return backupStorage.backup();
    }

    public boolean restore()
    {
        return backupStorage.restore();
    }
}
