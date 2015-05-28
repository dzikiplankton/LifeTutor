/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import generator.AvlTree.Node;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class describing words library with their connections
 * @author Dziki
 */
public class Library implements Serializable {

    AvlTree tree = new AvlTree();
    Node[] pointers;
    
    /**
     * Number of words in library.
     */
    public Integer next_key = 0;

    void makePointers() {
        pointers = new Node[next_key];
    }

    void addPointers(Node n) {
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
    
    /**
     * Method creating library from txt file
     * @param path file path/name
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void read(String path) throws FileNotFoundException, IOException {

        BufferedReader br;
        br = new BufferedReader(new FileReader(path));
        String line = br.readLine();

        while (line != null) {
            Pattern pattern = Pattern.compile("\\w+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                this.tree.add(matcher.group(),next_key++);
            }
            try {
                line = br.readLine();
            } catch (Exception e) {
            }

        }
        br.close();
        makePointers();

        addPointers(tree.root);
    }

    
}
