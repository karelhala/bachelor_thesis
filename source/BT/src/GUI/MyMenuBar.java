/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Karel Hala
 */
public class MyMenuBar {
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    
    public MyMenuBar()
    {
        initializeMenu();
        initializeItems();
        addItemsToMenu();
    }
    
    private void initializeMenu()
    {
        this.jMenuBar1 = new javax.swing.JMenuBar();
    }
    
    public javax.swing.JMenuBar getMainMenu()
    {
        javax.swing.JMenuBar menubar = this.jMenuBar1;
        return menubar;
    }

    private void initializeItems() {
        this.jMenu1 = new javax.swing.JMenu();
        this.jMenu2 = new javax.swing.JMenu();
        
        this.jMenu1.setText("File");
        this.jMenu2.setText("Edit");
    }

    private void addItemsToMenu() {
        this.jMenuBar1.add(this.jMenu1);
        this.jMenuBar1.add(this.jMenu2);
    }
    
}
