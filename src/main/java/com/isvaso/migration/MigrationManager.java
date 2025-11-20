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
        try {
            Optional<DataVersion> dataVersionOptional = dataVersionService.get();
            int taskFileDataVersion = dataVersionOptional.map(DataVersion::getVersion).orElse(0);
            final int appDataVersion = Configuration.APP_DATA_VERSION;
            if (appDataVersion < taskFileDataVersion)
                throw new MigrationException(
                        "Invalid app data version. App data version is %s but task file with %s".formatted(
                                appDataVersion, taskFileDataVersion
                        )
                );
            if (appDataVersion == taskFileDataVersion) {
                log.info("Data version is correct");
                return;
            }
            taskBackupService.backup();
            while (taskFileDataVersion < appDataVersion) {
                Migration migration = findMigration(taskFileDataVersion + 1);
                migration.migrate();
                taskFileDataVersion++;
                log.info("Data version was updated to {}", taskFileDataVersion);
            }
            DataVersion currentDataVersion = new DataVersion(appDataVersion);
            Optional<DataVersion> updatedDataVersionOptional = dataVersionService.update(currentDataVersion);
            if (updatedDataVersionOptional.isEmpty())
                throw new MigrationException("Failed to update data version");
            log.info("Data version migration completed");
        } catch (MigrationExecutionException exception) {
            taskBackupService.restore();
            throw new MigrationException("Error while data version migration", exception);
        }
    }

    private Migration findMigration(int version) throws MigrationExecutionException {
        return migrations.stream()
                .filter(migration -> migration.version() == version)
                .findFirst()
                .orElseThrow(() -> new MigrationExecutionException("Migration for data version %s not found".formatted(version)));
    }
}