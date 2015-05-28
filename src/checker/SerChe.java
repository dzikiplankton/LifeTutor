/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;
import java.io.*;

/**
 *  Class extending @see Checker to allow data serialization.
 * @author Dziki
 */
public class SerChe extends Checker{
    /**
     * Method saving data.
     * @param ch - object to save
     * @param fileName - path to file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void serializeCheck(Checker ch,String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fileOut;

        fileOut = new FileOutputStream(fileName);

        ObjectOutputStream out;

        out = new ObjectOutputStream(fileOut);
        out.writeObject(ch);
        
    }
    
    /**
     * Method restoring data.
     * @param fileName - path to file
     * @return restored object.
     * @throws FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @throws IOException 
     */
    public static Checker deserializeCheck(String fileName) throws IOException, ClassNotFoundException {
        Checker ch;
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
            ch = (Checker) ois.readObject();
        

        return ch;
    }
}
