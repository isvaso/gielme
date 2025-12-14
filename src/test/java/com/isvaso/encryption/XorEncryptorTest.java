package com.isvaso.encryption;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class XorEncryptorTest {

    private final XorEncryptor xorEncryptor = new XorEncryptor();

    @Nested
    class Encrypt {

        @ParameterizedTest
        @ValueSource(strings = {
                "A",
                "a",
                "z",
                "Z"
        })
        void shouldEncryptData_whenEncryptLetter(String originalString) {
            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "!",
                "{",
                ":",
                ",",
                "@"
        })
        void shouldEncryptData_whenEncryptSign(String originalString) {
            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @Test
        void shouldEncryptData_whenEncryptString() {
            String originalString = "Test test";

            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @Test
        void shouldEncryptData_whenEncryptLongString() {
            String originalString = "The text is so long that there wasn't enough space for it. Just kidding, of course. This text is so long that there was enough space for it.";

            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @Test
        void shouldEncryptData_whenEncryptUnicodeString() {
            String originalString = "Тест тестович";

            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @Test
        void shouldEncryptData_whenEncryptLongUnicodeString() {
            String originalString = "Текст такой длинной длины, что для него не хватило длины. Шутка, конечно. Это текст такой длинной длины, что для него хватило длины.";

            String actualString = xorEncryptor.encrypt(originalString);

            assertNotEquals(originalString, actualString);
        }

        @Test
        void shouldReturnNull_whenStringIsNull() {
            String originalString = null;

            String actualString = xorEncryptor.encrypt(originalString);

            assertNull(actualString);
        }

        @Test
        void shouldReturnEmpty_whenStringIsEmpty() {
            String originalString = "";

            String actualString = xorEncryptor.encrypt(originalString);

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldBeDeterministic_whenEncrypt() {
            String originalString = "Test test";

            String actualString = xorEncryptor.encrypt(originalString);

            for (int i = 1; i < 100; i++) {
                assertEquals(actualString, xorEncryptor.encrypt(originalString));
            }
        }
    }

    @Nested
    class EncryptDecrypt {

        @ParameterizedTest
        @ValueSource(strings = {
                "A",
                "a",
                "z",
                "Z"
        })
        void shouldDecryptData_whenDecryptLetter(String originalString) {
            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "!",
                "{",
                ":",
                ",",
                "@"
        })
        void shouldDecryptData_whenDecryptSign(String originalString) {
            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldDecryptData_whenDecryptString() {
            String originalString = "Test test";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldDecryptData_whenDecryptLongString() {
            String originalString = "The text is so long that there wasn't enough space for it. Just kidding, of course. This text is so long that there was enough space for it.";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldDecryptData_whenDecryptUnicodeString() {
            String originalString = "Тест тестович";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldDecryptData_whenDecryptLongUnicodeString() {
            String originalString = "Текст такой длинной длины, что для него не хватило длины. Шутка, конечно. Это текст такой длинной длины, что для него хватило длины.";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldReturnNull_whenStringIsNull() {
            String originalString = null;

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertNull(actualString);
        }

        @Test
        void shouldReturnEmpty_whenStringIsEmpty() {
            String originalString = "";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            assertEquals(originalString, actualString);
        }

        @Test
        void shouldBeDeterministic_whenDecrypt() {
            String originalString = "Test test";

            String actualString = xorEncryptor.decrypt(xorEncryptor.encrypt(originalString));

            for (int i = 1; i < 100; i++) {
                assertEquals(actualString, xorEncryptor.decrypt(xorEncryptor.encrypt(originalString)));
            }
        }
    }
}