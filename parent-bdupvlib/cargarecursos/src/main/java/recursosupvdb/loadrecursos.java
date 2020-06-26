package main.java.recursosupvdb;

import com.google.gson.*;
import org.apache.commons.collections4.Get;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class loadrecursos {
    public static void leerxlsx(File filepath) throws IOException {
        try{
            FileInputStream fileinput = new FileInputStream(String.valueOf(filepath));
            XSSFWorkbook wk = new XSSFWorkbook(fileinput);
            JsonObject hojasJsonObject = new JsonObject(); // <-

            for(int i = 0; i < wk.getNumberOfSheets(); i++){
                JsonArray ArrayHoja = new JsonArray();
                ArrayList<String> nombresColumnas = new ArrayList<String>();
                Sheet sheet = wk.getSheetAt(i);
                Iterator<Row> iteradorHojas = sheet.iterator();

                //------ATRAVESANDO CADA HOJA DEL ARCHIVO XLSX-----
                while(iteradorHojas.hasNext()){
                    Row filaActual = iteradorHojas.next();
                    JsonObject jsonObject = new JsonObject();
                    //------ATRAVESANDO CADA FILA DE LA HOJA-------
                    if(filaActual.getRowNum()!=0){
                        //------ATRAVESANDO CADA COLUMNA DE LA HOJA-----
                        for(int j = 0; j < nombresColumnas.size(); j++){
                            if (filaActual.getCell(j) != null) { //<- revisa que no este vacías las celdas
                                //divide las celdas en tipos de formato
                                if (filaActual.getCell(j).getCellType() == CellType.STRING) { //<- obtiene el tipo de celda string
                                    jsonObject.addProperty(nombresColumnas.get(j), filaActual.getCell(j).getStringCellValue());
                                } else if (filaActual.getCell(j).getCellType() == CellType.NUMERIC) { //<- obtiene el tipo de celda numérico
                                    jsonObject.addProperty(nombresColumnas.get(j), filaActual.getCell(j).getNumericCellValue());
                                } else if (filaActual.getCell(j).getCellType() == CellType.BOOLEAN) { //<- obtiene el tipo de celda booleano
                                    jsonObject.addProperty(nombresColumnas.get(j), filaActual.getCell(j).getBooleanCellValue());
                                } else if (filaActual.getCell(j).getCellType() == CellType.BLANK) { //<- obtiene el tipo de celda vacía
                                    jsonObject.addProperty(nombresColumnas.get(j), "");
                                }
                            } else {
                                jsonObject.addProperty(nombresColumnas.get(j), "");
                            }
                        }
                        ArrayHoja.add(jsonObject);
                    }else {
                        for (int k = 0; k < filaActual.getPhysicalNumberOfCells(); k++) {
                            nombresColumnas.add(filaActual.getCell(k).getStringCellValue());
                        }
                    }
                }
                hojasJsonObject.add(wk.getSheetName(i),ArrayHoja);
                System.out.println(ArrayHoja);

            }
            System.out.println("\n------------------------------------------------------------\n");
            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            String JsonConFormato = gson2.toJson(hojasJsonObject);
            System.out.println(JsonConFormato);

            try(Writer writer = new FileWriter("RecursosAcademicos.json")){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(hojasJsonObject,writer);
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("¡Archivo JSON creado con exito!");
            }catch (IOException e) {
                System.out.println("***Hubo un error al crear el archivo json***");
                e.printStackTrace();
            }
        }catch(FileNotFoundException e){
            System.out.println("No se encontró el archivo...");
            e.printStackTrace();
        }
    }
    public static void leertxt(File filepath) throws IOException, InvocationTargetException {
        /*
        try (Scanner scanner = new Scanner(String.valueOf(filepath))) {
            scanner.useDelimiter("\\A");
            String all = scanner.next();
            JsonParser parser = new JsonParser();
            JsonObject txtObject = parser.parse(all).getAsJsonObject();
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String JsonConFormato = g.toJson(txtObject);
            System.out.println(JsonConFormato);
            try (Writer writer = new FileWriter("RecursosAcademicos.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(txtObject, writer);
                System.out.println("¡Archivo JSON creado con exito!");
            } catch (IOException e) {
                System.out.println("***Hubo un error al crear el archivo json***");
                e.printStackTrace();
            }
        }
         */
        try{
            FileReader r = new FileReader(String.valueOf(filepath));
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