package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/java/config/config.properties")) {
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }
    
    // Browser with Jenkins override
    public static String getBrowser() {
        return System.getProperty("browser", props.getProperty("browser"));
    }

    // Timeout with Jenkins override
    public static int getTimeout() {
        return Integer.parseInt(System.getProperty("timeout", props.getProperty("timeout")));
    }
    
    // Env + Base URL
    public static String getEnv() {
        return System.getProperty("env", props.getProperty("env"));
    }

    public static String get(String key) {
    	String sysValue = System.getProperty(key);
        if (sysValue != null && !sysValue.isEmpty()) {
            return sysValue;
        }
        return props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static String getBaseUrl() {
        String env = System.getProperty("env", props.getProperty("env")); // default from properties
        String url = props.getProperty(env + ".url");
        if (url == null) {
            throw new RuntimeException("URL for environment '" + env + "' not found in config.properties");
        }
        return url;
    }
}