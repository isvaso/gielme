package com.isvaso.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @Nested
    class IsEmpty {

        @Test
        void shouldReturnTrue_whenStringIsEmpty() {
            String string = "";

            assertTrue(StringValidator.isEmpty(string));
        }

        @Test
        void shouldReturnFalse_whenStringIsNull() {
            String string = null;

            assertFalse(StringValidator.isEmpty(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "Test",
                "!",
                "^ ^",
                "$%~"
        })
        void shouldReturnFalse_whenStringIsNotEmpty(String string) {
            assertFalse(StringValidator.isEmptyOrNull(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "\n",
                " ",
                "     "
        })
        void shouldReturnFalse_whenStringIsBlank(String string) {
            assertFalse(StringValidator.isEmptyOrNull(string));
        }
    }

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
                "^ ^",
                "$%~"
        })
        void shouldReturnFalse_whenStringIsNotEmpty(String string) {
            assertFalse(StringValidator.isEmptyOrNull(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "\n",
                " ",
                "     "
        })
        void shouldReturnFalse_whenStringIsBlank(String string) {
            assertFalse(StringValidator.isEmptyOrNull(string));
        }
    }

    @Nested
    class IsBlankOrNull {

        @Test
        void shouldReturnTrue_whenStringIsEmpty() {
            String string = "";

            assertTrue(StringValidator.isBlankOrNull(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "\n",
                " ",
                "     "
        })
        void shouldReturnTrue_whenStringIsBlank(String string) {
            assertTrue(StringValidator.isBlankOrNull(string));
        }

        @Test
        void shouldReturnTrue_whenStringIsNull() {
            String string = null;

            assertTrue(StringValidator.isBlankOrNull(string));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "Test",
                "!",
                "^ ^",
                "$%~"
        })
        void shouldReturnFalse_whenStringIsNotEmpty(String string) {
            assertFalse(StringValidator.isBlankOrNull(string));
        }
    }
}