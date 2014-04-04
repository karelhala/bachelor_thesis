/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT;

import BT.modules.mainInterface.MainInterfaceControler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main of bachelor's thesis project
 * @author Karel Hala
 */
public class BT {

    /**
     * Enum of lines for usecase diagram
     */
    public static enum UCLineType {

        ASSOCIATION, INCLUDE, EXTENDS, GENERALIZATION
    };

    /**
     * Enum of types of objects in usecase diagram
     */
    public static enum UCObjectType {

        ACTOR, USECASE
    };

    /**
     * Enum of lines for class diagram
     */
    public static enum CDLineType {

        ASSOCIATION, AGGREGATION, COMPOSITION, GENERALIZATION, REALIZATION
    };

    /**
     * Enum of types of objects for class diagram
     */
    public static enum CDObjectType {

        CLASS, INTERFACE
    };

    /**
     * Enum of types of objects for OOPN
     */
    public static enum OOPNObjectType {

        PLACE, TRANSITION
    };

    /**
     * Enum of lines for OOPN
     */
    public static enum OOPNLineType {

        JOIN
    };

    /**
     * Enum of attributes used in class diagram
     */
    public static enum AttributeType {

        PUBLIC, PRIVATE, PROTECTED
    };

    /**
     * Enum of types of class used in class diagram
     */
    public static enum ClassType {

        ACTOR, ACTIVITY, NONE, INTERFACE
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BT.class.getName()).log(Level.SEVERE, null, ex);
        }
        final MainInterfaceControler mainWindow = new MainInterfaceControler("BT");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindow.runTheMainWindow();
            }
        });
    }
}
