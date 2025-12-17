package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.domain.model.DataVersion;
import com.isvaso.util.StringValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DataVersionSerializer {

    public String serialize(DataVersion dataVersion) throws SerializerException {
        if (dataVersion == null)
            throw new SerializerException("DataVersion cannot be null");
        if (dataVersion.getVersion() < 0)
            throw new SerializerException("Version should be positive, but now is ''%s'".formatted(dataVersion.getVersion()));
        return String.valueOf(dataVersion.getVersion());
    }

    public Optional<DataVersion> deserialize(String string) throws SerializerException {
        if (StringValidator.isBlankOrNull(string))
            throw new SerializerException("Invalid string with data version '%s'".formatted(string));
        try {
            int version = Integer.parseInt(string);
            if (version < 0)
                throw new SerializerException("Version should be positive, but now is '%s'".formatted(version));
            return Optional.of(new DataVersion(version));
        } catch (NumberFormatException exception) {
            throw new SerializerException("Error while parsing data version '%s'".formatted(string), exception);
        }
    }
}