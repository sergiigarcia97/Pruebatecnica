package com.app.ws;

import javax.ws.rs.*; 
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.Writer;

public class Logger 
{
    public String status;
    public String date;
    public String message;
    public String fileName;

    public Logger(String status,String message,String fileName) {
        this.status = status;
        this.date = DATE_NOW;
        this.message = message;
        this.fileName = fileName;
    }
    public Logger() {
        this.status = "";
        this.date = DATE_NOW;
        this.message = "";
        this.fileName = "";
    }
    public Logger(Logger obj) {
        this.status = obj.status;
        this.date = obj.date;
        this.message = obj.message;
        this.fileName = obj.fileName;
    }
    public void info(String message){
        this.status = "INFO";
        this.date = DATE_NOW;
        this.fileName = routeLogs;
        this.message = message;
        writeLog(this);
    }
    public void error(String message){
        this.status = "ERROR";
        this.date = DATE_NOW;
        this.fileName = routeLogs;
        this.message = message;
        logObject = this;
        writeLog(this);
    }
    public void success(String message){
        this.status = "SUCCESS";
        this.date = DATE_NOW;
        this.fileName = routeLogs;
        this.message = message;
        logObject = this;
        writeLog(this);
    }
    public void rebel(Rebel rebel){
        this.status = "SUCCESS";
        this.date = DATE_NOW;
        this.fileName = routeRebels;
        this.message = message;
        logObject = this;
        writeFile(rebel);
    }
    public String getMessage(){
        return this.message;
    }
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String DATE_NOW = DATE_FORMAT.format(LocalDateTime.now());
    private static Logger logObject = new Logger();
    private static String routeRebels = "../webapps/RESTful/WEB-INF/registerRebels.txt";
    private static String routeLogs = "../webapps/RESTful/WEB-INF/logRebels.txt";
    public static boolean createFile(){
        try{
            File myObj = new File(logObject.fileName);
            if (myObj.createNewFile()) {
                logObject.info("File created: " + myObj.getName());
            }
            return true;
        }catch(IOException e){
            logObject.error(e.getMessage());
            return false;
        }
    }
    public static boolean writeFile(Rebel rebelRegistered){
        try{
            if(createFile()){
                Writer myWriter = new BufferedWriter(new FileWriter(logObject.fileName, true));
                myWriter.append("Rebel " + rebelRegistered.name + " on " + rebelRegistered.planet + " at " + rebelRegistered.date);
                myWriter.write(System.getProperty("line.separator"));
                myWriter.close();
                return true;
            }
            return false;
        }catch(IOException e){
            logObject.error(e.getMessage());
            return false;
        }
    }
    public static boolean writeLog(Logger log){
        try{
            if(createFile()){
                Writer myWriter = new BufferedWriter(new FileWriter(log.fileName, true));
                myWriter.append(log.date + " - " + log.status + " = " + log.message);
                myWriter.write(System.getProperty("line.separator"));
                myWriter.close();
                return true;
            }
            return false;
        }catch(IOException e){
            log.error(e.getMessage());
            return false;
        }
    }
}