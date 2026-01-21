package com.api.retry;

import com.api.utils.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
	private int currentAttempt = 1;
	private static final int MAX_NUMBER_OF_ATTEMPTS= Integer.parseInt(ConfigManager.getProperty("MAX_NUMBER_OF_ATTEMPTS"));
	
	@Override
	public boolean retry(ITestResult result) {
		LOGGER.info("Retrying test: {} - Attempt {}/{}", result.getName(), currentAttempt, MAX_NUMBER_OF_ATTEMPTS);
		if (currentAttempt < MAX_NUMBER_OF_ATTEMPTS) {
			LOGGER.warn("Test {} failed on attempt {}/{}. Retrying...", result.getName(), currentAttempt, MAX_NUMBER_OF_ATTEMPTS);
			currentAttempt++;
			return true;
		}
		return false;
	}
}
