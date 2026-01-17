package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {
    public static void main(String[] args) {
        System.out.println(EnvUtil.getEnvValue("LOGIN_API_USERNAME"));
        System.out.println(EnvUtil.getEnvValue("LOGIN_API_PASSWORD"));
    }
}
