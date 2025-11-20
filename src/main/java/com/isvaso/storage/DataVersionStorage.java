package com.isvaso.storage;

import com.isvaso.encryption.Encryptor;
import com.isvaso.encryption.XorEncryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.model.DataVersion;
import com.isvaso.serialization.DataVersionSerializer;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DataVersionStorage {

    private final FileManager fileManager = new FileManager();
    private final DataVersionSerializer serializer = new DataVersionSerializer();
    private final Encryptor encryptor = new XorEncryptor();

    public Optional<DataVersion> get() {
        try {
            String dataFromFile = fileManager.read(Configuration.DATA_VERSION_FILE_PATH);
            String decryptedData = encryptor.decrypt(dataFromFile);
            return serializer.deserialize(decryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while getting tasks", exception);
        }
        return Optional.empty();
    }

    public void update(DataVersion dataVersion) {
        try {
            String serializedTasks = serializer.serialize(dataVersion);
            String encryptedData = encryptor.encrypt(serializedTasks);
            fileManager.write(Configuration.TASKS_FILE_PATH, encryptedData);
        } catch (FileManagerException | SerializerException exception) {
            log.error("Error while updating tasks", exception);
        }
    }
}
