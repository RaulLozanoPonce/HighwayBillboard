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
        
        //Archivo, t, opcion
        
        if(args.length==3){
            //Distancia minima entre carteles
            String example = args[0];
            int t = Integer.parseInt(args[1]);
            double T0, TF;
            switch (args[2]) {
                case "-t":
                    System.out.println("Tabulation ---------------------------------------------------");
                    //Cargamos el fichero con los datos
                    ExampleReaderTabulation et = new ExampleReaderTabulation();
                    et.read(example);
                    int xt[] = et.getX();
                    int revenuet[] = et.getRevenue();
                    //Calculamos el beneficio máximo
                    CalculateRevenueTabulation ct = new CalculateRevenueTabulation();
                    T0 = System.currentTimeMillis();
                    //Lo mostramos
                    System.out.println("El beneficio máximo es: " + ct.maxRevenue(xt, revenuet, t));
                    TF = System.currentTimeMillis();
                    //Mostramos el tiempo de ejecución
                    System.out.println("Tiempo de ejecución: " + (TF - T0)/1000 + " segundos");
                    break;
                case "-m":
                    System.out.println("Memoization --------------------------------------------------");
                    //Cargamos el fichero con los datos
                    ExampleReaderMemoization em = new ExampleReaderMemoization();
                    em.read(example);
                    ArrayList<Integer> xm = em.getX();
                    ArrayList<Integer> revenuem = em.getRevenue();
                    //Calculamos el beneficio máximo
                    CalculateRevenueMemoization cm = new CalculateRevenueMemoization();  
                    //Cargamos un posible mapa existente
                    T0 = System.currentTimeMillis();
                    cm.setMap(new File("map.map"));
                    TF = System.currentTimeMillis();
                    //Mostramos el tiempo de deserialización
                    System.out.println("Tiempo de deserialización del mapa: " + (TF - T0)/1000 + " segundos");
                    T0 = System.currentTimeMillis();
                    //Mostramos el beneficio máximo
                    System.out.println("El beneficio máximo es: " + cm.maxRevenue(xm, revenuem, t));
                    TF = System.currentTimeMillis();
                    //Mostramos el tiempo de ejecución
                    System.out.println("Tiempo de ejecución: " + (TF - T0)/1000 + " segundos");
                    //Guardamos el estado del mapa
                    T0 = System.currentTimeMillis();
                    cm.saveMap(new File("map.map"));
                    TF = System.currentTimeMillis();
                    //Mostramos el tiempo de serialización
                    System.out.println("Tiempo de serialización del mapa: " + (TF - T0)/1000 + " segundos");
                    break;
                default:
                    System.out.println("ERROR: Número incorrecto de argumentos:\n" +
                            "Sintaxis: HighwayBillboard <fichero> <separación> <opción>\n" +
                            "\tfichero: fichero del cual obtener la muestra\n" +
                            "\tseparación: separación mínima entre carteles colocados en la carretera\n" +
                            "\topción:\n" +
                            "\t\t-m : usar memoization\n" +
                            "\t\t-t : usar tabulation");
                    break;
            }
        }else{
            System.out.println("ERROR: Número incorrecto de argumentos:\n" +
                            "Sintaxis: HighwayBillboard <fichero> <separación> <opción>\n" +
                            "\tfichero: fichero del cual obtener la muestra\n" +
                            "\tseparación: separación mínima entre carteles colocados en la carretera\n" +
                            "\topción:\n" +
                            "\t\t-m : usar memoization\n" +
                            "\t\t-t : usar tabulation");
        }
    }
}
