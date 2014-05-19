/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.mainInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Manager for loading and setting help options.
 * @author Karel Hala
 */
public class HelpManager extends HelpModel{
    
    /**
     * Method for loading files from resources to strings.\
     * 
     * @return HelpManager for further use.
     */
    public HelpManager loadFiles()
    {
        InputStream resourceAsStream;
        resourceAsStream = HelpManager.class.getResourceAsStream("/resources/help/applicationHelp.txt");
        this.applicationString = new Scanner(resourceAsStream,"UTF8").useDelimiter("\\Z").next();
        resourceAsStream = HelpManager.class.getResourceAsStream("/resources/help/classDiagramHelp.txt");
        this.classDiagramString = new Scanner(resourceAsStream,"UTF8").useDelimiter("\\Z").next();
        resourceAsStream = HelpManager.class.getResourceAsStream("/resources/help/useCaseHelp.txt");
        this.useCaseString = new Scanner(resourceAsStream,"UTF8").useDelimiter("\\Z").next();
        resourceAsStream = HelpManager.class.getResourceAsStream("/resources/help/petriNetHelp.txt");
        this.petriNetString = new Scanner(resourceAsStream,"UTF8").useDelimiter("\\Z").next();
        return this;
    }
    
    /**
     * Method for showing dialog with selectbox and help text.
     * @param selectedTab
     */
    public void showDialog(int selectedTab)
    {
        this.displayedHelp.setText(getHelpBasedOnComboBox(selectedTab));
        this.helpSelect.setSelectedIndex(selectedTab);
        JOptionPane.showConfirmDialog(null, this.contentPanel,
                    "Help information", JOptionPane.OK_CANCEL_OPTION);
    }
    
    /**
     * Sets listener when combobox is changed.
     * @return HelpManager for further use.
     */
    public HelpManager setListenerToComboBox()
    {
        this.helpSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                displayedHelp.setText(getHelpBasedOnComboBox(helpSelect.getSelectedIndex()));
            }
        });
        return this;
    }
    
    /**
     * Method for loading which string help should be loaded.
     * @param selectedItem id of selcted help.
     * @return String from selected help.
     */
    private String getHelpBasedOnComboBox(int selectedItem)
    {
        switch (selectedItem) {
            case 0:  return this.applicationString;
            case 1: return this.useCaseString;
            case 2: return this.classDiagramString;
            case 3: return this.petriNetString;
        }
        return "error";
    }
}
