package com.isvaso.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DataVersion {

    private final int version;

    public DataVersion(int version) {
        this.version = version;
    }
}