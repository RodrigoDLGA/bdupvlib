package main.java.recursosupvdb;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class mainTempDB
{
    public static void main( String[] args ) {
        try(Connection conn = getConnection()){
            System.out.println("\nConexion:" + conn + "\n");
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static Connection getConnection(){
        boolean flag2 = false;
        Connection conn = null;
        int i=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el nombre de la base de datos: ");
        String dbnameinput = scanner.nextLine();
        System.out.println("Escriba el nombre del usuario de la bd: ");
        String dbuserinput = scanner.nextLine();
        while((!flag2 || conn == null) && i<=3){
            i++;
            PGSimpleDataSource source = new PGSimpleDataSource();
            String[] serversNames = {"localhost"};
            source.setServerNames(serversNames);
            source.setDatabaseName(dbnameinput);
            source.setUser(dbuserinput);
            // source.setPassword("xxx");
            source.setLoginTimeout(10);
            //Connection conn = null;

            try {
                return source.getConnection();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Retomando conexion...\n");
                flag2 = true;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }

        }
        if(conn == null){
            System.out.println("No se logró establecer conexion!\nAsegurate de encender los servicios del manejador de bds*");
            System.out.println("\nCreando base de datos temporal...\n");
            ConexionSQLite.connect();
        }

        return  null;
    }
    
}
