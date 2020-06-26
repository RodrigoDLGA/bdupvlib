package main.java.recursosupvdb;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class mainClass {
    public static void main( String[] args ) throws IOException, InterruptedException {
        int flag = 0;
        while(flag == 0) {
            try {
                int option = 0;
                Scanner in = new Scanner(System.in);
                while (option < 1 || option > 2) {
                    System.out.println("Elija extensión:\n1).xlsx 2).txt");
                    option = in.nextInt();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                if (option == 1) {
                    //URL res = mainClass.class.getClassLoader().getResource("recursosAcademicos.xlsx");
                    //File filepath = Paths.get(res.toURI()).toFile();
                    FileInputStream filepath = new FileInputStream("./src/resources/recursosAcademicos.xlsx");
                    loadrecursos lr = new loadrecursos();
                    lr.leerxlsx(filepath);
                    flag=1;
                } else {
                    if (option == 2) {
                        //URL res = mainClass.class.getClassLoader().getResource("recursosAcademicos.txt");
                        //File filepath = Paths.get(res.toURI()).toFile();
                        FileInputStream filepath = new FileInputStream("./src/resources/recursosAcademicos.txt");
                        loadrecursos lr = new loadrecursos();
                        lr.leertxt(filepath);
                        flag=1;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("***No se encontró el archivo***");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("***La URL no se pudo convertir a URI***");
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Inserte una opción válida!!!");
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch(InvocationTargetException e){
                System.out.println("***No se aceptan txt con fomato malformado***");
            }
        }
    }
}
