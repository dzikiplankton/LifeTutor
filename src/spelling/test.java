/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;
/**
 *
 * @author Dziki
 */
public class test {
    public static void main(String[] args) {
        Checker sp = new Checker();
        sp.read("rozbitki.txt");
        String a;
        a = "Rozbitkijshlhdshjsdf";
        boolean n =sp.includes(a.toCharArray());
    }
    
    
}
