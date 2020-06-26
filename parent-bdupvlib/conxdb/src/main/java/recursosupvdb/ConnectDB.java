package main.java.recursosupvdb;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class ConnectDB {
    public static void main( String[] args ){
        boolean flag;
        boolean exito = false;
        //intentar conexion
        try(Connection conn = getConnection()){
            System.out.println("\nConexion:" + conn + "\n");
            flag = true;
        } catch (SQLException ex){
            ex.printStackTrace();
            flag = false;
        }
        //si la conexion es exitosa entonces...
        if(flag){
            String firstkw = null;
            while(Sentencias.flagSentencias || !((Objects.equals(firstkw, "exit"))||(Objects.equals(firstkw, "EXIT"))) || exito==true){
                System.out.println("Escriba una sentencia para ejecutar (escriba exit para salir): \n");
                Scanner scanner = new Scanner(System.in);
                String sentinput = scanner.nextLine(); //<-- leer input de la sentencia
                String[] kws = sentinput.split(" ");
                firstkw = kws[0]; //<-- Primer keyword de la sentencia
                //switch que determina el tipo de sentencia
                try{
                    exito = true;
                    switch (firstkw) {
                        case "select":
                        case "SELECT":
                            Sentencias.selectExecute(sentinput);
                            break;
                        case "insert":
                        case "INSERT":
                        case "update":
                        case "UPDATE":
                        case "delete":
                        case "DELETE":
                        case "drop":
                        case "DROP":
                        case "create":
                        case "CREATE":
                            Sentencias.generalExecute(sentinput);
                            break;
                        case "exit":
                        case "EXIT":
                            exit(0);
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                }catch (IllegalStateException e){
                    System.out.println("Keyword inesperado: " + firstkw + "\n***Intentelo de nuevo***");
                    e.printStackTrace();
                }
            }
        }
    }
    //Establece la conexion
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
            exit(0);
        }

        return  null;
    }
}
