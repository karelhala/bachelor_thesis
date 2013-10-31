/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import GUI.MainContentModel;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public final class UCContentController {
    private MainContentModel UCContent;
    
    public UCContentController()
    {
        this.UCContent = new MainContentModel();
        createComponents();
    }
    
    public void createComponents()
    {   
        UCMainContent UCmain = new UCMainContent();
        
        UCLeftTopContent UCLeftTop = new UCLeftTopContent();
        
        UCLeftBottomContent UCLeftBottom = new UCLeftBottomContent();
        
        setListeners(UCLeftTop.getMainContentPane().getComponents(), UCmain, true);
        setListeners(UCLeftBottom.getMainContentPane().getComponents(), UCmain, false);
        
        this.UCContent.setCenterPane(UCmain.getMainContentPane());
        this.UCContent.setLeftTopPane(UCLeftTop.getMainContentPane());
        this.UCContent.setLeftBottomPane(UCLeftBottom.getMainContentPane());
    }
    
    public void setListeners(final Component[] allComponents, final UCMainContent UCMain, final boolean isNeeded)
    {
        for (Component comp : allComponents)
        {
            final JToggleButton toggleButton = (JToggleButton) comp;
            toggleButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange()==ItemEvent.SELECTED){
                        toggleButtonSelected(toggleButton, allComponents);
                    }
                    if (isNeeded)
                    {
                        UCMain.setSelectedButton(getSelectedButton(allComponents));
                    }
                }
            });
        }
    }
    
    
    private void toggleButtonSelected(JToggleButton selectedButton, Component[] allComponents) {
        for (Component comp : allComponents)
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (selectedButton != toggleButton)
            {
                toggleButton.setSelected(false);
            }
        }
    }
    
    private JToggleButton getSelectedButton(Component[] allComponents)
    {
        for (Component comp : allComponents)
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (toggleButton.isSelected() == true)
            {
                return toggleButton;
            }
        }
        return null;
    }
    
    public MainContentModel getUCContent()
    {
        return this.UCContent;
    }
    
}
