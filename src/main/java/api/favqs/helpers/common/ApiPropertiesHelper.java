package api.favqs.helpers.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Get api properties from file
 */
public class ApiPropertiesHelper {
    private static final String propertiesPath = "./src/test/resources/api.properties";

    public static String getBaseUri()
    {
        return getProperty("api.favqsHost");
    }
    public static String getApiKey()
    {
        return getProperty("api.key");
    }

    private static String getProperty(String propertyName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Files.newInputStream(Paths.get(propertiesPath));
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
