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
        sp.read("rozbitki.txt");
        SerChe.serializeCheck(sp, "abba");
        Checker ns = SerChe.deserializeCheck("abba");
        String a,b;
        a = "Rozbitki";
        b = "sdfjkhsdfgksdjfghsd";
        boolean n =ns.includes(a.toCharArray());
        boolean f =ns.includes(b.toCharArray());
    }
    
    
}
