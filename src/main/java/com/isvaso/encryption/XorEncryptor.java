package com.isvaso.encryption;

public class XorEncryptor implements Encryptor {

    private static final String ENCRYPTION_KEY = "GielmeKey";

    @Override
    public String encrypt(String data) {
        return process(data, ENCRYPTION_KEY);
    }

    @Override
    public String decrypt(String data) {
        return process(data, ENCRYPTION_KEY);
    }

    private String process(String data, String key) {
        char[] result = new char[data.length()];
        for (int i = 0; i < data.length(); i++) {
            result[i] = (char) (data.charAt(i) ^ key.charAt(i % key.length()));
        }
        return new String(result);
    }
}
