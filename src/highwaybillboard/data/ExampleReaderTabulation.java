package highwaybillboard.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExampleReaderTabulation {
    
    private int[] x;
    private int[] revenue;
    
    public void read(String directory) throws FileNotFoundException, IOException {
        String line;
        FileReader f = new FileReader(directory);
        try (BufferedReader b = new BufferedReader(f)) {
            int count = 0;
            while((line = b.readLine())!=null) {
                if(count == 0){
                    String[] xString = line.split(",");
                    x = new int[xString.length];
                    for (int i = 0; i < xString.length; i++) {
                        x[i] = Integer.parseInt(xString[i]);
                    }
                }else if(count == 1){
                    String[] revenueString = line.split(",");
                    revenue = new int[revenueString.length];
                    for (int i = 0; i < revenueString.length; i++) {
                        revenue[i] = Integer.parseInt(revenueString[i]);
                    }
                }else{
                    break;
                }
                count++;
            }
        }
    }

    public int[] getX() {
        return x;
    }

    public int[] getRevenue() {
        return revenue;
    }
}
