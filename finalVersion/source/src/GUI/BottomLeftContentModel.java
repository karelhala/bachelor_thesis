/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Class for holding class variables and methods. Fisrt variables will be shown and below them methods. Each line will
 * hav button for deleting them.
 *
 * @author Karel Hala
 */
public class BottomLeftContentModel {

    /**
     * Content pane which holds class variables and methods.
     */
    final private JPanel contentPane;
    /**
     * scroll pane for scrolling.
     */
    final private JScrollPane scrollPane;
    /**
     * Each component is stored in this.
     */
    final private JPanel mainPane;

    /**
     * Basic constructor. It sets layout of this bottom left component to 0 and 1. Each new oject will be inserted
     * below. It creates contentPane, scrollPane, mainPane and intialize content pane.
     */
    public BottomLeftContentModel() {
        this.contentPane = new JPanel(new GridLayout(0, 1));
        this.scrollPane = new JScrollPane();
        this.mainPane = new JPanel(new BorderLayout());
        initilizeCompponent();
    }

    /**
     * Returns content pane which holds each class variable and method.
     *
     * @return contentPane as JPanel.
     */
    public JPanel getContentPane() {
        return contentPane;
    }

    /**
     * Returns main pane with each cariable and method.
     *
     * @return mainPane as JPanel.
     */
    public JPanel getMainPane() {
        return mainPane;
    }

    /**
     * Method that will add contentPane in scrollPane and this scrollPane into main pane.
     */
    private void initilizeCompponent() {
        this.scrollPane.add(contentPane);
        this.scrollPane.setViewportView(contentPane);
        this.mainPane.add(this.scrollPane, BorderLayout.CENTER);
    }

    /**
     * Method that will insert text with button to contentPane. Create new JLabel and add listener when double clicked
     * on it to bring dialog for adding arguments for selected method. Variables are in top of bottom left panel, method
     * are in bottom of it.
     *
     * @param insertedAttribute Attribute either method or class variable.
     * @param deletebutton JButton to remove this attribute.
     */
    public void addObjectsToPane(final Attribute insertedAttribute, JButton deletebutton) {
        final JLabel newLabel = new JLabel();
        JPanel newPanel = new JPanel(new BorderLayout());
        newLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() % 2 == 0) {
                    if (insertedAttribute instanceof Method) {
                        String result = JOptionPane.showInputDialog("Please Enter attribues delimtered by comma.", ((Method) insertedAttribute).getAttributesAsString());
                        if (result != null) {
                            result = result.replaceAll("\\s+", "");
                            for (String oneAttribute : result.split(",")) {
                                ((Method) insertedAttribute).addNewAttribute(oneAttribute);
                            }
                            newLabel.setText(insertedAttribute.getAttributeStyled());
                        }
                    }
                }
            }
        });
        newLabel.setText(insertedAttribute.getAttributeStyled());
        newLabel.setToolTipText(insertedAttribute.getAttributeStyled());
        deletebutton.setToolTipText("Delete " + insertedAttribute.getAttributeStyled());
        newPanel.add(newLabel, BorderLayout.CENTER);
        newPanel.add(deletebutton, BorderLayout.LINE_END);
        this.contentPane.add(newPanel);
    }

    /**
     * Method for inserting class to bottom left with tooltip. It will be inserted in left top.
     *
     * @param classLabel JLabel to be inserted.
     * @return BottomLeftContentModel for further use.
     */
    public BottomLeftContentModel addClassLabelToPane(JLabel classLabel) {
        this.mainPane.removeAll();
        this.mainPane.add(this.scrollPane, BorderLayout.CENTER);
        classLabel.setToolTipText("Object's name");
        this.mainPane.add(classLabel, BorderLayout.LINE_START);
        this.mainPane.revalidate();
        this.mainPane.repaint();
        return this;
    }

    /**
     * Method for inserting new class variable or method to bottom left. It will insert label with tooltip to content
     * panel.
     *
     * @param attributeLabel JLabel to be inserted.
     * @return BottomLeftContentModel for further use.
     */
    public BottomLeftContentModel addAttributesToPane(JLabel attributeLabel) {
        attributeLabel.setToolTipText("Method of object");
        this.contentPane.add(attributeLabel);
        return this;
    }

    /**
     * Method for clearing content pane. It will remove all components from content pane, repaint it and revalidate it.
     */
    public void destroyContent() {
        this.contentPane.removeAll();
        this.contentPane.repaint();
        this.contentPane.revalidate();
    }
}
