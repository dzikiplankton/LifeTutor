/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dziki
 */
public class Checker implements Serializable {

    Trie trie = new Trie();

    public boolean includes(char[] word) {
        return trie.inclueds(word);
    }

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
