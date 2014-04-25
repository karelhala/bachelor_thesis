/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT;

import BT.modules.mainInterface.MainInterfaceControler;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

        ASSOCIATION, INCLUDE, EXTENDS, GENERALIZATION, USERINPUT
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

        ASSOCIATION, AGGREGATION, COMPOSITION, GENERALIZATION, REALIZATION, USERINPUT
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
        final MainInterfaceControler mainWindow = new MainInterfaceControler("Tool for Software Systems Design");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindow.runTheMainWindow();
            }
        });
    }
    
    /**
     * Method that creates Jpanel containing Jcomponent and label for this component.
     * @param element to be inserted.
     * @param label Jlabel, that describes it.
     * @return JPanel
     */
    public static JPanel elementWithLabelAbove(JComponent element, JLabel label)
    {
        JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.add(label, BorderLayout.NORTH);
        newPanel.add(element, BorderLayout.CENTER);
        return newPanel;
    }
}
