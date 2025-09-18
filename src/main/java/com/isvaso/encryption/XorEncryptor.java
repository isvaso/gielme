package com.isvaso.encryption;

public class XorEncryptor implements Encryptor {

    private static final String ENCRYPTION_KEY = "GielmeKey";

    @Override
    public String encrypt(String data) {
        return process(data);
    }

    @Override
    public String decrypt(String data) {
        return process(data);
    }

    private String process(String data) {
        if(data == null || data.isEmpty()) {
            return data;
        }
        char[] result = new char[data.length()];
        for (int i = 0; i < data.length(); i++) {
            result[i] = (char) (data.charAt(i) ^ ENCRYPTION_KEY.charAt(i % ENCRYPTION_KEY.length()));
        }
        return new String(result);
    }
}
