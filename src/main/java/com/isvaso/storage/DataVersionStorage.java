package com.isvaso.storage;

import com.isvaso.encryption.Encryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.domain.model.DataVersion;
import com.isvaso.files.FileManager;
import com.isvaso.serialization.DataVersionSerializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.isvaso.storage.StorageProperties.*;

@Slf4j
@AllArgsConstructor
public class DataVersionStorage {

    private final FileManager fileManager;
    private final DataVersionSerializer serializer;
    private final Encryptor encryptor;

    public Optional<DataVersion> get() {
        try {
            String dataFromFile = fileManager.read(DATA_VERSION_FILE_PATH);
            String decryptedData = encryptor.decrypt(dataFromFile);
            return serializer.deserialize(decryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while getting data version", exception);
        }
        return Optional.empty();
    }

    public void update(DataVersion dataVersion) {
        try {
            String serializedTasks = serializer.serialize(dataVersion);
            String encryptedData = encryptor.encrypt(serializedTasks);
            fileManager.write(TASKS_FILE_PATH, encryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while updating tasks", exception);
        }
    }
}
