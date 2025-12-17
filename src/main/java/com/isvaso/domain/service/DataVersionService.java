package com.isvaso.domain.service;

import com.isvaso.domain.model.DataVersion;
import com.isvaso.domain.repository.DataVersionRepository;
import lombok.AllArgsConstructor;
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