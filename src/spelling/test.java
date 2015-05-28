/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;

import java.io.IOException;

/**
 *
 * @author Dziki
 */
public class test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Checker sp = new Checker();
        sp.read("psychology.txt");
        String a,b;
        a = "are";
        b = "ZUZIA";
        boolean n =sp.includes(a.toCharArray());
        boolean f =sp.includes(b.toCharArray());

    }
      
}
