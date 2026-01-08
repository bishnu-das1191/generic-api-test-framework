package com.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtil {

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

        ObjectMapper objMapper = new ObjectMapper();
        T[] classArray;
        List<T> list = null;
        try {
            classArray = objMapper
                    .readValue(inputStream, clazz);
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
