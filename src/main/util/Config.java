package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try {
            System.out.println("Working directory: " + System.getProperty("user.dir"));
            props.load(new FileInputStream(".env"));
            System.out.println(".env loaded successfully");
        } catch (IOException e) {
            System.out.println("Could not load .env file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}