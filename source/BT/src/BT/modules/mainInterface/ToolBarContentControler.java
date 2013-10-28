/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.modules.UC.UCContentController;
import GUI.MainContentModel;
import GUI.ToolBarContentModel;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
/**
 *
 * @author Karel
 */
public class ToolBarContentControler {
    private ToolBarContentModel toolBarcontent;
    private MouseAdapter newFileMouseClicked;
    
    public ToolBarContentControler()
    {
        this.toolBarcontent = new ToolBarContentModel();
    }
    
    public void addBasicButtons(final WindowLayoutControler myLayout)
    {
        JPanel myPanel = this.toolBarcontent.getToolBarPane();
        JButton NewFileButton = toolBarcontent.addNewButton("New File");
        JButton Closebutton = toolBarcontent.addNewButton("Close File");
        
        this.newFileMouseClicked = new MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewFileButtonMouseClicked(evt, myLayout);
            }
        };
        NewFileButton.addMouseListener(this.newFileMouseClicked);
        
        Closebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt, myLayout);
            }
        });
        myPanel.add(NewFileButton);
        myPanel.add(Closebutton);
        this.toolBarcontent.setToolBarPane(myPanel);
        setCloseAndOpenShortCuts(myLayout);
    }
    
    private void NewFileButtonMouseClicked(MouseEvent evt, WindowLayoutControler myLayout) {
        addNewTab(myLayout);
    }
    
    private void addNewTab(WindowLayoutControler myLayout)
    {
        UCContentController UCController = new UCContentController();
        
        MainContentModel UMLContenctModel = new MainContentModel();
        MainContentModel OOPNContentModel = new MainContentModel();
        myLayout.addNewTab(UCController.getUCContent(), UMLContenctModel, OOPNContentModel);
    }
    
    private void CloseButtonMouseClicked(MouseEvent evt, WindowLayoutControler myLayout) {
        myLayout.removeTab(myLayout.getSelectedTab());
    }
    
    public ToolBarContentModel getToolBarcontent()
    {
        return this.toolBarcontent;
    }
    
    public MouseAdapter getNewFileMouseClicked()
    {
        return this.newFileMouseClicked;
    }
    
        
    private void setCloseAndOpenShortCuts(final WindowLayoutControler myLayout)
    {
        myLayout.getFileTab().getActionMap().put("closeTab", new AbstractAction() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    myLayout.removeTab(myLayout.getSelectedTab());
                }
            }
        );
        
        myLayout.getFileTab().getActionMap().put("newTab", new AbstractAction() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    addNewTab(myLayout);
                }
            }
        );
        
        InputMap inputMap = myLayout.getFileTab().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK), "newTab");
    }
}
