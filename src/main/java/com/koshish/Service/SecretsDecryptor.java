package com.koshish.Service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class SecretsDecryptor {

    public String decrypt(String secretKey, String encryptedPassword, String encryptAlgo) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(secretKey); // Set your secret key here
        decryptor.setAlgorithm(encryptAlgo);
        return decryptor.decrypt(encryptedPassword);
    }

}
