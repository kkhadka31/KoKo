package com.koshish.Security;

import com.koshish.Repository.UserCredentialsDLImpl;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordService {

    UserCredentialsDLImpl userCredentialsDL = new UserCredentialsDLImpl();
    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) {
        String hashedPassword = null;
        int iterations = 10000;
        int keyLength = 256;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
            hashedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return hashedPassword;
    }

    public void storePasswordWithSalt(String username, String password) {
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        userCredentialsDL.storePasswordInDatabase(username, hashedPassword, salt);
    }

}
