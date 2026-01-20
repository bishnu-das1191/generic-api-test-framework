package com.listeners;

import com.api.utils.AllureEnvironmentWriterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class APITestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(APITestListener.class);
    public void onTestStart(ITestResult result) {

        LOGGER.info("****************************************************************************************");
        LOGGER.info("******** Test Started, class : {}, method : {} *******", result.getTestClass().getName(), result.getName());
        LOGGER.info("******** Description : {} ****************************", result.getMethod().getDescription());
        LOGGER.info("******** Groups : {} *********************************", String.join(", ", result.getMethod().getGroups()));
        LOGGER.info("****************************************************************************************");
    }

    public void onTestSuccess(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        LOGGER.info("****************************************************************************************");
        LOGGER.info("******** Test Execution Time: {} ms ********************************************", (endTime - startTime));
        LOGGER.info("******** Test PASSED !!!, class : {}, method : {} *******", result.getTestClass().getName(), result.getName());
        LOGGER.info("****************************************************************************************");
    }

    public void onTestFailure(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        LOGGER.error("****************************************************************************************");
        LOGGER.error("******** Test Execution Time: {} ms ********************************************", (endTime - startTime));
        LOGGER.error("******** Test FAILED !!!, class : {}, method : {} *******", result.getTestClass().getName(), result.getName());
        LOGGER.error("******** Exception: {} ********************************", result.getThrowable());
        LOGGER.error("****************************************************************************************");
    }

    public void onTestSkipped(ITestResult result) {
        LOGGER.info("****************************************************************************************");
        LOGGER.error("******** Test SKIPPED !!!, class : {}, method : {} *******", result.getTestClass().getName(), result.getName());
        LOGGER.info("****************************************************************************************");
    }

    public void onStart(ITestContext context) {
        LOGGER.info("===============================================================================================");
        LOGGER.info("========== TEST SUITE STARTED: {} ======================================", context.getName());
        AllureEnvironmentWriterUtil.createEnvironmentPropertiesFile();
    }

    public void onFinish(ITestContext context) {
        LOGGER.info("========== TEST SUITE FINISHED: {} ======================================", context.getName());
        LOGGER.info("===============================================================================================");
    }


}
