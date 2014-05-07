/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel
 */
public class BottomLeftContentModel {

    final private JPanel contentPane;
    final private JScrollPane scrollPane;
    final private JPanel mainPane;

    public BottomLeftContentModel() {
        this.contentPane = new JPanel(new GridLayout(0, 1));
        this.scrollPane = new JScrollPane();
        this.mainPane = new JPanel(new BorderLayout());
        initilizeCompponent();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

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
     * Method that will insert text with button to contentPane
     *
     * @param insertedAttribute
     * @param deletebutton
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
     *
     * @param attributeLabel
     * @return
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
