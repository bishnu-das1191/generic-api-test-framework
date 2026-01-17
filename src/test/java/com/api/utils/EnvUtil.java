package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

    private static Dotenv dotenv;

    static{
        dotenv = Dotenv.load();
    }

    private EnvUtil() {
        // private constructor to prevent instantiation
    }

    public static String getEnvValue(String key) {
        return dotenv.get(key);
    }

}
