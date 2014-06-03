/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.mainInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Model which stores strings of helps and selectbox for which string should be shown.
 * @author Karel Hala
 */
public class HelpModel {
    /**
     * Help string about whole application.
     */
    protected String applicationString;
    
    /**
     * Help string about useCase diagram part.
     */
    protected String useCaseString;
    
    /**
     * Help string about classDiagram diagram part.
     */
    protected String classDiagramString;
    
    /**
     * Help string about petriNets part.
     */
    protected String petriNetString;
    
    /**
     * DropDown for selecting which string should be shown.
     */
    protected JComboBox helpSelect;
    
    /**
     * Text field which shows different string texts.
     */
    protected JTextArea displayedHelp;
    
    /**
     * Panel with selectbox and textField.
     */
    protected JPanel contentPanel;
    
    /**
     * Basic constructor. It will create each element and insert it into panel.
     */
    public HelpModel()
    {
        String[] selectString = {"Whole application help", "Use case help", "Class diagram help", "Objected oriented Petri net help"};
        helpSelect = new JComboBox(selectString);
        displayedHelp = new JTextArea();
        displayedHelp.setLineWrap(true);
        insertElements();
    }
    
    /**
     * Method for inserting elements of help into panel.
     */
    private void insertElements()
    {
        displayedHelp.setEditable(false);
        contentPanel = new JPanel(new BorderLayout());
        JScrollPane areaScrollPane = new JScrollPane(displayedHelp);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setPreferredSize(new Dimension(750, 500));
        contentPanel.add(helpSelect, BorderLayout.PAGE_START);
        contentPanel.add(areaScrollPane, BorderLayout.CENTER);
    }
}
