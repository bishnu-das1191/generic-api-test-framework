package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvUtil {

    private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
    private static Dotenv dotenv;


    static{
        LOGGER.info("Loading... environment variables from .env file");
        dotenv = Dotenv.load();
    }

    private EnvUtil() {
        // private constructor to prevent instantiation
    }

    public static String getEnvValue(String key) {
        LOGGER.info("Fetching value for key: {} from environment variables", key);
        return dotenv.get(key);
    }

}
