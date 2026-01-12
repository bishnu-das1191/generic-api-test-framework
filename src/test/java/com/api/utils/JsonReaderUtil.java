package com.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtil {

    // In order to perform Data Driven Testing in this framework,
    // we need to use 5 components from our framework:
    // 1. Test Data component (JSON file with test data)
    // 2. JsonReaderUtil class to read test data from JSON file
    // 3. DataProviderUtils method to fetch the data from JsonReaderUtil
    // 4. POJO class to map the JSON data to Java Object
    // 5. Data Driven Test class.

    public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {

        //loginAPITestData.json ----> src/test/resources/demodata/loginAPITestData.json
        // covert JSON Object into POJO or Java Object using ObjectMapper ----> Deserialization
        // using library Jackson Databind  ---> ObjectMapper class

        // read the JSON file from resources folder
        // we use Thread.currentThread() because when we run tests in parallel,
        // each thread will have its own context class loader
        // this will ensure that the correct class loader is used to load the resource
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName);

        // ObjectMapper is used to convert JSON to Java Object and vice versa
        ObjectMapper objMapper = new ObjectMapper();
        T[] classArray;
        List<T> list = null;
        try {
            classArray = objMapper
                    .readValue(inputStream, clazz); // readValue() method converts JSON to Java Object
            System.out.println("json data in array form: ");
            System.out.println(Arrays.toString(classArray));
            list = List.of(classArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

       return list.iterator();
    }

    // sample data provider method with hardcoded values
    // @DataProvider(name = "loginDataProvider" )
    // public Object[][] loginDataProvider() {
    //     return new Object[][] {
    //             {"user1", "pass1"},
    //             {"user2", "pass2"},
    //             {"user3", "pass3"}
    //     };

}
