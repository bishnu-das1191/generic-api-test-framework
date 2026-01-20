package com.listeners;

import com.api.utils.ConfigManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class APIRetryAnalyzer implements IRetryAnalyzer {

	private int currentAttempt = 1;
	private static final int MAX_NUMBER_OF_ATTEMPTS= Integer.parseInt(ConfigManager.getProperty("MAX_NUMBER_OF_ATTEMPTS"));
	
	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt < MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}
}
