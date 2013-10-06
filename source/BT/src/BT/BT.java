/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT;

import BT.modules.mainInterface.MainInterfaceControler;
/**
 *
 * @author Karel
 */
public class BT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("you are about to run the program");
        final MainInterfaceControler mainWindow = new MainInterfaceControler("BT");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindow.runTheMainWindow();
            }
        });
    }
}
