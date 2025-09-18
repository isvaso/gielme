package com.isvaso.encryption;

public interface Encryptor {

    String encrypt(String data);

    String decrypt(String data);
}
