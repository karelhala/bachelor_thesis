/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.managers.DiagramPlacesManager;
import BT.modules.mainInterface.ToolBarContentControler;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Karel Hala
 */
public class CloseTabbedPane extends JTabbedPane {

    /**
     * 
     */
    final private ToolBarContentControler toolBarContent ;
    
    public CloseTabbedPane(ToolBarContentControler toolBarContent) {
        super();
        this.toolBarContent = toolBarContent;
    }

    /**
     *
     * @param title
     * @param component
     */
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

    /**
     *
     * @param component
     */
    public void removeTab(Component component) {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this file?", "Please confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            if (this.indexOfComponent(component) != -1) {
                if (this.indexOfComponent(component) == this.getSelectedIndex()) {

                    this.setSelectedIndex(this.getTabCount() - 3);
                }
            }
            if (this.getTabCount() == 1) {
                this.setSelectedIndex(-1);
            }
            this.toolBarContent.removeDiagramPlaceById(this.indexOfComponent(component));
            for (DiagramPlacesManager diagramPlaces : toolBarContent.getDiagramPlaces()) {
                if (diagramPlaces.getDiagramNumber() > this.indexOfComponent(component))
                {
                    diagramPlaces.setDiagramNumber(diagramPlaces.getDiagramNumber() - 1);
                }
            }
            this.remove(component);
        }
    }

    /**
     *
     * @param component
     * @return
     */
    private JButton createCloseButton(final Component component) {
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

    /**
     *
     * @param evt
     * @param component
     */
    private void buttonCloseMouseClicked(MouseEvent evt, Component component) {
        removeTab(component);
    }

    /**
     *
     * @param buttonClose
     */
    private void createIconsForButton(JButton buttonClose) {
        try{
            ClassLoader cl = this.getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(cl.getResource("resources/closeFile.png"));
            Image normalImage = icon.getImage();
            Image grayImage = GrayFilter.createDisabledImage(normalImage);
            ImageIcon greyIcon = new ImageIcon(grayImage);
            buttonClose.setRolloverIcon(icon);
            buttonClose.setIcon(greyIcon);
        }
        catch (NullPointerException exception)
        {
            buttonClose.setText("X");
        }

        buttonClose.setRolloverEnabled(true);
        buttonClose.setContentAreaFilled(false);
        
    }

}
