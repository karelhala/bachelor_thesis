/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Karel Hala
 */
public class PlusTab {
    private JButton buttonPlus;
    public void AddPlusTab(JTabbedPane tabPane)
    {
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        tabPane.addTab(null, null);
        tabPane.setBorder(null);
        int index = tabPane.getTabCount() - 1;
        this.buttonPlus = createPlusButton();
        closePanel.add(this.buttonPlus, BorderLayout.CENTER);
        tabPane.setTabComponentAt(index, closePanel);
        tabPane.setEnabledAt(index, false);
        tabPane.setSelectedIndex(index - 1);
    }
    
    private JButton createPlusButton()
    {
        JButton plusButton = new JButton(); 
        
        plusButton.setBorder(null);
        plusButton.setFocusable(false);
        plusButton.setContentAreaFilled(false);
        createIconsForButton(plusButton);
        return plusButton;
    }
    
    private void createIconsForButton(JButton button)
    {
        ImageIcon icon = new ImageIcon(CloseTabbedPane.class.getResource("/resources/greenPlus.png"));
        Image normalImage = icon.getImage();
        Image grayImage = GrayFilter.createDisabledImage(normalImage);
        ImageIcon greyIcon = new ImageIcon(grayImage);
        
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setRolloverIcon(icon);
        button.setIcon(greyIcon);
    }

    public void addMouseClickedListenerToPlus(MouseAdapter addNewTabListener) {
        this.buttonPlus.addMouseListener(addNewTabListener);
    }
}
