/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Karel
 */
public class PlusTab {
    public void AddPlusTab(JTabbedPane tabPane)
    {
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        tabPane.addTab(null, null);
        tabPane.setBorder(null);
        int index = tabPane.getTabCount() - 1;
        closePanel.add(createPlusButton(), BorderLayout.CENTER);
        tabPane.setTabComponentAt(index, closePanel);
        tabPane.setEnabledAt(index, false);
        tabPane.setSelectedIndex(index - 1);
    }
    
    private JButton createPlusButton()
    {
        JButton plusButton = new JButton("+"); 
        
        plusButton.setBorder(null);
        plusButton.setFocusable(false);
        plusButton.setContentAreaFilled(false);
        
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                plusButtonMouseClicked(evt);
            }
        };
        
        plusButton.addMouseListener(mouseListener);
        
        return plusButton;
    }
    
    private void plusButtonMouseClicked(MouseEvent evt) {
        System.out.println("clicked");
    }
}
