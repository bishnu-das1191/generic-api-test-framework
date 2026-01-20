package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriterUtil {

    private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

    private AllureEnvironmentWriterUtil() {
        // private constructor to prevent instantiation
    }
    public static void createEnvironmentPropertiesFile() {

        // in order to store environment related information in allure report
        // we need to create a file named "environment.properties"

        String folderPath = "target/allure-results";
        File file = new File(folderPath);
        file.mkdirs(); // create directories if not exist

        Properties prop = new Properties();
        prop.setProperty("Project_Name", "Generic API Automation Framework");
        prop.setProperty("Environment", ConfigManager.env.toUpperCase());
        prop.setProperty("Base_URI", ConfigManager.getProperty("BASE_URI"));
        prop.setProperty(System.getProperty("os.name"), System.getProperty("os.version"));
        prop.setProperty("Java_Version", System.getProperty("java.version"));

        // FileWriter will create the file if it does not exist
        FileWriter writer = null;
        try {
            writer = new FileWriter(folderPath+"/environment.properties");
            prop.store(writer, "Environment Details");
            LOGGER.info("Allure environment.properties file created successfully at : {} ",folderPath);
        } catch (IOException e) {
            LOGGER.error("Failed to create allure environment.properties file at : {} ",folderPath);
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
