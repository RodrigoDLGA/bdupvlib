package main.java.recursosupvdb;
import main.java.recursosupvdb.mainClass;
import main.java.recursosupvdb.mainConfig;
import main.java.recursosupvdb.ConnectDB;
import main.java.recursosupvdb.mainTempDB;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AllMain
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        int option = 0;
        while(option<1||option>4) {
            try {
                System.out.println("Elija un modulo:\n");
                System.out.println("1) cargarecursos\n2) configfile\n3) conxdb\n4) tempdb\n");
                Scanner in = new Scanner(System.in);
                option = in.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Input invalido!!!");
            }
        }
        switch (option) {
            case 1:
                mainClass.main(null);
                break;
            case 2:
                mainConfig.main(null);
                break;
            case 3:
                ConnectDB.main(null);
                break;
            case 4:
                mainTempDB.main(null);
                break;
        }

    }
}
