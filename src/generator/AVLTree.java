/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Dziki
 */
class AVLnode implements Serializable{

    public AVLnode() {
    }
    
    AVLnode left;
    AVLnode right;
    AVLnode parent;
    int ht;
    String var;
    ArrayList<Integer> keys;

    public AVLnode(String var, Integer key) {
        this.var = var;
        this.keys.add(key);
    }
    
    int compareTo(AVLnode k){
        return this.var.compareTo(k.var);
    }
    
    
    AVLnode find(String s){
        if(this.var.equals(s)){
            return this;
        }
        if(var.compareTo(s)>0)
            return this.left.find(s);
        else
            return this.right.find(s);
    }
    
    
    AVLnode  insert(AVLnode t, String var, int key) {
	if (t == null) {
		t = new AVLnode(var, key);
	} else if ((var.compareTo(t.var)) == 0) {
		t.keys.add(key);
	} else if ((var.compareTo(t.var)) > 0) {
		t.right = insert(t.right, var, key);
		if (BF(t) == -2)
			if (var.compareTo(t.right.var)> 0)
				t = RR(t);
			else
				t = RL(t);
	} else if (var.compareTo(t.var) < 0) {
		t.left = insert(t.left, var, key);
		if (BF(t) == 2)
			if (t.left.var.compareTo(var)>0)
				t = LL(t);
			else
				t = LR(t);
	}
	t.ht = height(t);
	return (t);
}

    int height(AVLnode t) {
	int lh, rh;
	if (t == null)
		return (0);
	if (t.left == null)
		lh = 0;
	else
		lh = 1 + t.left.ht;
	if (t.right == null)
		rh = 0;
	else
		rh = 1 + t.right.ht;
	if (lh > rh)
		return (lh);
	return (rh);
}
    AVLnode rotateright(AVLnode x) {
        if (x != null) {
            AVLnode y;
            y = x.left;
            x.left = y.right;
            y.right = x;
            x.ht = height(x);
            y.ht = height(y);
            return (y);
        }
        return x;
    }

    AVLnode rotateleft(AVLnode x) {
        if (x.right != null) {
            AVLnode y;
            y = x.right;
            x.right = y.left;
            y.left = x;
            x.ht = height(x);
            y.ht = height(y);
            return (y);
        }
        return x;
    }

    AVLnode RR(AVLnode t) {
        t = rotateleft(t);
        return (t);
    }

    AVLnode LL(AVLnode t) {
        t = rotateright(t);
        return (t);
    }

    AVLnode LR(AVLnode t) {
        t.left = rotateleft(t.left);
        t = rotateright(t);
        return (t);
    }

    AVLnode RL(AVLnode t) {
        t.right = rotateright(t.right);
        t = rotateleft(t);
        return (t);
    }

    int BF(AVLnode t) {
        int lh, rh;
        if (t == null) {
            return (0);
        }
        if (t.left == null) {
            lh = 0;
        } else {
            lh = 1 + t.left.ht;
        }
        if (t.right == null) {
            rh = 0;
        } else {
            rh = 1 + t.right.ht;
        }
        return (lh - rh);
    }

}
