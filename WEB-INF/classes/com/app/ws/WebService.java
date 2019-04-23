
package com.app.ws; //Esta es la estructura de paquete creada
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
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     private static LocalDateTime now = LocalDateTime.now();
    private static String fileName = "../webapps/RESTful/WEB-INF/registroUsers.txt";
    private static String message = "";
    public class Objeto{
      public String rebel;
      public String planet;
      public String date;

      public Objeto(String rebel, String planet, String date) {
        this.rebel = rebel;
        this.planet = planet;
        this.date = date;
      }
       public Objeto() {
        this.rebel = "";
        this.planet = "";
        this.date = "";
      }
    }
		@GET //Indicamos que este método se ejecutará al recibir una petición por get
		@Produces({"text/plain"}) //Indicamos que el tipo de salida es texto plano
		public String registro(@PathParam("rebel") String rebel,@PathParam("planet") String planet)
		{
			if(WriteFile(fileName,rebel,planet)){
        return "Has been registered correctly : Rebel " + rebel + " on " + planet + " at " + dtf.format(now);
      }else{
        return "Rebel has not been registered correctly : "+message;
      }
		}
		public boolean CreateFile(String fileName){
				try{
				File myObj = new File(fileName);
				  if (myObj.createNewFile()) {
						System.out.println("File created: " + myObj.getName());
					}
          return true;
				}catch(IOException e){
					message += e.getMessage();
					return false;
				}
		}
		public boolean WriteFile(String fileName, String rebel, String planet){
        try{
            if(CreateFile(fileName)){
              Writer myWriter = new BufferedWriter(new FileWriter(fileName, true));
              myWriter.append("Rebel " + rebel + " on " + planet + " at " + dtf.format(now));
              myWriter.write(System.getProperty("line.separator"));
              myWriter.close();
              //Boolean resultCheck = CheckWrite(readFile(fileName));
              return true;
            }
            return false;
        }catch(IOException e){
          message += e.getMessage();
          return false;
        }
		}
    // EXTRA : Esta parte del código era para leer, comprovar y obtener los registros del archivo
    public ArrayList obtenerObjeto(String content){
        ArrayList<Objeto> todosVisitantes = new ArrayList<Objeto>();
        ArrayList<String> lines = new ArrayList<String>();
        String[] tempLine = content.split("\n");
        for (int i = 0; i < tempLine.length; i++) {
          lines.add(tempLine[i]);
        }
        ArrayList<String> camp = new ArrayList<String>();
        Objeto persona = new Objeto();
        for(int c = 0; c < lines.size() ; c++){
          String line = lines.get(c).toString();
          int iniPos = 6;
          int onPos = line.indexOf(" ",iniPos+1);
          int atPos = line.indexOf(" ",onPos+4);
          persona.rebel = line.substring(iniPos,onPos);
          persona.planet = line.substring(onPos+4,atPos);
          persona.date = line.substring(atPos+4);
          todosVisitantes.add(persona);
          //See all rebels
          /*
          System.out.println("Rebel : " + persona.rebel);
          System.out.println("Planet : "+ persona.planet);
          System.out.println("Date : "+  persona.date );
          */
        }
        return todosVisitantes;
    }
    public boolean CheckWrite(String content){
      ArrayList todosVisitantes = obtenerObjeto(content);
      //Añadir cualquier tipo de comprobacion fijandote en el arraylist anterior
      return true;
    }
    public String readFile(String fileName)
    {
        String content = null;
        File file = new File(fileName); // For example, foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            System.out.println(content);
            reader.close();
            return content;
        } catch (IOException e) {
            message += e.getMessage();
            return e.getMessage();
        } 
    }
}