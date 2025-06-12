package com.ps;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("invalid user and password");
            System.exit(1);
        }
        BasicDataSource basicDataSource= new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);

    }
}