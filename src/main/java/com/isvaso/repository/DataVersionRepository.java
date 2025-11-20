package com.isvaso.repository;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.DataVersion;
import com.isvaso.storage.DataVersionStorage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DataVersionRepository {

    private final DataVersionStorage storage = new DataVersionStorage();

    public Optional<DataVersion> get() {
        return storage.get();
    }

    public Optional<DataVersion> update(DataVersion dataVersion) throws RepositoryException {
        if (dataVersion == null)
            throw new RepositoryException("Data version is null");
        storage.update(dataVersion);
        return Optional.of(dataVersion);
    }
}