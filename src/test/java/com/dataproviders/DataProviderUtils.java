package com.dataproviders;

import com.api.request.model.UserCredentials;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.JsonReaderUtil;
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
    public static Iterator<UserCredentials> loginAPIExcelDataProvider() {
        return ExcelReaderUtil.loadTestData("testdata/PhoenixTestData.xlsx","LoginTestData", UserCredentials.class);
    }

}
