/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
public class MyMenuBar extends MyMenyBarModel{

    public MyMenuBar() {
        initializeMenu();
        initializeItems();
	addItemsToFilemenu();
	addItemsToEditMenu();
	addItesmToExportMenu();
        addItemsToMenu();
    }

    /**
     *
     */
    private void initializeMenu() {
        this.jMenuBar1 = new JMenuBar();
    }

    /**
     *
     * @return
     */
    public JMenuBar getMainMenu() {
        return this.jMenuBar1;
    }

    /**
     * Method for creating and initializing file menu.
     */
    private void initializeItems() {
        this.jMenu1 = new JMenu();
        this.jMenu2 = new JMenu();
	this.jMenu3 = new JMenu();

        this.jMenu1.setText("File");
        this.jMenu2.setText("Edit");
	this.jMenu3.setText("Export");
    }

    /**
     * Method for inserting file items to file menu.
     */
    private void addItemsToFilemenu()
    {
	this.newFile = new JMenuItem("New File");
	this.openFile = new JMenuItem("Open File");
	this.closeFile = new JMenuItem("Close File");
	
	this.jMenu1.add(this.newFile);
	this.jMenu1.add(this.openFile);
	this.jMenu1.add(this.closeFile);
    }
    
    /**
     * Method for initializing and inserting edit menu items.
     */
    private void addItemsToEditMenu()
    {
	this.backAction = new JMenuItem("Back");
	this.frontAction = new JMenuItem("forward");
	
	this.jMenu2.add(this.backAction);
	this.jMenu2.add(this.frontAction);
    }
    
    /**
     * Method for initializing and inserting export menu items.
     */
    private void addItesmToExportMenu()
    {
	this.exportToEps = new JMenuItem("Export to EPS");
	this.exportToXml = new JMenuItem("Export to XML");
	this.exportToPdf = new JMenuItem("Export to PDF");
	
	this.jMenu3.add(this.exportToEps);
	this.jMenu3.add(this.exportToXml);
	this.jMenu3.add(this.exportToPdf);
    }
    
    /**
     * Method for adding menus to menu bar
     */
    private void addItemsToMenu() {
        this.jMenuBar1.add(this.jMenu1);
        this.jMenuBar1.add(this.jMenu2);
	this.jMenuBar1.add(this.jMenu3);
    }

    /**
     * Method for add new file listener to new file item
     * @param newFileListener 
     */
    public void addActionListenerToNewFileItem(ActionListener newFileListener)
    {
	this.newFile.addActionListener(newFileListener);
	this.newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }
}
