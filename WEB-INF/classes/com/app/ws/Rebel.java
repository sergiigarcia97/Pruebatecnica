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

public class Rebel 
{
  public  String name;
  public  String planet;
  public  String date;
  public  String message = "";
  public Rebel(String name, String planet, String date,String message) {
    this.name = name;
    this.planet = planet;
    this.date = date;
    this.message = message;
  }
  public Rebel(String name, String planet) {
    this.name = name;
    this.planet = planet;
    this.date = DATE_NOW;
    this.message = "";
  }
   public Rebel() {
    this.name = "";
    this.planet = "";
    this.date = DATE_NOW;
    this.message = "";
  }
  public Rebel(Rebel obj) {
    this.name = obj.name;
    this.planet = obj.planet;
    this.date = obj.date;
    this.message = obj.message;
  }
  public void setMessage(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  private static final String DATE_NOW = DATE_FORMAT.format(LocalDateTime.now());
  private static Logger log = new Logger();
  public static Boolean register(Rebel rebelSend)
  {
    try{
        if((rebelSend.name!= null && !rebelSend.name.trim().isEmpty()) && (rebelSend.planet!= null && !rebelSend.planet.trim().isEmpty())){
            log.rebel(rebelSend);
          return true;
        }else{
            log.error("Empty Parameters | Name: "+ rebelSend.name +" , Planet:"+rebelSend.planet);
          return false;
        }
    }catch(NullPointerException e){
        log.error(e.getMessage());
      return false;
    }
  }
}