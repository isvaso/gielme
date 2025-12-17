package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.domain.model.DataVersion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DataVersionSerializerTest {

    private final DataVersionSerializer dataVersionSerializer = new DataVersionSerializer();

    @Nested
    class Serialize {

        @Test
        void shouldThrowSerializerException_whenDataVersionIsNull() {
            DataVersion dataVersion = null;

            assertThrows(SerializerException.class, () -> dataVersionSerializer.serialize(dataVersion));
        }

        @Test
        void shouldThrowSerializerException_whenVersionIsNegative() {
            DataVersion dataVersion = new DataVersion(-1);

            assertThrows(SerializerException.class, () -> dataVersionSerializer.serialize(dataVersion));
        }

        @Test
        void shouldSerialize_whenZeroVersion() throws SerializerException {
            DataVersion dataVersion = new DataVersion(0);
            String expectedVersion = "0";

            String actualVersion = dataVersionSerializer.serialize(dataVersion);

            assertEquals(expectedVersion, actualVersion);
        }

        @Test
        void shouldSerialize_whenPositiveVersion() throws SerializerException {
            DataVersion dataVersion = new DataVersion(1);
            String expectedVersion = "1";

            String actualVersion = dataVersionSerializer.serialize(dataVersion);

            assertEquals(expectedVersion, actualVersion);
        }
    }

    @Nested
    class Deserialize {

        @Test
        void shouldThrowSerializerException_whenVersionIsNull() {
            String dataVersion = null;

            assertThrows(SerializerException.class, () -> dataVersionSerializer.deserialize(dataVersion));
        }

        @Test
        void shouldThrowSerializerException_whenVersionIsEmpty() {
            String dataVersion = "";

            assertThrows(SerializerException.class, () -> dataVersionSerializer.deserialize(dataVersion));
        }

        @Test
        void shouldThrowSerializerException_whenVersionIsBlank() {
            String dataVersion = " ";

            assertThrows(SerializerException.class, () -> dataVersionSerializer.deserialize(dataVersion));
        }

        @Test
        void shouldThrowSerializerException_whenNotANumberVersion() {
            String dataVersion = "Version";

            assertThrows(SerializerException.class, () -> dataVersionSerializer.deserialize(dataVersion));
        }

        @Test
        void shouldThrowSerializerException_whenNegativeVersion() {
            String dataVersion = "-1";

            assertThrows(SerializerException.class, () -> dataVersionSerializer.deserialize(dataVersion));
        }

        @Test
        void shouldDeserialize_whenZeroVersion() throws SerializerException {
            String dataVersion = "0";
            int expectedVersion = 0;

            Optional<DataVersion> dataVersionOptional = dataVersionSerializer.deserialize(dataVersion);

            assertTrue(dataVersionOptional.isPresent());
            assertEquals(expectedVersion, dataVersionOptional.get().getVersion());
        }

        @Test
        void shouldDeserialize_whenPositiveVersion() throws SerializerException {
            String dataVersion = "1";
            int expectedVersion = 1;

            Optional<DataVersion> dataVersionOptional = dataVersionSerializer.deserialize(dataVersion);

            assertTrue(dataVersionOptional.isPresent());
            assertEquals(expectedVersion, dataVersionOptional.get().getVersion());
        }
    }
}