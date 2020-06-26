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
            for (Object key: p.keySet()){
                System.out.println(key + ": " + p.getProperty(key.toString()));
            }
            System.out.println("\nLos datos se cargaron con exito!!!\n");
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
