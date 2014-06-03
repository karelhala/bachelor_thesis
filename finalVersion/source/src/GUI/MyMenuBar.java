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
 * Class to descibe action of menu, also insert menu items in menus.
 *
 * @author Karel Hala
 */
public class MyMenuBar extends MyMenyBarModel {

    /**
     * Basic constructor. It creates each menu and insert menu items in it. It creates main menu, insert file menu
     * items, export menu items and help menu items.
     */
    public MyMenuBar() {
        initializeMenu();
        initializeItems();
        addItemsToFilemenu();
        addItemsToEditMenu();
        addItesmToExportMenu();
        addItemsToHelpMenu();
        addItemsToMenu();
    }

    /**
     * Creates main menu bar.
     */
    private void initializeMenu() {
        this.jMenuBar1 = new JMenuBar();
    }

    /**
     * Returns main menu bar.
     * @return main menu bar as JMenuBar.
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
        this.helpMenu = new JMenu();

        this.jMenu1.setText("File");
        this.jMenu2.setText("Edit");
        this.jMenu3.setText("Export");
        this.helpMenu.setText("Help");
    }

    /**
     * Method for inserting file items to file menu.
     */
    private void addItemsToFilemenu() {
        this.newFile = new JMenuItem("New File");
        this.openFile = new JMenuItem("Open File");
        this.closeFile = new JMenuItem("Close File");
        this.saveAction = new JMenuItem("Save File");
        this.saveAsAction = new JMenuItem("Save As...");
        this.exit = new JMenuItem("Exit");

        this.jMenu1.add(this.newFile);
        this.jMenu1.addSeparator();
        this.jMenu1.add(this.saveAction);
        this.jMenu1.add(this.saveAsAction);
        this.jMenu1.addSeparator();
        this.jMenu1.add(this.openFile);
        this.jMenu1.add(this.closeFile);
        this.jMenu1.addSeparator();
        this.jMenu1.add(this.exit);
    }

    /**
     * Method for initializing and inserting edit menu items.
     */
    private void addItemsToEditMenu() {
        this.backAction = new JMenuItem("Back");
        this.frontAction = new JMenuItem("forward");

        this.jMenu2.add(this.backAction);
        this.jMenu2.add(this.frontAction);
    }

    /**
     * Method for initializing and inserting export menu items.
     */
    private void addItesmToExportMenu() {
        this.exportToEps = new JMenuItem("Export to EPS");
        this.exportToXml = new JMenuItem("Export to XML");
        this.exportToPdf = new JMenuItem("Export to PDF");

        this.jMenu3.add(this.exportToEps);
        this.jMenu3.add(this.exportToXml);
        this.jMenu3.add(this.exportToPdf);
    }

    /**
     * Method for inserting help items in help menu.
     */
    private void addItemsToHelpMenu() {
        this.currentHelp = new JMenuItem("Help");
        this.useCaseHelp = new JMenuItem("UseCase diagram Help");
        this.classDiagramHelp = new JMenuItem("Class diagram Help");
        this.oopnHelp = new JMenuItem("Petri net Help");
        this.applicationHelp = new JMenuItem("Application Help");

        this.helpMenu.add(currentHelp);
        this.helpMenu.addSeparator();
        this.helpMenu.add(useCaseHelp);
        this.helpMenu.add(classDiagramHelp);
        this.helpMenu.add(oopnHelp);
        this.helpMenu.add(applicationHelp);
    }

    /**
     * Method for adding menus to menu bar
     */
    private void addItemsToMenu() {
        this.jMenuBar1.add(this.jMenu1);
//        this.jMenuBar1.add(this.jMenu2);
        this.jMenuBar1.add(this.jMenu3);
        this.jMenuBar1.add(this.helpMenu);
    }

    /**
     * Method for add new file listener to new file item
     *
     * @param newFileListener
     */
    public void addActionListenerToNewFileItem(ActionListener newFileListener) {
        this.newFile.addActionListener(newFileListener);
        this.newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for close file listener to close file item
     *
     * @param closeFileListener
     */
    public void addActionListenerToCloseFileItem(ActionListener closeFileListener) {
        this.closeFile.addActionListener(closeFileListener);
        this.closeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for open file listener to open file item
     *
     * @param openFileListener
     */
    public void addActionListenerToOpenNewFileItem(ActionListener openFileListener) {
        this.openFile.addActionListener(openFileListener);
        this.openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for back action listener to take back action
     *
     * @param backActionListener
     */
    public void addActionListenerToBackActionItem(ActionListener backActionListener) {
        this.backAction.addActionListener(backActionListener);
        this.backAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for forward action listener do again action.
     *
     * @param forwardActionListener
     */
    public void addActionListenerToForwardActionItem(ActionListener forwardActionListener) {
        this.backAction.addActionListener(forwardActionListener);
        this.backAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for adding listener to export to pdf.
     *
     * @param exportPdfListener
     */
    public void addActionListenerToExportPdf(ActionListener exportPdfListener) {
        this.exportToPdf.addActionListener(exportPdfListener);
        this.exportToPdf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
    }

    /**
     * Method for adding listener to export to eps.
     *
     * @param exportEpsListener
     */
    public void addActionListenerToExportEps(ActionListener exportEpsListener) {
        this.exportToEps.addActionListener(exportEpsListener);
        this.exportToEps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
    }

    /**
     * Method for adding listener to export to xml.
     *
     * @param exportXmlListener
     */
    public void addActionListenerToExportXml(ActionListener exportXmlListener) {
        this.exportToXml.addActionListener(exportXmlListener);
        this.exportToXml.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
    }

    /**
     * Method for adding save action listener to save option.
     *
     * @param saveAction
     */
    public void addActionListenerToSave(ActionListener saveAction) {
        this.saveAction.addActionListener(saveAction);
        this.saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    /**
     * Method for setting listener to save as.
     *
     * @param saveAsAction
     */
    public void addActionListenerToSaveAs(ActionListener saveAsAction) {
        this.saveAsAction.addActionListener(saveAsAction);
    }

    /**
     * Method for setting listener to exit application.
     *
     * @param exitApplication listener.
     */
    public void addActionListenerToExit(ActionListener exitApplication) {
        this.exit.addActionListener(exitApplication);
    }

    /**
     * Action listener to show basic help dialog.
     *
     * @param showHelp listener.
     */
    public void addActionListenerToCurrentHelp(ActionListener showHelp) {
        this.currentHelp.addActionListener(showHelp);
        this.currentHelp.setAccelerator(KeyStroke.getKeyStroke("F1"));
    }

    /**
     * Action listener to show useCase help dialog.
     *
     * @param showHelp listener.
     */
    public void addActionListenerToUseCaseHelp(ActionListener showHelp) {
        this.useCaseHelp.addActionListener(showHelp);
    }

    /**
     * Action listener to show class diagram help dialog.
     *
     * @param showHelp listener.
     */
    public void addActionListenerToClassDiagramHelp(ActionListener showHelp) {
        this.classDiagramHelp.addActionListener(showHelp);
    }

    /**
     * Action listener to show OOPN help dialog.
     *
     * @param showHelp listener.
     */
    public void addActionListenerToOOPNHelp(ActionListener showHelp) {
        this.oopnHelp.addActionListener(showHelp);
    }

    /**
     * Action listener to show application help dialog.
     *
     * @param showHelp listener.
     */
    public void addActionListenerToApplicationHelp(ActionListener showHelp) {
        this.applicationHelp.addActionListener(showHelp);
    }
}
