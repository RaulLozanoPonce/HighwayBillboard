package highwaybillboard.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

public class MapNativeFileWriter {
    private final File file;

    public MapNativeFileWriter(File file) {
        this.file = file;
    }
    
    public void write(Map<ArrayList<Integer>,Integer> map) throws IOException {
        try (FileOutputStream os = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(map);
        }
    }
}