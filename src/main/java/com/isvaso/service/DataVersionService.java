package com.isvaso.service;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.DataVersion;
import com.isvaso.repository.DataVersionRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DataVersionService {

    @Getter
    private static final DataVersionService instance = new DataVersionService();

    private final DataVersionRepository repository = new DataVersionRepository();

    private DataVersionService() {
    }

    public Optional<DataVersion> get() {
        return repository.get();
    }

    public void update(DataVersion dataVersion) {
        try {
            repository.update(dataVersion);
        } catch (RepositoryException exception) {
            log.error("Error while updating data version", exception);
        }
    }
}