/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dziki
 */
public class Checker implements Serializable {

    Trie trie = new Trie();
    
    /**
     * Method checking the word in dictionary.
     * @param word -word to check.
     * @return true - dic includes word or false - not 
     */
    public boolean includes(char[] word) {
        return trie.inclueds(word);
    }
    
    /**
     * Method creating text base from file for dictionary.
     * @param path - file name
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
                this.trie.add(matcher.group().toCharArray());
            }
            try {
                line = br.readLine();
            } catch (Exception e) {
            }

        }
        br.close();
    }
}
