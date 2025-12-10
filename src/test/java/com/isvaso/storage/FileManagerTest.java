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

    @Nested
    class Write {

        @Test
        void shouldThrowFileManagerException_whenFilePathIsNull() {
            Path filePath = null;
            String data = "File contents";

            assertThrows(FileManagerException.class, () -> fileManager.write(filePath, data));
        }

        @Test
        void shouldNotThrowException_whenDirectoryIsNull() {
            Path filePath = Path.of("file.txt");
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(filePath)).thenReturn(true);

                filesMock.when(() -> Files.writeString(filePath, fileContents)).thenReturn(filePath);

                assertDoesNotThrow(() -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldNotThrowException_whenDirectoryDoesNotExist() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(false);

                assertDoesNotThrow(() -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryIsNotDirectory() {
            Path filePath = Path.of("/directory.txt/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(false);
                filesMock.when(() -> Files.isExecutable(parent)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryIsNotWritable() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isExecutable(parent)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(parent)).thenReturn(false);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenDirectoryIsNotExecutable() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isExecutable(parent)).thenReturn(false);
                filesMock.when(() -> Files.isWritable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(filePath)).thenReturn(true);

                assertThrows(FileManagerException.class, () -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldNotThrowException_whenFileIsDirectory() {
            Path filePath = Path.of("file.txt");
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(false);

                filesMock.when(() -> Files.writeString(filePath, fileContents)).thenReturn(filePath);
                ;

                assertDoesNotThrow(() -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenFileIsNotWritable() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(parent)).thenReturn(true);
                filesMock.when(() -> Files.isDirectory(parent)).thenReturn(true);
                filesMock.when(() -> Files.isExecutable(parent)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(parent)).thenReturn(true);
                filesMock.when(() -> Files.exists(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isRegularFile(filePath)).thenReturn(true);
                filesMock.when(() -> Files.isWritable(filePath)).thenReturn(false);

                assertThrows(FileManagerException.class, () -> fileManager.write(filePath, fileContents));
            }
        }

        @Test
        void shouldCreateParentDirectory_whenParentDirectoryDoesNotExist() throws FileManagerException {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.createDirectories(parent)).thenReturn(parent);
                filesMock.when(() -> Files.writeString(filePath, fileContents)).thenReturn(filePath);

                fileManager.write(filePath, fileContents);

                filesMock.verify(() -> Files.createDirectories(parent));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenCreatingParentDirectoryFails() {
            Path filePath = Path.of("/directory/file.txt");
            Path parent = filePath.getParent();
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.createDirectories(parent)).thenThrow(new IOException());

                FileManagerException exception = assertThrows(
                        FileManagerException.class,
                        () -> fileManager.write(filePath, fileContents)
                );

                assertInstanceOf(IOException.class, exception.getCause());
                filesMock.verify(() -> Files.writeString(any(), any()), never());
            }
        }

        @Test
        void shouldWriteString_whenWriteIsInvoked() {
            Path filePath = Path.of("file.txt");
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(false);

                filesMock.when(() -> Files.writeString(
                        filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                )).thenReturn(filePath);

                assertDoesNotThrow(() -> fileManager.write(filePath, fileContents));

                filesMock.verify(() -> Files.writeString(
                        filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                ));
            }
        }

        @Test
        void shouldWriteExactDataToFile_whenWriteIsInvoked() throws FileManagerException {
            Path filePath = Path.of("file.txt");
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(false);
                filesMock.when(() -> Files.writeString(
                        filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                )).thenReturn(filePath);

                fileManager.write(filePath, fileContents);

                filesMock.verify(() -> Files.writeString(
                        filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                ));
            }
        }

        @Test
        void shouldThrowFileManagerException_whenWritingFails() {
            Path filePath = Path.of("file.txt");
            String fileContents = "File contents";

            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.exists(filePath)).thenReturn(false);
                filesMock.when(() -> Files.writeString(
                        filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                )).thenThrow(new IOException("IO error"));

                FileManagerException exception = assertThrows(
                        FileManagerException.class,
                        () -> fileManager.write(filePath, fileContents)
                );

                assertInstanceOf(IOException.class, exception.getCause());
                filesMock.verify(() -> Files.writeString(filePath,
                        fileContents,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                ));
            }
        }
    }

    // TODO:
    //  2 Tests for copy(), check for file
    //  3 Tests for delete(), check for file
}
