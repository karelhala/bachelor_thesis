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
 *
 * @author Karel Hala
 */
public class BT {

    public static enum UCLineType {

        ASSOCIATION, INCLUDE, EXTENDS, GENERALIZATION
    };

    public static enum UCObjectType {

        ACTOR, USECASE
    };

    public static enum CDLineType {

        ASSOCIATION, AGGREGATION, COMPOSITION, GENERALIZATION, REALIZATION
    };

    public static enum CDObjectType {

        CLASS, INTERFACE
    };

    public static enum OOPNObjectType {

        PLACE, TRANSITION
    };

    public static enum OOPNLineType {

        JOIN
    };

    public static enum AttributeType {

        PUBLIC, PRIVATE, PROTECTED
    };

    public static enum ClassType {

        ACTOR, ACTIVITY, NONE
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
