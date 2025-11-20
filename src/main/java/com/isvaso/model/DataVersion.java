package com.isvaso.model;

import lombok.Getter;

@Getter
public class DataVersion {

    private int version;

    public DataVersion(int version) {
        this.version = version;
    }
}