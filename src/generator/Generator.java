/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.*;

/**
 *
 * @author Dziki
 */
public class Generator {

    Library lib;

    boolean have_key(AVLnode n, int key) {
        for (int i : n.keys) {
            if (n.keys.get(i) == key) {
                return true;
            }
        }
        return false;
    }

    boolean is_valid(ArrayList<AVLnode> t, int n, int key) {
        int s = 0;
        int i;
        for (i = n - 2; i >= 0; i--) {
            if (have_key(t.get(i), key + i - n + 1) == false) {
                break;
            } else {
                s++;
            }
        }
        return s > 0;
    }

    ArrayList<AVLnode> getNodes(ArrayList<String> tab) {
        ArrayList<AVLnode> ret = new ArrayList<AVLnode>();
        for (String a : tab) {
            ret.add(lib.root.find(a));
        }
        if (ret.size() != tab.size()) {
            return null;
        }
        int s = 0;
        for (int i : ret.get(ret.size() - 1).keys) {
            i++;
            if (is_valid(ret, tab.size(), i)) {
                return ret;
            }
        }
        return null;

    }

    String wrd_gen(ArrayList<String> tab, int n_gram) {
        StringBuilder ret = new StringBuilder("K: ");
        if (n_gram > (lib.next_key - 1)) {
            ret.append("mniej słów niż stopień n-gramu \n");
            return ret.toString();
        }
        int wrd = 0, first;
        int tab_n = n_gram - 1;
        int i = 0;
        int e = 1;
        ArrayList<Integer> good = new ArrayList<Integer>();
        ArrayList<AVLnode> nodes;
        nodes = getNodes(tab);
        if (nodes == null) {
            ret.append("nie znaleziono łańcucha \n");
            return ret.toString();
        }

        while (wrd < 15) {
            for (i = 0; i < nodes.get(nodes.size() - 1).keys.size(); i++) {
                if (is_valid(nodes, n_gram, nodes.get(nodes.size() - 1).keys.get(i))) {
                    good.add(nodes.get(nodes.size() - 1).keys.get(i));
                }
            }
            int lot = new Random().nextInt() % good.size();
            ArrayList<AVLnode> nodes1 = new ArrayList<>();
            for (i = 0; i < n_gram - 1; i++) {
                nodes1.add(nodes.get(i + 1));
            }
            nodes1.add(lib.pointers[lot]);
            nodes = nodes1;
            ret.append(nodes.get(nodes.size() - 1).var).append(" ");
            wrd++;
        }
        return ret.toString();
    }

}
