package com.dataproviders;

import com.api.request.model.CreateBooking;
import com.api.request.model.UserCredentials;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.DataProvider;
import java.util.Iterator;

public class DataProviderUtils {

    // DataProvider needs to return either of these :
    // Object[][]
    // Object[]
    // Iterator<>


    @DataProvider(name = "loginAPIJsonDataProvider", parallel = true)
    public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
        return JsonReaderUtil.loadJSON("testdata/loginAPITestData.json", UserCredentials[].class);
    }


    @DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
    public static Iterator<UserBean> loginAPIExcelDataProvider() {
        return ExcelReaderUtil.loadTestData("testdata/PhoenixTestData.xlsx","LoginTestData", UserBean.class);
    }


    @DataProvider(name = "CreateBookingAPIFakerDataProvider", parallel = true)
    public static Iterator<CreateBooking> createJobFakerDataProvider() {
        // Read faker count from system property during runtime
        // mvn test -Denv=qa -DsuiteXmlFile=testng-datadriven.xml -Dgroups=faker -DfakerCount=20
        String fakerCount = System.getProperty("fakerCount", "5"); // default to 5 if not provided during runtime
        int fakerCountInt = Integer.parseInt(fakerCount);

        // Generate faker data based on fakerCountInt
        return FakerDataGenerator
                .generateFakeCreateBookingData(fakerCountInt);
    }

}
