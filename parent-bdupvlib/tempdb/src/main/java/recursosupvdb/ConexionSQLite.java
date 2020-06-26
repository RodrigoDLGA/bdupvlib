package main.java.recursosupvdb;

import java.sql.*;
import java.util.Scanner;

public class ConexionSQLite {

    public static String crearTempDB(String nombreArchivo){
        String url = "jdbc:sqlite:./tempdb/src/main/resources/"+nombreArchivo;
        System.out.println(url);
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Controlador en uso: " + meta.getDriverName());
                System.out.println("Una nueva base de datos se ha creado!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return url;
    }

    public static void connect(){
        Connection conn = null;
        try{
            String urlstring = crearTempDB("temp.db");
            conn = DriverManager.getConnection(urlstring);
            System.out.println("\nLa conexión a SQLite se ha realizado con exito!!\n");
            boolean flag=false;
            String input = null;
            while(!flag ||input.equals("exit")||input.equals("EXIT")) {
                try {
                    System.out.println("Escriba una sentencia (escriba exit para salir):\n");
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    if(input.equals("exit")||input.equals("EXIT")){
                        break;
                    }
                    Statement stmt = conn.createStatement();
                    stmt.execute(input);
                    flag = true;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
