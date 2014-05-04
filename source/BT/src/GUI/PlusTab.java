/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Class that creates plus tab at end of tabs in tabbed pane.
 * @author Karel Hala
 */
public class PlusTab {

    /**
     * Button that is plus button.
     */
    private JButton buttonPlus;

    /**
     * Method that creates pane for plus button, insert this button in it and insert this panel in tab Pane.
     * @param tabPane tab pane that holds this plus button
     */
    public void addPlusTab(JTabbedPane tabPane) {
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        closePanel.add(createPlusButton(), BorderLayout.CENTER);
	tabPane.addTab(null, null);
        tabPane.setBorder(null);
        int index = tabPane.getTabCount() - 1;
        tabPane.setTabComponentAt(index, closePanel);
        tabPane.setEnabledAt(index, false);
        tabPane.setSelectedIndex(index - 1);
    }

    /**
     * Method for creating initializing and creating new plus button.
     * @return JButton with icon as plus button.
     */
    private JButton createPlusButton() {
        this.buttonPlus = new JButton();
        this.buttonPlus.setBorder(null);
        this.buttonPlus.setFocusable(false);
        this.buttonPlus.setContentAreaFilled(false);
	this.buttonPlus.setToolTipText("Add new tab");
        createIconsForButton();
        return this.buttonPlus;
    }

    /**
     * Method for creating icon for plus button.
     */
    private void createIconsForButton() {
	try
	{
	    ImageIcon icon = new ImageIcon(CloseTabbedPane.class.getResource("/resources/plusIcon.png"));
	    Image normalImage = icon.getImage();
	    Image grayImage = GrayFilter.createDisabledImage(normalImage);
	    ImageIcon greyIcon = new ImageIcon(grayImage);

	    this.buttonPlus.setRolloverEnabled(true);
	    this.buttonPlus.setContentAreaFilled(false);
	    this.buttonPlus.setRolloverIcon(icon);
	    this.buttonPlus.setIcon(greyIcon);
	}
	catch(NullPointerException exep)
	{
	    System.err.println(exep.getMessage());
	    this.buttonPlus.setText("+");
	}
    }

    /**
     * Method for adding mouse listener to plus button.
     * @param addNewTabListener that will be triggered on button clicked.
     */
    public void addMouseClickedListenerToPlus(ActionListener addNewTabListener) {
        this.buttonPlus.addActionListener(addNewTabListener);
    }
}
