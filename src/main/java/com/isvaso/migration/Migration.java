package com.isvaso.migration;

import com.isvaso.exception.MigrationExecutionException;

public interface Migration {

    int version();

    void migrate() throws MigrationExecutionException;
}