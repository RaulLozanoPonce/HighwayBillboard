package highwaybillboard.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapNativeFileReader {
    private final File file;

    public MapNativeFileReader(File file) {
        this.file = file;
    }
    
    public Map<ArrayList<Integer>,Integer> read() throws IOException, ClassNotFoundException {
        Object object;
        try (FileInputStream is = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(is);
            object = ois.readObject();
            return (Map<ArrayList<Integer>,Integer>) object;
        }catch(FileNotFoundException e){
            return new HashMap<>();
        }
    }
}