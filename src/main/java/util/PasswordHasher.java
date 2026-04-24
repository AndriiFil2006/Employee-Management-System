package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher 
{
    public static String hashPassword(String password) 
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b: hashBytes)
            {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static boolean verifyPassword(String password, String hash)
    {
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(hash);
    }
}