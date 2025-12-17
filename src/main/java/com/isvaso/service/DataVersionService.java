package com.isvaso.service;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.DataVersion;
import com.isvaso.repository.DataVersionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class DataVersionService {

    private final DataVersionRepository repository;

    public Optional<DataVersion> get() {
        return repository.get();
    }

    public Optional<DataVersion> update(DataVersion dataVersion) {
        if (dataVersion == null) {
            log.error("DataVersion cannot be null");
            return Optional.empty();
        }
        return repository.update(dataVersion);
    }
}