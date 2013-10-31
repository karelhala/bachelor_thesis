/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Karel Hala
 */
public class CloseTabbedPane extends JTabbedPane{
    public CloseTabbedPane ()
    {
        super();
    }
    
    public void addCloseTab(String title, final JComponent component) {
        JPanel closePanel = new JPanel();
        JLabel closeTitle = new JLabel(title);
        JButton closeButton = createCloseButton(component);
        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);

        closePanel.setOpaque(false);

        closePanel.setLayout(f);
        closePanel.add(closeTitle);
        closePanel.add(closeButton);

        this.addTab(null, component);
        int index = this.indexOfComponent(component);
        this.setTabComponentAt(index, closePanel);

        this.setSelectedComponent(component);
    }
    
    public void removeTab(Component component)
    {
        if (this.indexOfComponent(component) != -1)
        {
            if (this.indexOfComponent(component) == this.getSelectedIndex())
            {
                
                this.setSelectedIndex(this.getTabCount()-3);
            }
        }
        if (this.getTabCount() == 1)
        {
            this.setSelectedIndex(-1);
        }
        
        this.remove(component);
    }
    
    private JButton createCloseButton(final Component component)
    {
        JButton buttonClose = new JButton();
        buttonClose.setToolTipText("Close this tab");
        buttonClose.setBorder(null);
        buttonClose.setFocusable(false);
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                buttonCloseMouseClicked(evt, component);
            }
        };
        buttonClose.addMouseListener(mouseListener);
        
        createIconsForButton(buttonClose);
        
        return buttonClose;
    }
    
    private void buttonCloseMouseClicked (MouseEvent evt, Component component)
    {
        removeTab(component);
    }
    
    private void createIconsForButton(JButton buttonClose)
    {
        ImageIcon icon = new ImageIcon(CloseTabbedPane.class.getResource("/resources/redSmallX.png"));
        Image normalImage = icon.getImage();
        Image grayImage = GrayFilter.createDisabledImage(normalImage);
        ImageIcon greyIcon = new ImageIcon(grayImage);
        
        buttonClose.setRolloverEnabled(true);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setRolloverIcon(icon);
        buttonClose.setIcon(greyIcon);
    }
    
}
