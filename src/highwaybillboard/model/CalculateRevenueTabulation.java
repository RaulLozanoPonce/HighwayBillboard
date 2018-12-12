package highwaybillboard.model;

public class CalculateRevenueTabulation {
    
    public int maxRevenue(int x[], int revenue[], int t) {
        
        //Array que almacena el beneficio máximo para cada punto de la carretera
        int[] maxRev = new int[x[x.length - 1]+1];
        //Contador relativo al vector x
        int nxtbb = 0;
        
        //Vamor rellenando el array de los beneficios
        for (int i = 1; i <= x[x.length - 1]; i++){
            if (nxtbb < x.length){
                if (x[nxtbb] != i){
                    /*Mientras que sea un punto entre dos valores de x, ponemos
                    el valor del punto anterior*/
                    maxRev[i] = maxRev[i-1];
                }else{
                    if (i <= t){
                        maxRev[i] = Math.max(maxRev[i-1], revenue[nxtbb]);
                    }else{
                        maxRev[i] = Math.max(maxRev[i-t-1]+revenue[nxtbb], maxRev[i-1]);
                    }
                    nxtbb++;
                }
            }else{
                /*Si hemos recorrido todas las posiciones de x, terminamos de
                rellenar el vector con el último valor*/
                maxRev[i] = maxRev[i - 1];
            }
        }
        //Devolvemos el último valor
        return maxRev[x[x.length - 1]];
    }
}
