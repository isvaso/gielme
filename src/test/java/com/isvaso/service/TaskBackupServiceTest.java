package com.isvaso.service;

import com.isvaso.storage.BackupStorage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskBackupServiceTest {

    @Mock
    private BackupStorage backupStorage;

    @InjectMocks
    private TaskBackupService taskBackupService;

    @Nested
    class Backup {

        @Test
        void shouldCallRepository_whenInvoked() {
            taskBackupService.backup();

            verify(backupStorage).backup();
        }

        @Test
        void shouldReturnTrue_whenBackupSuccess() {
            when(backupStorage.backup()).thenReturn(true);

            boolean result = taskBackupService.backup();

            assertTrue(result);
        }

        @Test
        void shouldReturnTrue_whenBackupUnsuccessful() {
            when(backupStorage.backup()).thenReturn(false);

            boolean result = taskBackupService.backup();

            assertFalse(result);
        }
    }

    @Nested
    class Restore {

        @Test
        void shouldCallRepository_whenInvoked() {
            taskBackupService.restore();

            verify(backupStorage).restore();
        }

        @Test
        void shouldReturnTrue_whenRestoreSuccess() {
            when(backupStorage.restore()).thenReturn(true);

            boolean result = taskBackupService.restore();

            assertTrue(result);
        }

        @Test
        void shouldReturnTrue_whenBRrestoreUnsuccessful() {
            when(backupStorage.restore()).thenReturn(false);

            boolean result = taskBackupService.restore();

            assertFalse(result);
        }
    }
}