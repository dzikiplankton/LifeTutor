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
public class Checker {

    Trie trie = new Trie();
    


    boolean includes(char[] word) {
        return trie.inclueds(word);
    }
    

    void read(String path) {
        FileReader fr;

        //OTWIERANIE PLIKU:
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            return;
        }

        StreamTokenizer st = new StreamTokenizer(fr);

        try {
            int wartosc;
            while ((wartosc = st.nextToken()) != StreamTokenizer.TT_EOF) {
                if (wartosc == StreamTokenizer.TT_WORD) {
                    this.trie.add(st.sval.toCharArray());
                }
            }
        } catch (IOException e) {
        }

        try {
            fr.close();
        } catch (IOException e) {
        }
    }
}
