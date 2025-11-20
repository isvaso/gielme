package com.isvaso.storage;

import com.isvaso.exception.FileManagerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public String read(Path filePath) throws FileManagerException {
        try {
            checkDirectoryForRead(filePath);
            checkFileForRead(filePath);
            return Files.readString(filePath);
        } catch (IOException exception) {
            throw new FileManagerException("Error while reading file", exception);
        }
    }

    public void write(Path filePath, String data) throws FileManagerException {
        try {
            checkDirectoryForWrite(filePath);
            checkFileForWrite(filePath);
            prepareDirectory(filePath);
            Files.writeString(filePath, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            throw new FileManagerException("Error while writing file", exception);
        }
    }

    public void copy(Path fromPath, Path toPath) throws FileManagerException {
        checkFileForRead(fromPath);
        try {
            Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new FileManagerException("Error while copy file", exception);
        }
    }

    public void delete(Path filePath) throws FileManagerException {
        checkFileForDelete(filePath);
        try {
            Files.delete(filePath);
        } catch (IOException exception) {
            throw new FileManagerException("Error while delete file", exception);
        }
    }

    private void checkDirectoryForRead(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        Path fileDirectory = filePath.getParent();
        if (fileDirectory == null)
            return;
        if (!Files.exists(fileDirectory))
            throw new FileManagerException("Directory does not exist: %s".formatted(fileDirectory));
        if (!Files.isDirectory(fileDirectory))
            throw new FileManagerException("Parent path exists but is not a directory: %s".formatted(fileDirectory));
        if (!Files.isReadable(fileDirectory))
            throw new FileManagerException("Directory is not readable: %s".formatted(fileDirectory));
    }

    private void checkDirectoryForWrite(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        Path fileDirectory = filePath.getParent();
        if (fileDirectory == null)
            return;
        if (!Files.exists(fileDirectory))
            return;
        if (!Files.isDirectory(fileDirectory))
            throw new FileManagerException("Parent path exists but is not a directory: %s".formatted(fileDirectory));
        if (!Files.isWritable(fileDirectory))
            throw new FileManagerException("Directory is not writable: %s".formatted(fileDirectory));
        if (!Files.isExecutable(fileDirectory))
            throw new FileManagerException("Directory is not accessible: %s".formatted(fileDirectory));
    }

    private void checkFileForRead(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        if (!Files.exists(filePath))
            throw new FileManagerException("File does not exist: %s".formatted(filePath));
        if (!Files.isRegularFile(filePath))
            throw new FileManagerException("Path is not a regular file: %s".formatted(filePath));
        if (!Files.isReadable(filePath))
            throw new FileManagerException("File is not readable: %s".formatted(filePath));
    }

    private void checkFileForWrite(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        if (!Files.exists(filePath))
            return;
        if (Files.isDirectory(filePath))
            throw new FileManagerException("Target path is a directory, not a file: %s".formatted(filePath));
        if (!Files.isWritable(filePath))
            throw new FileManagerException("File is not writable: %s".formatted(filePath));
    }

    private void checkFileForDelete(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        if (!Files.exists(filePath))
            throw new FileManagerException("File does not exist: %s".formatted(filePath));
        if (!Files.isRegularFile(filePath))
            throw new FileManagerException("Path is not a regular file: %s".formatted(filePath));
        if (!Files.isWritable(filePath))
            throw new FileManagerException("File is not readable: %s".formatted(filePath));
    }

    private void prepareDirectory(Path filePath) throws FileManagerException {
        if (filePath == null)
            throw new FileManagerException("File path cannot be null");
        Path parent = filePath.getParent();
        if (parent == null)
            return;
        try {
            Files.createDirectories(parent);
        } catch (IOException exception) {
            throw new FileManagerException("Error while creating parent directory", exception);
        }
    }
}
