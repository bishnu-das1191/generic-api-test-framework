package com.dataproviders;

import com.api.request.model.CreateBooking;
import com.api.request.model.UserCredentials;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.UserBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import java.util.Iterator;

public class DataProviderUtils {

    // DataProvider needs to return either of these :
    // Object[][]
    // Object[]
    // Iterator<>

    private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);

    @DataProvider(name = "loginAPIJsonDataProvider", parallel = true)
    public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
        LOGGER.info("Providing login API test data from JSON file testdata/loginAPITestData.json.");
        return JsonReaderUtil.loadJSON("testdata/loginAPITestData.json", UserCredentials[].class);
    }


    @DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
    public static Iterator<UserBean> loginAPIExcelDataProvider() {
        LOGGER.info("Providing login API test data from Excel file testdata/PhoenixTestData.xlsx, sheet: LoginTestData.");
        return ExcelReaderUtil.loadTestData("testdata/PhoenixTestData.xlsx","LoginTestData", UserBean.class);
    }


    @DataProvider(name = "CreateBookingAPIFakerDataProvider", parallel = true)
    public static Iterator<CreateBooking> createJobFakerDataProvider() {
        // Read faker count from system property during runtime
        // mvn test -Denv=qa -DsuiteXmlFile=testng-datadriven.xml -Dgroups=faker -DfakerCount=20
        String fakerCount = System.getProperty("fakerCount", "5"); // default to 5 if not provided during runtime
        int fakerCountInt = Integer.parseInt(fakerCount);
        LOGGER.info("Generating {} faker data entries for Create Booking API test.", fakerCountInt);
        // Generate faker data based on fakerCountInt
        return FakerDataGenerator
                .generateFakeCreateBookingData(fakerCountInt);
    }

}
