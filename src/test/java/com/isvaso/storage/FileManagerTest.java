package com.isvaso.storage;

import com.isvaso.exception.FileManagerException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;

class FileManagerTest {

    private final FileManager fileManager = new FileManager();

    @Nested
    class Read {

        @Test
        void shouldThrowFileManagerException_whenFilePathIsNull() {
            Path filePath = null;

            assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
        }

        @Test
        void shouldNotThrowException_whenDirectoryIsNull() {
            Path filePath = Path.of("file.txt");
            String expectedFileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                filesMock.when(() -> Files.readString(filePath)).thenReturn(expectedFileContents);

                assertDoesNotThrow(() -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryDoesNotExists() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(false);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryIsNotDirectory() {
            Path filePath = Path.of("/directory.txt/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(false);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryIsNotReadable() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(false);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenFileIsNotRegular() {
            Path filePath = Path.of("/directory/file");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(false);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenFileDoesNotExist() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(false);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenFileIsNotReadable() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(false);

                assertThrows(FileManagerException.class, () -> fileManager.read(filePath));
            }
        }

        @Test
        void shouldReadFile_whenFileExists() throws FileManagerException {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String expectedFileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                filesMock.when(() -> Files.readString(filePath)).thenReturn(expectedFileContents);

                String actual = fileManager.read(filePath);
                assertEquals(expectedFileContents, actual);
            }
        }

        @Test
        void shouldThrowFileManagerException_whenReadingFails() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isReadable(filePath)).thenReturn(true);

                filesMock.when(() -> Files.readString(filePath)).thenThrow(new IOException());

                FileManagerException exception = assertThrows(
                        FileManagerException.class,
                        () -> fileManager.read(filePath)
                );

                assertInstanceOf(IOException.class, exception.getCause());
                filesMock.verify(() -> Files.readString(filePath));
            }
        }
    }
}
