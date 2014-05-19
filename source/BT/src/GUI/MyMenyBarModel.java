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
 *
 * @author E819996
 */
public class MyMenyBarModel {

    /**
     * 
     */
    protected JMenu jMenu1;
    /**
     * 
     */
    protected JMenu jMenu2;
    /**
     * 
     */
    protected JMenu jMenu3;
    /**
     * Help menu for manuBar.
     */
    protected JMenu helpMenu;
    /**
     * 
     */
    protected JMenuItem exportToPdf;
    /**
     * 
     */
    protected JMenuItem exportToEps;
    /**
     * 
     */
    protected JMenuItem exportToXml;
    /**
     * 
     */
    protected JMenuItem newFile;
    /**
     * 
     */
    protected JMenuItem closeFile;
    /**
     * 
     */
    protected JMenuItem openFile;
    /**
     * 
     */
    protected JMenuItem backAction;
    /**
     * 
     */
    protected JMenuItem frontAction;
    /**
     * 
     */
    protected JMenuItem saveAction;
    /**
     * 
     */
    protected JMenuItem saveAsAction;
    /**
     * 
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
     * 
     */
    protected JMenuBar jMenuBar1;

    /**
     * 
     * @return 
     */
    public JMenu getjMenu1() {
        return jMenu1;
    }

    /**
     * 
     * @return 
     */
    public JMenu getjMenu2() {
        return jMenu2;
    }

    /**
     * 
     * @return 
     */
    public JMenu getjMenu3() {
        return jMenu3;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getExportToPdf() {
        return exportToPdf;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getExportToEps() {
        return exportToEps;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getExportToXml() {
        return exportToXml;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getNewFile() {
        return newFile;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getCloseFile() {
        return closeFile;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getOpenFile() {
        return openFile;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getBackAction() {
        return backAction;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getFrontAction() {
        return frontAction;
    }

    /**
     * 
     * @return 
     */
    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getSaveAction() {
        return saveAction;
    }

    /**
     * 
     * @return 
     */
    public JMenuItem getSaveAsAction() {
        return saveAsAction;
    }

    /**
     * 
     * @param jMenu1 
     */
    public void setjMenu1(JMenu jMenu1) {
        this.jMenu1 = jMenu1;
    }

    /**
     * 
     * @param jMenu2 
     */
    public void setjMenu2(JMenu jMenu2) {
        this.jMenu2 = jMenu2;
    }

    /**
     * 
     * @param jMenu3 
     */
    public void setjMenu3(JMenu jMenu3) {
        this.jMenu3 = jMenu3;
    }

    /**
     * 
     * @param exportToPdf 
     */
    public void setExportToPdf(JMenuItem exportToPdf) {
        this.exportToPdf = exportToPdf;
    }

    /**
     * 
     * @param exportToEps 
     */
    public void setExportToEps(JMenuItem exportToEps) {
        this.exportToEps = exportToEps;
    }

    /**
     * 
     * @param exportToXml 
     */
    public void setExportToXml(JMenuItem exportToXml) {
        this.exportToXml = exportToXml;
    }

    /**
     * 
     * @param newFile 
     */
    public void setNewFile(JMenuItem newFile) {
        this.newFile = newFile;
    }

    /**
     * 
     * @param closeFile 
     */
    public void setCloseFile(JMenuItem closeFile) {
        this.closeFile = closeFile;
    }

    /**
     * 
     * @param openFile 
     */
    public void setOpenFile(JMenuItem openFile) {
        this.openFile = openFile;
    }

    /**
     * 
     * @param backAction 
     */
    public void setBackAction(JMenuItem backAction) {
        this.backAction = backAction;
    }

    /**
     * 
     * @param frontAction 
     */
    public void setFrontAction(JMenuItem frontAction) {
        this.frontAction = frontAction;
    }

    /**
     * 
     * @param jMenuBar1 
     */
    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    /**
     * 
     * @param saveAction 
     */
    public void setSaveAction(JMenuItem saveAction) {
        this.saveAction = saveAction;
    }

    /**
     * 
     * @param saveAsAction 
     */
    public void setSaveAsAction(JMenuItem saveAsAction) {
        this.saveAsAction = saveAsAction;
    }
}
