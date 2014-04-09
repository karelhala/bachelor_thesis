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
    protected JMenu jMenu1;
    protected JMenu jMenu2;
    protected JMenu jMenu3;
    protected JMenuItem exportToPdf;
    protected JMenuItem exportToEps;
    protected JMenuItem exportToXml;
    protected JMenuItem newFile;
    protected JMenuItem closeFile;
    protected JMenuItem openFile;
    protected JMenuItem backAction;
    protected JMenuItem frontAction;
    protected JMenuItem saveAction;
    protected JMenuItem saveAsAction;
    protected JMenuBar jMenuBar1;

    public JMenu getjMenu1() {
	return jMenu1;
    }

    public JMenu getjMenu2() {
	return jMenu2;
    }

    public JMenu getjMenu3() {
	return jMenu3;
    }

    public JMenuItem getExportToPdf() {
	return exportToPdf;
    }

    public JMenuItem getExportToEps() {
	return exportToEps;
    }

    public JMenuItem getExportToXml() {
	return exportToXml;
    }

    public JMenuItem getNewFile() {
	return newFile;
    }

    public JMenuItem getCloseFile() {
	return closeFile;
    }

    public JMenuItem getOpenFile() {
	return openFile;
    }

    public JMenuItem getBackAction() {
	return backAction;
    }

    public JMenuItem getFrontAction() {
	return frontAction;
    }

    public JMenuBar getjMenuBar1() {
	return jMenuBar1;
    }

    public JMenuItem getSaveAction() {
        return saveAction;
    }

    public JMenuItem getSaveAsAction() {
        return saveAsAction;
    }
    
    public void setjMenu1(JMenu jMenu1) {
	this.jMenu1 = jMenu1;
    }

    public void setjMenu2(JMenu jMenu2) {
	this.jMenu2 = jMenu2;
    }

    public void setjMenu3(JMenu jMenu3) {
	this.jMenu3 = jMenu3;
    }

    public void setExportToPdf(JMenuItem exportToPdf) {
	this.exportToPdf = exportToPdf;
    }

    public void setExportToEps(JMenuItem exportToEps) {
	this.exportToEps = exportToEps;
    }

    public void setExportToXml(JMenuItem exportToXml) {
	this.exportToXml = exportToXml;
    }

    public void setNewFile(JMenuItem newFile) {
	this.newFile = newFile;
    }

    public void setCloseFile(JMenuItem closeFile) {
	this.closeFile = closeFile;
    }

    public void setOpenFile(JMenuItem openFile) {
	this.openFile = openFile;
    }

    public void setBackAction(JMenuItem backAction) {
	this.backAction = backAction;
    }

    public void setFrontAction(JMenuItem frontAction) {
	this.frontAction = frontAction;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
	this.jMenuBar1 = jMenuBar1;
    }

    public void setSaveAction(JMenuItem saveAction) {
        this.saveAction = saveAction;
    }

    public void setSaveAsAction(JMenuItem saveAsAction) {
        this.saveAsAction = saveAsAction;
    }
}
