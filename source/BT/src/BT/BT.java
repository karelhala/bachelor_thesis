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
 * @author Karel
 */
public class BT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("You are about to run the program.");
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
        System.out.println("You have succesfully ran the program, I hope you nejoyed it.");
    }
}
