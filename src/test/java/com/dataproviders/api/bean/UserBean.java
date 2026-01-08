package com.dataproviders.api.bean;

//import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class UserBean {

    //if we are changing the column names in CSV file then
    // we need to use @CsvBindByName annotation to map the column names to the fields

    //@ExcelCellName("username") is from Poiji library to map excel column to field
    //moreover Poiji is case-sensitive for column names
    //moreover Poiji requires default constructor, getters and setters to work properly

    //@CsvBindByName(column = "username")
    @ExcelCellName("username")
    private String username;

    //@CsvBindByName(column = "password")
    @ExcelCellName("password")
    private String password;

    public UserBean() {
    }

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
