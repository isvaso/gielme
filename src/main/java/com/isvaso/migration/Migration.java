package com.isvaso.migration;

public interface Migration {

    int version();

    void migrate();
}