/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Karel
 * @param <E>
 */
public class MyArrayList<E> extends ArrayList<E> {

    public void swapWholeArrayList() {
        if (!this.isEmpty()) {
            for (int i = 0; i <= (this.size() - 1) / 2; i++) {
                Collections.swap(this, i, this.size() - 1 - i);
            }
        }
    }

    public E getFirst() {
        if (!this.isEmpty()) {
            return this.get(0);
        }
        return null;
    }

    public E getLast() {
        if (!this.isEmpty()) {
            return this.get(this.size() - 1);
        }
        return null;
    }

    public E getLeftMiddle() {
        if (!this.isEmpty()) {
            if (this.size() <= 2) {
                return this.getFirst();
            }
            return this.get(this.size() / 2 - 1);
        }
        return null;
    }

    public E getRightMiddle() {
        if (!this.isEmpty()) {
            if (this.size() > 1) {
                return this.get(this.size() / 2 - 1 + 1);
            }
        }
        return null;
    }
}
