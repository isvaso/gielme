package com.isvaso.domain.repository;

import com.isvaso.domain.model.DataVersion;
import com.isvaso.storage.DataVersionStorage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DataVersionRepository {

    private final DataVersionStorage storage;

    public Optional<DataVersion> get() {
        return storage.get();
    }

    public Optional<DataVersion> update(DataVersion dataVersion) {
        storage.update(dataVersion);
        return Optional.of(dataVersion);
    }
}