package com.isvaso.migration;

import com.isvaso.exception.MigrationException;
import com.isvaso.exception.MigrationExecutionException;
import com.isvaso.model.DataVersion;
import com.isvaso.service.DataVersionService;
import com.isvaso.service.TaskBackupService;
import com.isvaso.storage.Configuration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class MigrationManager {

    @Getter
    private static final MigrationManager instance = new MigrationManager();

    private final TaskBackupService taskBackupService = new TaskBackupService();

    private final DataVersionService dataVersionService = DataVersionService.getInstance();

    private final List<Migration> migrations = new ArrayList<>();

    public MigrationManager() {
        initMigrations();
    }

    private void initMigrations() {

    }

    public void run() throws MigrationException {
        Optional<DataVersion> dataVersionOptional = dataVersionService.get();
        int taskFileDataVersion = dataVersionOptional.map(DataVersion::getVersion).orElse(0);
        int appDataVersion = Configuration.APP_DATA_VERSION;
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

    private void tryApplyMigrations(int fromVersion, int toVersion) throws MigrationException {
        try {
            backupData();
            applyMigrations(fromVersion, toVersion);
        } catch (MigrationExecutionException exception) {
            restoreData();
            throw new MigrationException("Error while data version migration", exception);
        }
    }

    private void applyMigrations(int fromVersion, int toVersion) throws MigrationExecutionException {
        log.info("Start migration application from data version {} to data version {}", fromVersion, toVersion);
        while (fromVersion < toVersion) {
            Migration migration = findMigration(fromVersion + 1);
            migration.migrate();
            fromVersion++;
            log.info("Data version was updated to {}", fromVersion);
        }
        log.info("Migration application is complete");
    }

    private void backupData() throws MigrationException {
        log.info("Backup data");
        boolean isBackup = taskBackupService.backup();
        if (!isBackup)
            throw new MigrationException("Failed to make a backup");
    }

    private void restoreData() throws MigrationException {
        boolean isRestore = taskBackupService.restore();
        if (!isRestore)
            throw new MigrationException("Failed to make a backup");
    }

    private void updateDataVersion(int newDataVersion) throws MigrationException {
        DataVersion currentDataVersion = new DataVersion(newDataVersion);
        Optional<DataVersion> updatedDataVersionOptional = dataVersionService.update(currentDataVersion);
        if (updatedDataVersionOptional.isEmpty())
            throw new MigrationException("Failed to update data version");
        log.info("Data version migration completed");
    }

    private Migration findMigration(int version) throws MigrationExecutionException {
        return migrations.stream()
                .filter(migration -> migration.version() == version)
                .findFirst()
                .orElseThrow(() -> new MigrationExecutionException("Migration for data version %s not found".formatted(version)));
    }
}