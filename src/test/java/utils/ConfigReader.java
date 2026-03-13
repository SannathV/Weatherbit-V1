package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {

            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("serenity.properties");

            if (input == null) {
                throw new RuntimeException("serenity.properties file not found in classpath");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Unable to load config file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}