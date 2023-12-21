package com.cac.tpfinal_cac;


public class DatabaseConnector {
    private String url;
    private String driver;
    private String user;
    private String pass;

    public DatabaseConnector() {
        this.url = "jdbc:mysql://localhost:3306/db_oradores";
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.user = "root";
        this.pass = "";
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
}
