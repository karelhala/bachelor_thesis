/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import GUI.MainContentModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
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
        
        List list = new ArrayList(Arrays.asList(UCLeftTop.getMainContentPane().getComponents()));
        list.addAll(Arrays.asList(UCLeftBottom.getMainContentPane().getComponents()));
        setListeners(list.toArray(), UCmain);
        
        this.UCContent.setCenterPane(UCmain.getMainContentPane());
        this.UCContent.setLeftTopPane(UCLeftTop.getMainContentPane());
        this.UCContent.setLeftBottomPane(UCLeftBottom.getMainContentPane());
    }
    
    public void setListeners(final Object[] allComponents, final UCMainContent UCMain)
    {
        for (Object comp : allComponents)
        {
            final JToggleButton toggleButton = (JToggleButton) comp;
            toggleButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    if(ev.getStateChange()==ItemEvent.SELECTED){
                        UCMain.setSelectedButton(toggleButton);
                        toggleButtonSelected(toggleButton, allComponents);
                    }
                }
            });
        }
    }
    
    
    private void toggleButtonSelected(JToggleButton selectedButton, Object[] allComponents) {
        for (Object comp : allComponents)
        {
            JToggleButton toggleButton = (JToggleButton) comp;
            if (selectedButton != toggleButton)
            {
                toggleButton.setSelected(false);
            }
        }
    }
                    
    public MainContentModel getUCContent()
    {
        return this.UCContent;
    }
    
}
