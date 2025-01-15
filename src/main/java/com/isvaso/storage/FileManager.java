package com.isvaso.storage;

import com.isvaso.exception.FileManagerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public String read(Path filePath) throws FileManagerException {
        try {
            return Files.readString(filePath);
        } catch (IOException exception) {
            throw new FileManagerException("Error while reading file", exception);
        }
    }

    public void write(Path filePath, String data) throws FileManagerException {
        try {
            Files.writeString(filePath, data, StandardOpenOption.CREATE);
        } catch (IOException exception) {
            throw new FileManagerException("Error while writing file", exception);
        }
    }
}
