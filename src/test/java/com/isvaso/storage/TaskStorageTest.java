package com.isvaso.storage;

import com.isvaso.domain.model.Task;
import com.isvaso.encryption.Encryptor;
import com.isvaso.exception.FileManagerException;
import com.isvaso.exception.SerializerException;
import com.isvaso.exception.StorageException;
import com.isvaso.files.FileManager;
import com.isvaso.serialization.TaskSerializer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.isvaso.storage.StorageProperties.TASKS_FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskStorageTest {

    @Mock
    private FileManager fileManager;

    @Mock
    private TaskSerializer serializer;

    @Mock
    private Encryptor encryptor;

    @Spy
    @InjectMocks
    private TaskStorage taskStorage;

    @Nested
    class Add {

        @Test
        void shouldThrowStorageException_whenTaskIsNull() {
            Task task = null;

            assertThrows(StorageException.class, () -> taskStorage.add(task));
        }

        @Test
        void shouldWriteEncryptedSerializedTasksToFile_whenValidTask() throws SerializerException, StorageException, FileManagerException {
            Task task = new Task("Test task");
            List<Task> allTasks = List.of(task);
            String serializedTasks = "Serialized tasks";
            String encryptedTasks = "Encrypted tasks";

            when(taskStorage.get()).thenReturn(new ArrayList<>());
            when(serializer.serializeList(allTasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(encryptedTasks);

            taskStorage.add(task);

            InOrder inOrder = inOrder(serializer, encryptor, fileManager);
            inOrder.verify(serializer).serializeList(allTasks);
            inOrder.verify(encryptor).encrypt(serializedTasks);
            inOrder.verify(fileManager).write(TASKS_FILE_PATH, encryptedTasks);
        }

        @Test
        void shouldAddTaskToExist_whenValidTask() throws SerializerException, StorageException, FileManagerException {
            Task taskToAdd = new Task("Test task 3");
            List<Task> existTasks = List.of(
                    new Task("Test task"),
                    new Task("Test task 2")
            );
            List<Task> expectedTasks = List.of(
                    new Task("Test task"),
                    new Task("Test task 2"),
                    new Task("Test task 3")
            );
            String serializedTasks = "Serialized tasks";
            String encryptedTasks = "Encrypted tasks";

            doReturn(new ArrayList<>(existTasks)).when(taskStorage).get();
            when(serializer.serializeList(anyList())).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(encryptedTasks);

            taskStorage.add(taskToAdd);

            verify(serializer).serializeList(expectedTasks);
            verify(fileManager).write(TASKS_FILE_PATH, encryptedTasks);
        }

        @Test
        void shouldThrowStorageException_whenSerializationFail() throws SerializerException, StorageException, FileManagerException {
            Task task = new Task("Test task");
            List<Task> allTasks = List.of(task);

            doReturn(new ArrayList<>()).when(taskStorage).get();
            doThrow(new SerializerException("")).when(serializer).serializeList(allTasks);

            verify(encryptor, never()).encrypt(anyString());
            verify(fileManager, never()).write(any(), anyString());

            assertThrows(StorageException.class, () -> taskStorage.add(task));
        }

        @Test
        void shouldThrowStorageException_whenTasksFileWritingFail() throws SerializerException, StorageException, FileManagerException {
            Task task = new Task("Test task");
            List<Task> allTasks = List.of(task);
            String serializedTasks = "Serialized task";
            String encryptedTasks = "Encrypted task";

            doReturn(new ArrayList<>()).when(taskStorage).get();
            when(serializer.serializeList(allTasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(encryptedTasks);
            doThrow(new FileManagerException("")).when(fileManager).write(TASKS_FILE_PATH, encryptedTasks);

            assertThrows(StorageException.class, () -> taskStorage.add(task));
        }
    }

    @Nested
    class Get {

        @Test
        void shouldReturnEmptyList_whenTasksFileIsEmpty() throws FileManagerException, SerializerException, StorageException {
            List<Task> expectedTasks = new ArrayList<>();
            String dataFromFile = "";

            when(fileManager.read(TASKS_FILE_PATH)).thenReturn(dataFromFile);

            List<Task> actualTasks = taskStorage.get();

            verify(encryptor, never()).decrypt(anyString());
            verify(serializer, never()).deserializeList(anyString());

            assertEquals(expectedTasks, actualTasks);
        }

        @Test
        void shouldReturnExpectedTasks_whenTasksExist() throws FileManagerException, SerializerException, StorageException {
            List<Task> expectedTasks = List.of(
                    new Task("Test task"),
                    new Task("Test task 2"),
                    new Task("Test task 3")
            );
            String dataFromFile = "Tasks list";
            String decryptedData = "Tasks list";

            when(fileManager.read(TASKS_FILE_PATH)).thenReturn(dataFromFile);
            when(encryptor.decrypt(dataFromFile)).thenReturn(decryptedData);
            when(serializer.deserializeList(decryptedData)).thenReturn(expectedTasks);

            List<Task> actualTasks = taskStorage.get();

            assertEquals(expectedTasks, actualTasks);
        }

        @Test
        void shouldDecryptDeserializeTasksFile_whenInvoked() throws FileManagerException, SerializerException, StorageException {
            List<Task> expectedTasks = List.of();
            String dataFromFile = "Tasks list";
            String decryptedData = "Tasks list";

            when(fileManager.read(TASKS_FILE_PATH)).thenReturn(dataFromFile);
            when(encryptor.decrypt(dataFromFile)).thenReturn(decryptedData);
            when(serializer.deserializeList(decryptedData)).thenReturn(expectedTasks);

            taskStorage.get();

            InOrder inOrder = inOrder(serializer, encryptor, fileManager);
            inOrder.verify(fileManager).read(TASKS_FILE_PATH);
            inOrder.verify(encryptor).decrypt(dataFromFile);
            inOrder.verify(serializer).deserializeList(decryptedData);

        }

        @Test
        void shouldThrowStorageException_whenTasksFileReadingFail_when() throws FileManagerException {
            doThrow(new FileManagerException("")).when(fileManager).read(TASKS_FILE_PATH);

            assertThrows(StorageException.class, () -> taskStorage.get());
        }

        @Test
        void shouldThrowStorageException_whenDeserializationFail_when() throws SerializerException, FileManagerException {
            String dataFromFile = "Tasks list";
            String decryptedData = "Tasks list";
            when(fileManager.read(TASKS_FILE_PATH)).thenReturn(dataFromFile);
            when(encryptor.decrypt(dataFromFile)).thenReturn(decryptedData);
            doThrow(new SerializerException("")).when(serializer).deserializeList(any());

            assertThrows(StorageException.class, () -> taskStorage.get());
        }
    }

    @Nested
    class Update {

        @Test
        void shouldThrowStorageException_whenTaskIsNull() {
            List<Task> tasks = null;

            assertThrows(StorageException.class, () -> taskStorage.update(tasks));
        }

        @Test
        void shouldWriteEmptyData_whenNoTasks() throws SerializerException, StorageException, FileManagerException {
            List<Task> tasks = List.of();
            String serializedTasks = "";
            String expectedEncryptedTasks = "";

            when(serializer.serializeList(tasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(expectedEncryptedTasks);

            taskStorage.update(tasks);

            verify(fileManager).write(TASKS_FILE_PATH, expectedEncryptedTasks);
            verifyNoMoreInteractions(fileManager);
        }

        @Test
        void shouldWriteToFileOnlyExpectedTasks_whenInvoked() throws SerializerException, StorageException, FileManagerException {
            List<Task> tasks = List.of(
                    new Task("Test task"),
                    new Task("Test task 2"),
                    new Task("Test task 3")
            );
            String serializedTasks = "Serialized tasks";
            String expectedEncryptedTasks = "Encrypted tasks";

            when(serializer.serializeList(tasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(expectedEncryptedTasks);

            taskStorage.update(tasks);

            verify(fileManager).write(TASKS_FILE_PATH, expectedEncryptedTasks);
            verifyNoMoreInteractions(fileManager);
        }

        @Test
        void shouldWriteEncryptedSerializedTasksToFile_whenValidTask() throws SerializerException, StorageException, FileManagerException {
            Task task = new Task("Test task");
            List<Task> tasks = List.of(task);
            String serializedTasks = "Serialized tasks";
            String encryptedTasks = "Encrypted tasks";

            when(serializer.serializeList(tasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(encryptedTasks);

            taskStorage.update(tasks);

            InOrder inOrder = inOrder(serializer, encryptor, fileManager);
            inOrder.verify(serializer).serializeList(tasks);
            inOrder.verify(encryptor).encrypt(serializedTasks);
            inOrder.verify(fileManager).write(TASKS_FILE_PATH, encryptedTasks);
        }

        @Test
        void shouldThrowStorageException_whenSerializationFail() throws SerializerException, StorageException, FileManagerException {
            List<Task> tasks = List.of();

            doThrow(new SerializerException("")).when(serializer).serializeList(tasks);

            verify(encryptor, never()).encrypt(anyString());
            verify(fileManager, never()).write(any(), anyString());

            assertThrows(StorageException.class, () -> taskStorage.update(tasks));
        }

        @Test
        void shouldThrowStorageException_whenTasksFileWritingFail() throws SerializerException, StorageException, FileManagerException {
            List<Task> tasks = List.of();
            String serializedTasks = "";
            String encryptedTasks = "";

            when(serializer.serializeList(tasks)).thenReturn(serializedTasks);
            when(encryptor.encrypt(serializedTasks)).thenReturn(encryptedTasks);
            doThrow(new FileManagerException("")).when(fileManager).write(TASKS_FILE_PATH, encryptedTasks);

            assertThrows(StorageException.class, () -> taskStorage.update(tasks));
        }
    }
}