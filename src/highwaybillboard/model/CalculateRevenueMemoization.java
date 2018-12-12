package highwaybillboard.model;

import highwaybillboard.data.MapNativeFileReader;
import highwaybillboard.data.MapNativeFileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CalculateRevenueMemoization {
    
    private Map<ArrayList<Integer>,Integer> map;
    
    public int maxRevenue(ArrayList<Integer> x, ArrayList<Integer> revenue, int min_distance) { 
        ArrayList<Integer> current_pos_key = new ArrayList<>();
        current_pos_key.add(min_distance);
        for (Integer integer : x) {
            current_pos_key.add(integer);
        }
        for (Integer integer : revenue) {
            current_pos_key.add(integer);
        }
        
        if(map.containsKey(current_pos_key)){
            return map.get(current_pos_key);
        }else{
            //Si tenemos un solo elemento, éste será el máximo revenue
            if(x.size() == 1){
                map.put(current_pos_key,revenue.get(0));
                return map.get(current_pos_key);
            }
            
            //Recorremos de arriba a abajo, tomamos última posición
            int current_pos = x.get(x.size() - 1);
            
            /*Obtenemos el max revenue hasta ahora, (del elemento actual para
            abajo sin contar con el actual)*/
            int current_max_rev = maxRevenue(cut(x,0,x.size()-1), cut(revenue,0,revenue.size()-1), min_distance);
            
            //En las posiciones comprendidas entre 0 y la distancia mínima solo puede haber 1 billboard
            if(current_pos < min_distance){
                map.put(current_pos_key, Math.max(revenue.get(revenue.size() - 1), current_max_rev));
                return map.get(current_pos_key);
            }
            int previous_pos = x.get(x.size() - 2);
            
            //En caso de no cumplir con la distancia mínima decidimos si reemplazar billboards
            if(current_pos - previous_pos <= min_distance){
                /*Será 0 por defecto, en caso de no tener otro punto previo al
                billboard anterior, el previous_max_rev permanecerá en 0*/
                int previous_max_rev = 0;
                /*Max_rev previo a la colocación del billboard anterior, si no
                hay no entra en el bucle quedando el previous_max_rev en 0*/
                for (int i = 2; i < x.size(); i++) {
                    previous_pos = x.get(x.size() -i -1);
                    /*El punto de comparación previo al billboard anterior debe
                    ser un punto más allá de la distancia mínima*/
                    if(current_pos - previous_pos > min_distance){
                        previous_max_rev = maxRevenue(cut(x,0,x.size()-i), cut(revenue,0,revenue.size()-i), min_distance);
                        break;
                    }
                }
                
                //Reemplazamos billboard si el nuevo da mejor revenue
                //Si no, no colocamos el billboard actual y mantenemos el antiguo
                map.put(current_pos_key, Math.max(previous_max_rev + revenue.get(revenue.size()-1), current_max_rev));
                return map.get(current_pos_key);
            }
            //Si cumple con la distancia simplemente lo colocamos
            map.put(current_pos_key, current_max_rev + revenue.get(revenue.size() - 1));
            return map.get(current_pos_key);
        }
    }
    
    public ArrayList<Integer> cut(ArrayList<Integer> array, int pos1, int pos2){
        if(array.isEmpty() || pos1 < 0 || pos2 < 0 || pos2 < pos1 || pos2 > array.size()){
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = pos1; i < pos2; i++) {
            res.add(array.get(i));
        }
        return res;
    }

    public void setMap(File file) throws IOException, ClassNotFoundException {
        this.map = new MapNativeFileReader(file).read();
    }
    
    public void saveMap(File file) throws IOException {
        MapNativeFileWriter mjfw = new MapNativeFileWriter(file);
        mjfw.write(map);
    }
}