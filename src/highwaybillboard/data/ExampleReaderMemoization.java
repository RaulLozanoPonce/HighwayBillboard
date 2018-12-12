package highwaybillboard.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExampleReaderMemoization {
    
    private final ArrayList<Integer> x = new ArrayList<>();
    private final ArrayList<Integer> revenue = new ArrayList<>();
    
    public void read(String directory) throws FileNotFoundException, IOException {
        String line;
        FileReader f = new FileReader(directory);
        try (BufferedReader b = new BufferedReader(f)) {
            int count = 0;
            while((line = b.readLine())!=null) {
                if(count == 0){
                    String[] xString = line.split(",");
                    for (String xString1 : xString) {
                        x.add(Integer.parseInt(xString1));
                    }
                }else if(count == 1){
                    String[] revenueString = line.split(",");
                    for (String revenueString1 : revenueString) {
                        revenue.add(Integer.parseInt(revenueString1));
                    }
                }else{
                    break;
                }
                count++;
            }
        }
    }

    public ArrayList<Integer> getX() {
        return x;
    }

    public ArrayList<Integer> getRevenue() {
        return revenue;
    }
}
