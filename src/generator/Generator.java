/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.*;
import generator.AvlTree.Node;

/**
 *
 * @author Dziki
 */
public class Generator {

    public Library lib = new Library();

    boolean contains(ArrayList<Node> nodes) {
        int s;
        for (int j = 0; j < nodes.get(nodes.size() - 1).keys.size(); j++) {
            s = 0;
            int key = nodes.get(nodes.size() - 1).keys.get(j);
            for (int k = 1, i = (nodes.size() - (1 + k)); i > 0; k++, i--) {
                if (nodes.get(i).haveKey(key - k)) {
                    s++;
                }
            }
            if (s == nodes.size() - 1) {
                return true;
            }
        }
        return false;
    }

    ArrayList<Integer> goodKeys(ArrayList<Node> nodes) {
        ArrayList<Integer> goodkeys = new ArrayList<>();
        int s;
        for (int j = 0; j < nodes.get(nodes.size() - 1).keys.size(); j++) {
            s = 0;
            int key = nodes.get(nodes.size() - 1).keys.get(j);
            for (int k = 1, i = (nodes.size() - (1 + k)); i >= 0; k++, i--) {
                if (nodes.get(i).haveKey(key - k)) {
                    s++;
                }
            }
            if (s == nodes.size() - 1) {
                if(key+1<lib.next_key)
                goodkeys.add(key + 1);
            }
        }
        return goodkeys;
    }

    ArrayList<Node> getNodes(ArrayList<String> tab) {
        ArrayList<Node> ret = new ArrayList<>();
        for (String a : tab) {
            ret.add(lib.tree.fromTree(a));
        }
        ret.removeAll(Collections.singleton(null));
        if (ret.size() != tab.size()) {
            return null;
        }
        return ret;
    }

    public String generate(ArrayList<String> tab, int wordsNum) {
        StringBuilder ret = new StringBuilder("K: ");
        if (tab.size() > (lib.next_key - 1)) {
            ret.append("n-gram deegre bigger than words number in library \n");
            return ret.toString();
        }
        int wrd = 0;
        ArrayList<Integer> good;
        ArrayList<Node> nodes;
        nodes = getNodes(tab);
        if (nodes == null) {
            ret.append("no matches found \n");
            return ret.toString();
        }

        while (wrd < wordsNum) {
            good=goodKeys(nodes);
            if(good.size()==(int)0){
                ret.append("error unable to generate next word probably last generated word was last in library \n");
                return ret.toString();
            }
            Integer lot = new Random().nextInt(good.size());
            ArrayList<Node> nodes1 = new ArrayList<>();
            for (int i = 0; i < nodes.size()-1; i++) {
                nodes1.add(nodes.get(i + 1));
            }
            nodes1.add(lib.pointers[good.get(lot)]);
            nodes = nodes1;
            ret.append(nodes.get(nodes.size() - 1).value).append(" ");
            wrd++;
        }
        ret.append("\n");
        return ret.toString();
    }

}
