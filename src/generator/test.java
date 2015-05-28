/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;
import java.io.IOException;
import java.util.*;
/**
 *  Class with simple test for package generator.
 * @author Dziki
 */
public class test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Generator gen =new Generator();
        gen.lib.read("psychology.txt");
        ArrayList<String> zdanie = new ArrayList<>();
        zdanie.add("Life");
        zdanie.add("will");
        String a=gen.generate(zdanie,6);
        System.out.print(a);
        
        SerLib.serializeLib(gen.lib, "serializedlib");
        gen.lib=null;
        gen.lib=SerLib.deserialize("serializedlib");
        a=gen.generate(zdanie,6);
        System.out.print(a);
        
    }
    
    
    
}
