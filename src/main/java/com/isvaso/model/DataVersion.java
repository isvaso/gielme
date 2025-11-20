package com.isvaso.model;

import lombok.Getter;

@Getter
public class DataVersion {

    private final int version;

    public DataVersion(int version) {
        this.version = version;
    }
}