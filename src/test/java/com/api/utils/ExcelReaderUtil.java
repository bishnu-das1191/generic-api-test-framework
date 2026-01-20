package com.api.utils;

import com.poiji.bind.Poiji;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtil {

    private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
    private ExcelReaderUtil() {
        // private constructor to prevent instantiation
    }

    public static <T> Iterator<T> loadTestData(String filePath, String sheetName, Class<T> clazz) {

        LOGGER.info("Loading test data from Excel file: {} and Sheet: {} ",filePath ,sheetName);
        // Read Excel file using Apache POI OOXML Library

        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath);

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            LOGGER.error("Error reading Excel file: {}", filePath, e);
            e.printStackTrace();
        }
        //focus on the specific sheet
        XSSFSheet sheet =  workbook.getSheet(sheetName);

        // java bean are POJO class with getters and setters
        //Poiji libary to map excel data to Java Bean class
        LOGGER.info("Mapping Excel data to Java Bean: {}",clazz.getName());
        List<T> dataList =  Poiji.fromExcel(sheet, clazz); // UserBean not UserCredentials because Poiji needs bean class with getters and setters
        return dataList.iterator();
    }
}
