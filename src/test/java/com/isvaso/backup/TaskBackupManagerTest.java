package com.isvaso.backup;

import com.isvaso.exception.FileManagerException;
import com.isvaso.files.FileManager;
import com.isvaso.storage.StorageProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.isvaso.storage.StorageProperties.TASKS_FILE_PATH;
import static com.isvaso.storage.StorageProperties.TASK_BACKUP_FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskBackupManagerTest {

    @Mock
    private FileManager fileManager;

    @InjectMocks
    private TaskBackupManager taskBackupManager;

    @Nested
    class Backup {

        @Test
        void shouldCopyTaskFileToBackup_whenInvoked() throws FileManagerException {
            taskBackupManager.backup();

            verify(fileManager).copy(TASKS_FILE_PATH, TASK_BACKUP_FILE_PATH);
            verifyNoMoreInteractions(fileManager);
        }

        @Test
        void shouldReturnTrue_whenSucceed() throws FileManagerException {
            boolean result = taskBackupManager.backup();

            assertTrue(result);
        }

        @Test
        void shouldReturnFalse_whenCopyThrowsException() throws FileManagerException {
            doThrow(new FileManagerException("")).when(fileManager).copy(TASKS_FILE_PATH, TASK_BACKUP_FILE_PATH);

            boolean result = taskBackupManager.backup();

            assertFalse(result);
        }
    }

    @Nested
    class Restore {

        @Test
        void shouldDeleteTasksCopyBackupDeleteBackup_whenInvoked() throws FileManagerException {
            taskBackupManager.restore();

            InOrder inOrder = inOrder(fileManager);
            inOrder.verify(fileManager).copy(TASK_BACKUP_FILE_PATH, TASKS_FILE_PATH);
            inOrder.verify(fileManager).delete(TASK_BACKUP_FILE_PATH);
            inOrder.verifyNoMoreInteractions();
        }

        @Test
        void shouldReturnTrue_whenSucceed() throws FileManagerException {
            boolean result = taskBackupManager.restore();

            assertTrue(result);
        }

        @Test
        void shouldReturnFalse_whenCopyThrowsException() throws FileManagerException {
            doThrow(new FileManagerException("")).when(fileManager).copy(TASK_BACKUP_FILE_PATH, TASKS_FILE_PATH);

            boolean result = taskBackupManager.restore();

            assertFalse(result);
        }

        @Test
        void shouldReturnFalse_whenDeleteThrowsException() throws FileManagerException {
            doThrow(new FileManagerException("")).when(fileManager).delete(TASK_BACKUP_FILE_PATH);

            boolean result = taskBackupManager.restore();

            assertFalse(result);
        }


        @Test
        void shouldNotDeleteBackup_whenCopyThrowsException() throws FileManagerException {
            doThrow(new FileManagerException("")).when(fileManager).copy(TASK_BACKUP_FILE_PATH, TASKS_FILE_PATH);

            taskBackupManager.restore();

            verify(fileManager, never()).delete(TASK_BACKUP_FILE_PATH);
        }
    }
}