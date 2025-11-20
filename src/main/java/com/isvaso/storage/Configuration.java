package com.isvaso.storage;

import java.nio.file.Path;

public class Configuration {

    private static final int APP_DATA_VERSION = 1;

    private static final String ROOT_DIRECTORY = System.getProperty("user.home");

    private static final String APP_DIRECTORY = "Gielme";

    private static final String FILE_EXTENSION = ".gielme";

    private static final String BACKUP_EXTENSION = ".backup";

    public static final String TASKS_FILE_NAME = "task";

    public static final String DATA_VERSION_FILE_NAME = "data-version";

    private static final Path APP_PATH = Path.of(ROOT_DIRECTORY, APP_DIRECTORY);

    public static final Path TASKS_FILE_PATH = APP_PATH.resolve(TASKS_FILE_NAME + FILE_EXTENSION);

    public static final Path TASK_BACKUP_FILE_PATH = APP_PATH.resolve(TASKS_FILE_PATH + BACKUP_EXTENSION);

    public static final Path DATA_VERSION_FILE_PATH = APP_PATH.resolve(DATA_VERSION_FILE_NAME + FILE_EXTENSION);

    private Configuration() {}
}
