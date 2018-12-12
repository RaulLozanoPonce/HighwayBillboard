package highwaybillboard.controller;

import highwaybillboard.data.ExampleReaderMemoization;
import highwaybillboard.data.ExampleReaderTabulation;
import highwaybillboard.model.CalculateRevenueMemoization;
import highwaybillboard.model.CalculateRevenueTabulation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HighwayBillboard {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        if(args.length==1){
            //Distancia minima entre carteles
            int t = 10;
            double TInicio, TFin;
            switch (args[0]) {
                case "-t":
                    System.out.println("Tabulation ---------------------------------------------------");
                    //Cargamos el fichero con los datos
                    ExampleReaderTabulation et = new ExampleReaderTabulation();
                    et.read("ejemplo.txt");
                    int xt[] = et.getX();
                    int revenuet[] = et.getRevenue();
                    //Calculamos el beneficio máximo
                    CalculateRevenueTabulation ct = new CalculateRevenueTabulation();
                    TInicio = System.currentTimeMillis();
                    //Lo mostramos
                    System.out.println("El beneficio máximo es: " + ct.maxRevenue(xt, revenuet, t));
                    TFin = System.currentTimeMillis();
                    //Mostramos el tiempo de ejecución
                    System.out.println("Tiempo de ejecución: " + (TFin - TInicio)/1000 + " microsegundos");
                    break;
                case "-m":
                    System.out.println("Memoization --------------------------------------------------");
                    //Cargamos el fichero con los datos
                    ExampleReaderMemoization em = new ExampleReaderMemoization();
                    em.read("ejemplo.txt");
                    ArrayList<Integer> xm = em.getX();
                    ArrayList<Integer> revenuem = em.getRevenue();
                    //Calculamos el beneficio máximo
                    CalculateRevenueMemoization cm = new CalculateRevenueMemoization();  
                    //Cargamos un posible mapa existente
                    cm.setMap(new File("map.map"));
                    TInicio = System.currentTimeMillis();
                    //Mostramos el beneficio máximo
                    System.out.println("El beneficio máximo es: " + cm.maxRevenue(xm, revenuem, t));
                    TFin = System.currentTimeMillis();
                    //Mostramos el tiempo de ejecución
                    System.out.println("Tiempo de ejecución: " + (TFin - TInicio)/1000 + " microsegundos");
                    //Guardamos el estado del mapa
                    cm.saveMap(new File("map.map"));
                    break;
                default:
                    System.out.println("Número incorrecto de argumentos" + '\n' + 
                        "Sintaxis: HighwayBillboard <opción>" + '\n' + '\t' + 
                        "-m : usar memoization" + '\n' + '\t' + 
                        "-t : usar tabulation");
                    break;
            }
        }else{
            System.out.println("Número incorrecto de argumentos" + '\n' + 
                    "Sintaxis: HighwayBillboard <opción>" + '\n' + '\t' + 
                    "-m : usar memoization" + '\n' + '\t' + 
                    "-t : usar tabulation");
        }
    }
}
