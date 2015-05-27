/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.*;
import java.io.*;

/**
 *
 * @author Dziki
 */
public class Library implements Serializable{

    AVLnode root = new AVLnode();
    AVLnode[] pointers;
    Integer next_key = 0;

    void makePointers() {
        pointers = new AVLnode[next_key];
    }

    void addPointers(AVLnode n) {
        if (n == null) {
            return;
        }
        int i;
        for (i = 0; i < n.keys.size(); i++) {
            pointers[(n.keys.get(i))] = n;
        }
        addPointers(n.left);
        addPointers(n.right);
    }

    void read(String path) {
        FileReader fr;

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
                    this.root.insert(root, st.sval,next_key++);
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
