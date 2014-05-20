/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Model which controlls menu items, setters and getters.
 * @author E819996
 */
public class MyMenyBarModel {

    /**
     * File menu.
     */
    protected JMenu jMenu1;
    /**
     * Back and forth menu.
     */
    protected JMenu jMenu2;
    /**
     * Export menu.
     */
    protected JMenu jMenu3;
    /**
     * Help menu for manuBar.
     */
    protected JMenu helpMenu;
    /**
     * Export to PDF menu item.
     */
    protected JMenuItem exportToPdf;
    /**
     * Export to EPS menu item.
     */
    protected JMenuItem exportToEps;
    /**
     * Export to XML menu item.
     */
    protected JMenuItem exportToXml;
    /**
     * New file menu item.
     */
    protected JMenuItem newFile;
    /**
     * Close file menu item.
     */
    protected JMenuItem closeFile;
    /**
     * Open file menu item.
     */
    protected JMenuItem openFile;
    /**
     * Back action menu item.
     */
    protected JMenuItem backAction;
    /**
     * Forward action menu item.
     */
    protected JMenuItem frontAction;
    /**
     * Save file menu item.
     */
    protected JMenuItem saveAction;
    /**
     * Save file as... menu item.
     */
    protected JMenuItem saveAsAction;
    /**
     * Exit application menu item.
     */
    protected JMenuItem exit;
    /**
     * Current help item of manuBar.
     */
    protected JMenuItem currentHelp;
    /**
     * Application help item of manuBar.
     */
    protected JMenuItem applicationHelp;
    /**
     * Use Case help item of manuBar.
     */
    protected JMenuItem useCaseHelp;
    /**
     * Class diagram help item of manuBar.
     */
    protected JMenuItem classDiagramHelp;
    /**
     * OOPN help item of manuBar.
     */
    protected JMenuItem oopnHelp;
    /**
     * Menu bar which holds each menu and menu item.
     */
    protected JMenuBar jMenuBar1;

    /**
     * Getter for file menu.
     * @return file menu as JMenu.
     */
    public JMenu getjMenu1() {
        return jMenu1;
    }

    /**
     * Getter for back and forward menu.
     * @return and forward menu as JMenu.
     */
    public JMenu getjMenu2() {
        return jMenu2;
    }

    /**
     * Getter for export menu.
     * @return export menu as JMenu.
     */
    public JMenu getjMenu3() {
        return jMenu3;
    }

    /**
     * Getter for export to PDF menu item.
     * @return exportToPdf as JMenuItem.
     */
    public JMenuItem getExportToPdf() {
        return exportToPdf;
    }

    /**
     * Getter for export to EPS menu item.
     * @return exportToEps as JMenuItem.
     */
    public JMenuItem getExportToEps() {
        return exportToEps;
    }

    /**
     * Getter for export to XML menu item.
     * @return exportToXml as JMenuItem.
     */
    public JMenuItem getExportToXml() {
        return exportToXml;
    }

    /**
     * Getter for new file menu item.
     * @return newFile as JMenuItem.
     */
    public JMenuItem getNewFile() {
        return newFile;
    }

    /**
     * Getter for close file menu item.
     * @return closeFile as JMenuItem.
     */
    public JMenuItem getCloseFile() {
        return closeFile;
    }

    /**
     * Getter for open file menu item.
     * @return openFile as JMenuItem.
     */
    public JMenuItem getOpenFile() {
        return openFile;
    }

    /**
     * Getter for get back action menu item.
     * @return backAction as JMenuItem.
     */
    public JMenuItem getBackAction() {
        return backAction;
    }

    /**
     * Getter for get forward action menu item.
     * @return frontAction as JMenuItem.
     */
    public JMenuItem getFrontAction() {
        return frontAction;
    }

    /**
     * Getter for get file menu.
     * @return jMenuBar1 as JMenuBar.
     */
    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    /**
     * Returns save action menu item.
     * @return saveAction as JMenuItem.
     */
    public JMenuItem getSaveAction() {
        return saveAction;
    }

    /**
     * Returns save file as... action menu item.
     * @return saveAsAction as JMenuItem.
     */
    public JMenuItem getSaveAsAction() {
        return saveAsAction;
    }

    /**
     * Sets file menu.
     * @param jMenu1 jMenu1 will be set as JMenu.
     */
    public void setjMenu1(JMenu jMenu1) {
        this.jMenu1 = jMenu1;
    }

    /**
     * Sets forward and back action menu.
     * @param jMenu2 jMenu2 will be set as JMenu.
     */
    public void setjMenu2(JMenu jMenu2) {
        this.jMenu2 = jMenu2;
    }

    /**
     * Sets export menu.
     * @param jMenu3 jMenu3 will be set as JMenu.
     */
    public void setjMenu3(JMenu jMenu3) {
        this.jMenu3 = jMenu3;
    }

    /**
     * Sets export to PDF menu item.
     * @param exportToPdf exportToPdf will be set as JMenuItem.
     */
    public void setExportToPdf(JMenuItem exportToPdf) {
        this.exportToPdf = exportToPdf;
    }

    /**
     * Sets export to EPS menu item.
     * @param exportToEps exportToEps will be set as JMenuItem.
     */
    public void setExportToEps(JMenuItem exportToEps) {
        this.exportToEps = exportToEps;
    }

    /**
     * Sets export to XML menu item.
     * @param exportToXml exportToXml will be set as JMenuItem.
     */
    public void setExportToXml(JMenuItem exportToXml) {
        this.exportToXml = exportToXml;
    }

    /**
     * Sets new file menu item.
     * @param newFile newFile will be set as JMenuItem.
     */
    public void setNewFile(JMenuItem newFile) {
        this.newFile = newFile;
    }

    /**
     * Sets close file menu item.
     * @param closeFile closeFile will be set as JMenuItem.
     */
    public void setCloseFile(JMenuItem closeFile) {
        this.closeFile = closeFile;
    }

    /**
     * Sets open file menu item.
     * @param openFile openFile will be set as JMenuItem.
     */
    public void setOpenFile(JMenuItem openFile) {
        this.openFile = openFile;
    }

    /**
     * Sets back action menu item.
     * @param backAction backAction will be set as JMenuItem.
     */
    public void setBackAction(JMenuItem backAction) {
        this.backAction = backAction;
    }

    /**
     * Sets front action menu item.
     * @param frontAction frontAction will be set as JMenuItem.
     */
    public void setFrontAction(JMenuItem frontAction) {
        this.frontAction = frontAction;
    }

    /**
     * Sets file menu.
     * @param jMenuBar1 jMenuBar1 will be set as JMenuBar.
     */
    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    /**
     * Sets save file menu item.
     * @param saveAction saveAction will be set as JMenuItem.
     */
    public void setSaveAction(JMenuItem saveAction) {
        this.saveAction = saveAction;
    }

    /**
     * Sets save file as.. menu item.
     * @param saveAsAction saveAsAction will be set as JMenuItem.
     */
    public void setSaveAsAction(JMenuItem saveAsAction) {
        this.saveAsAction = saveAsAction;
    }
}
