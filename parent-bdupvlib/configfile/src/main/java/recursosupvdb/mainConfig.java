package main.java.recursosupvdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class mainConfig {
    public static void main( String[] args ){
        try{
            FileReader r = new FileReader("./configfile/src/main/resources/configDB.properties");
            Properties p = new Properties();
            p.load(r);
            System.out.println(p.getProperty("rutatxt"));
            System.out.println(p.getProperty("rutaxlsx"));
            System.out.println(p.getProperty("rutaconexionSQLite"));
            System.out.println(p.getProperty("nombreconexion"));
            System.out.println(p.getProperty("usuarioconexion"));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
