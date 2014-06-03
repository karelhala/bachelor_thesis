/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

/**
 * Exception thrown when wrong type of attribute is provided.
 *
 * @author Karel Hala
 */
public class ClassTypeException extends Exception {

    /**
     * Creates a new instance of <code>ClassTypeException</code> without detail message.
     */
    public ClassTypeException() {
    }

    /**
     * Constructs an instance of <code>ClassTypeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ClassTypeException(String msg) {
        super(msg);
    }
}
