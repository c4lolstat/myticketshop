package com.epam.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Farkas on 2017.02.06..
 */
@SpringBootApplication
public class Main {

    public static void main(String args[]){
        SpringApplication.run(Main.class, args);
    }
}
