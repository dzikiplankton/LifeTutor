/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Dziki
 */
public class SerLib extends Generator {
    
    public static void serializeLib(Library lib,String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fileOut;

        fileOut = new FileOutputStream(fileName);

        ObjectOutputStream out;

        out = new ObjectOutputStream(fileOut);
        out.writeObject(lib);
        
    }
    
    public static Library deserialize(String fileName) throws IOException, ClassNotFoundException {
        Library lib;
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
            lib = (Library) ois.readObject();
        

        return lib;
    }
    
}
