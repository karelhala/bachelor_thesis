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
 * Class which defines and creates panel with close button on it.
 *
 * @author Karel Hala
 */
public class CloseTabbedPane extends JTabbedPane {

    /**
     * Controller for whole toolbar.
     */
    final private ToolBarContentControler toolBarContent;

    /**
     * Basic constructor. It assign toolBarContent to toolBarContent.
     *
     * @param toolBarContent this will be toolBarContent.
     */
    public CloseTabbedPane(ToolBarContentControler toolBarContent) {
        super();
        this.toolBarContent = toolBarContent;
    }

    /**
     * Method for setting panel with title and close button on each tab.
     *
     * @param title file name.
     * @param component this will be close icon.
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
     * Remove tab with specific component.
     *
     * @param component Component that will be removed from file tabs.
     */
    public void removeTab(Component component) {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this file?", "Please confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION) {
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
                if (diagramPlaces.getDiagramNumber() > this.indexOfComponent(component)) {
                    diagramPlaces.setDiagramNumber(diagramPlaces.getDiagramNumber() - 1);
                }
            }
            this.remove(component);
        }
    }

    /**
     * Method for creating button which will remove tab at specific index.
     *
     * @param component Component which describes where button is located.
     * @return buttonClose as JButton.
     */
    private JButton createCloseButton(final Component component) {
        JButton buttonClose = new JButton();
        buttonClose.setToolTipText("Close this tab");
        buttonClose.setBorder(null);
        buttonClose.setFocusable(false);
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                buttonCloseMouseClicked(component);
            }
        };
        buttonClose.addMouseListener(mouseListener);

        createIconsForButton(buttonClose);

        return buttonClose;
    }

    /**
     * Event when clicked on remove tab button.
     *
     * @param component which describes what tab should be removed.
     */
    private void buttonCloseMouseClicked(Component component) {
        removeTab(component);
    }

    /**
     * For creating close icon for button. Icon is located at resources under name "closeFile.png". If no icon is found
     * it will have "x".
     *
     * @param buttonClose button that will have this icon.
     */
    private void createIconsForButton(JButton buttonClose) {
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            ImageIcon icon = new ImageIcon(cl.getResource("resources/closeFile.png"));
            Image normalImage = icon.getImage();
            Image grayImage = GrayFilter.createDisabledImage(normalImage);
            ImageIcon greyIcon = new ImageIcon(grayImage);
            buttonClose.setRolloverIcon(icon);
            buttonClose.setIcon(greyIcon);
        } catch (NullPointerException exception) {
            buttonClose.setText("X");
        }

        buttonClose.setRolloverEnabled(true);
        buttonClose.setContentAreaFilled(false);

    }

}
