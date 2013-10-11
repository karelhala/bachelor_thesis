/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel
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
         
        this.getActionMap().put("closeTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTab(component);
            }
          }
        );
        
         InputMap inputMap = this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
         
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
    }
    
    public void removeTab(Component component)
    {
        this.remove(component);
    }
    
    private JButton createCloseButton(final Component component)
    {
        JButton buttonClose = new JButton();
        buttonClose.setToolTipText("Close this tab");
        buttonClose.setRolloverEnabled(true);
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
        if (this.indexOfComponent(component) != -1)
        {
            removeTab(component);
        }
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
