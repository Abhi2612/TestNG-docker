package com.abksharm.utils;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() {

        // load default properties
        properties = loadProperties();

        //check for any overrides
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        //print
        logger.info("Test Properties");
        logger.info("_______________");
        for (String key : properties.stringPropertyNames()) {
            logger.info("{}:{}", key, properties.getProperty(key));
        }
        logger.info("_______________");
    }

    public static String get(String key) {
        return properties.getProperty(key);

    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            props.load(stream);
        } catch (IOException e) {
            logger.error("Failed to load properties from default properties file", e);

        }
        return props;
    }


}
