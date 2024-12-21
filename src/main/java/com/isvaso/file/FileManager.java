package com.isvaso.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    public List<String> read(String filePath) {
        try {
            Path path = Path.of(filePath);
            return Files.readAllLines(path);
        } catch (IOException exception) {
            throw new RuntimeException("Error while reading file '%s'".formatted(filePath));
        }
    }

    public boolean write(String filePath, String data) {
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, data);
        } catch (IOException exception) {
            throw new RuntimeException("Error while writing file '%s'".formatted(filePath));
        }
        return true;
    }
}
