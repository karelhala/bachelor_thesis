/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Class that extends array list by few methods.
 *
 * @author Karel
 * @param <E>
 */
public class MyArrayList<E> extends ArrayList<E> {

    /**
     * Method that will swap whole array by middle element.
     */
    public void swapWholeArrayList() {
        if (!this.isEmpty()) {
            for (int i = 0; i <= (this.size() - 1) / 2; i++) {
                Collections.swap(this, i, this.size() - 1 - i);
            }
        }
    }

    /**
     * Method for returning first elemnt. It will return element at position 0
     * if arrayList is not empty
     *
     * @return element at first position
     */
    public E getFirst() {
        if (!this.isEmpty()) {
            return this.get(0);
        }
        return null;
    }

    /**
     * Method for returning last elemnt. It will return element at position
     * arrayList.size()-1 if arrayList is not empty.
     *
     * @return element at last position
     */
    public E getLast() {
        if (!this.isEmpty()) {
            return this.get(this.size() - 1);
        }
        return null;
    }

    /**
     * Method for returning left muiddle element. It will return element at
     * position arrayList.size()/2-1 if arrayList is not empty. If size of
     * arrayList is bellow or equal 2 return first elemnt.
     *
     * @return element at left middle position
     */
    public E getLeftMiddle() {
        if (!this.isEmpty()) {
            if (this.size() <= 2) {
                return this.getFirst();
            }
            return this.get(this.size() / 2 - 1);
        }
        return null;
    }

    /**
     * Method for returning right muiddle element. It will return element at
     * position arrayList.size()/2 if arrayList is not empty. It will return
     * this elemnt only if size of arrayList is bigger than 1
     *
     * @return element at right middle position
     */
    public E getRightMiddle() {
        if (!this.isEmpty()) {
            if (this.size() > 1) {
                return this.get(this.size() / 2);
            }
        }
        return null;
    }

    /**
     * Add unique element to arrayList if it is not null. Check if array list
     * does not contain insertElement and if not, insert it.
     *
     * @param insertElement element that is being inserted.
     * @return this object.
     */
    public MyArrayList<E> addUnique(E insertElement) {
        if (!this.contains(insertElement) && insertElement != null) {
            this.add(insertElement);
        }
        return this;
    }

    /**
     * Add elemnts to arrayList using addUnique. Add only elements, that are not
     * null.
     *
     * @param collection items to be inserted.
     * @return this object as myArrayList<E>.
     */
    public MyArrayList<E> addAllUnique(Collection<E> collection) {
        for (E e : collection) {
            if (e != null) {
                this.addUnique(e);
            }
        }
        return this;
    }

    /**
     * Check if elemnt is last item in arrayList.
     *
     * @param element checked element.
     * @return true or false.
     */
    public Boolean isLast(E element) {
        return getLast().equals(element);
    }
}
