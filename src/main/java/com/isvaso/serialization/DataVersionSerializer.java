package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.model.DataVersion;
import com.isvaso.util.StringValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DataVersionSerializer {

    public String serialize(DataVersion dataVersion) throws SerializerException {
        return String.valueOf(dataVersion.getVersion());
    }

    public Optional<DataVersion> deserialize(String string) throws SerializerException {
        if (StringValidator.isEmptyOrNull(string))
            throw new SerializerException("Invalid string with data version");
        int version = Integer.parseInt(string);
        return Optional.of(new DataVersion(version));
    }
}