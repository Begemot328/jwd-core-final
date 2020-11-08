package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "src/main/resources/application.properties";

        try (FileInputStream stream = new FileInputStream(propertiesFileName)){
            properties.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropery(String key) {
        return properties.getProperty(key);
    }
}
