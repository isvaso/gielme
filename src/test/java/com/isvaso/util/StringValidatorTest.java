package com.isvaso.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @Nested
    class IsEmptyOrNull {

        @Test
        void shouldReturnTrue_whenStringIsEmpty() {
            String string = "";

            assertTrue(StringValidator.isEmptyOrNull(string));
        }

        @Test
        void shouldReturnTrue_whenStringIsNull() {
            String string = null;

            assertTrue(StringValidator.isEmptyOrNull(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "Test",
                "!",
                "\n",
                " ",
                "     "
        })
        void shouldReturnFalse_whenStringIsNotEmpty() {
            String string = "Test";

            assertFalse(StringValidator.isEmptyOrNull(string));
        }
    }
}