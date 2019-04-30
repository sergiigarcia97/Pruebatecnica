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

@Path("registrar/{rebel}/{planet}") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class WebService 
{
  @GET //Indicamos que este método se ejecutará al recibir una petición por get
  @Produces({"text/plain"}) //Indicamos que el tipo de salida es texto plano
  public static String WebService(@PathParam("rebel") String name,@PathParam("planet") String planet){
   Rebel rebel = new Rebel(name,planet);
   Logger log = new Logger();
    try{ 
        if(rebel.register(rebel)){
          log.success("Has been registered correctly : Rebel " + rebel.name + " on " + rebel.planet);
          return "Has been registered correctly : Rebel " + rebel.name + " on " + rebel.planet + " at " + rebel.date;
        }else{
          log.error("Rebel has not been registered correctly");
          return "Rebel has not been registered correctly : " + log.getMessage();
        }
    }catch(NullPointerException e){
      log.error(e.getMessage());
      return "Rebel has not been registered correctly : " + e.getMessage();
    }
  }
}