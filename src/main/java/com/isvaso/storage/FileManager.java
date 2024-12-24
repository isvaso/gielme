package com.isvaso.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    public List<String> read(Path filePath) throws IOException {
        try {
            createFileIfExists(filePath);
            return Files.readAllLines(filePath);
        } catch (IOException exception) {
            throw new IOException("Error while reading file '%s'".formatted(filePath));
        }
    }

    public void write(Path filePath, String data) throws IOException {
        try {
            createFileIfExists(filePath);
            Files.writeString(filePath, data);
        } catch (IOException exception) {
            throw new IOException("Error while writing file '%s'".formatted(filePath));
        }
    }

    private void createFileIfExists(Path filePath) throws IOException {
        try {
            if (!Files.exists(filePath)) {
                if (filePath.getParent() != null) {
                    Files.createDirectories(filePath.getParent());
                }
                Files.createFile(filePath);
            }
        } catch (IOException exception) {
            throw new IOException("Error while reading file '%s'".formatted(filePath));
        }
    }
}
