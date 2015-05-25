/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;

import java.util.ArrayList;

/**
 *
 * @author Dziki
 */
public class Trie {

    TrieNode root;

    public class TrieNode {

        public TrieNode(char leter) {
            this.leter = leter;
        }

        char leter;
        ArrayList<TrieNode> childrens;
        boolean isEnd = false;

        TrieNode fCh(char a) {
            if (this.childrens == null) {
                return null;
            }
            for (TrieNode c : childrens) {
                if (c.leter == a) {
                    return c;
                }
            }
            return null;
        }
    }

    void add(char[] word) {
        TrieNode current = root;
        TrieNode exist;
        int i = 0;
        for (char a : word) {
            i++;
            exist = current.fCh(a);
            if (exist == null) {
                current.childrens.add(new TrieNode(a));
                current = current.fCh(a);
                if (word.length == i) {
                    current.isEnd = true;
                }
            }
        }
    }
    
    boolean inclueds(char[] word){
        TrieNode current = root;
        TrieNode exist;
        int i = 0;
        for (char a : word) {
            i++;
            exist = current.fCh(a);
            if (exist == null) {
                return false;
            }
        }
        return current.isEnd;
    }
}
