package com.isvaso.service;

import com.isvaso.exception.RepositoryException;
import com.isvaso.model.DataVersion;
import com.isvaso.repository.DataVersionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class DataVersionServiceTest {

    @Mock
    private DataVersionRepository dataVersionRepository;

    @InjectMocks
    private DataVersionService dataVersionService;

    @Nested
    class Get {

        @Test
        void shouldCallRepository_whenInvoked() {
            when(dataVersionRepository.get()).thenReturn(Optional.empty());

            dataVersionService.get();

            verify(dataVersionRepository).get();
        }
    }

    @Nested
    class Update {

        @Test
        void shouldReturnOptionalEmpty_whenDataVersionIsNull() throws RepositoryException {
            DataVersion dataVersion = null;

            Optional<DataVersion> actualDataVersion = dataVersionService.update(dataVersion);

            assertTrue(actualDataVersion.isEmpty());
        }

        @Test
        void shouldCallRepository_whenInvoked() {
            DataVersion dataVersion = new DataVersion(1);
            when(dataVersionRepository.update(dataVersion)).thenReturn(Optional.of(dataVersion));

            dataVersionService.update(dataVersion);

            verify(dataVersionRepository).update(dataVersion);
        }

    }

}