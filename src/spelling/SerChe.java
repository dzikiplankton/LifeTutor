/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;
import java.io.*;

/**
 *
 * @author Dziki
 */
public class SerChe extends Checker{
    public static void serializeCheck(Checker ch,String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fileOut;

        fileOut = new FileOutputStream(fileName);

        ObjectOutputStream out;

        out = new ObjectOutputStream(fileOut);
        out.writeObject(ch);
        
    }
    
    public static Checker deserializeCheck(String fileName) throws IOException, ClassNotFoundException {
        Checker ch;
        FileInputStream fis = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            ch = (Checker) ois.readObject();
        }

        return ch;
    }
}
