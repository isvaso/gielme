package com.isvaso.migration;

import com.isvaso.exception.MigrationException;
import com.isvaso.exception.MigrationExecutionException;
import com.isvaso.domain.model.DataVersion;
import com.isvaso.domain.service.DataVersionService;
import com.isvaso.backup.TaskBackupManager;
import com.isvaso.storage.StorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MigrationManager {

    private final TaskBackupManager taskBackupManager;

    private final DataVersionService dataVersionService;

    private final List<Migration> migrations = List.of();

    public void run() {
        Optional<DataVersion> dataVersionOptional = dataVersionService.get();
        int taskFileDataVersion = dataVersionOptional.map(DataVersion::getVersion).orElse(0);
        int appDataVersion = StorageProperties.APP_DATA_VERSION;
        log.info("Current app data version is %s".formatted(appDataVersion));
        if (appDataVersion < taskFileDataVersion)
            throw new MigrationException("Invalid app data version. File version is %s".formatted(taskFileDataVersion));
        if (appDataVersion == taskFileDataVersion) {
            log.info("Data version is correct");
            return;
        }
        tryApplyMigrations(taskFileDataVersion, appDataVersion);
        updateDataVersion(appDataVersion);
    }

    private void tryApplyMigrations(int fromVersion, int toVersion) {
        try {
            backupData();
            applyMigrations(fromVersion, toVersion);
        } catch (MigrationExecutionException exception) {
            restoreData();
            throw new MigrationException("Error while data version migration", exception);
        }
    }

    private void applyMigrations(int fromVersion, int toVersion) {
        log.info("Start migration application from data version {} to data version {}", fromVersion, toVersion);
        while (fromVersion < toVersion) {
            Migration migration = findMigration(fromVersion + 1);
            migration.migrate();
            fromVersion++;
            log.info("Data version was updated to {}", fromVersion);
        }
        log.info("Migration application is complete");
    }

    private void backupData() {
        log.info("Backup data");
        boolean isBackup = taskBackupManager.backup();
        if (!isBackup)
            throw new MigrationException("Failed to make a backup");
    }

    private void restoreData() {
        boolean isRestore = taskBackupManager.restore();
        if (!isRestore)
            throw new MigrationException("Failed to make a backup");
    }

    private void updateDataVersion(int newDataVersion) {
        DataVersion currentDataVersion = new DataVersion(newDataVersion);
        Optional<DataVersion> updatedDataVersionOptional = dataVersionService.update(currentDataVersion);
        if (updatedDataVersionOptional.isEmpty())
            throw new MigrationException("Failed to update data version");
        log.info("Data version migration completed");
    }

    private Migration findMigration(int version) {
        return migrations.stream()
                .filter(migration -> migration.version() == version)
                .findFirst()
                .orElseThrow(() -> new MigrationExecutionException("Migration for data version %s not found".formatted(version)));
    }
}