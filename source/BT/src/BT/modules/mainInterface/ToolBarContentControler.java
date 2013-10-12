/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import GUI.MainContentModel;
import GUI.ToolBarContentModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
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
    }
    
    private void NewFileButtonMouseClicked(MouseEvent evt, WindowLayoutControler myLayout) {
        addNewTab(myLayout);
    }
    
    private void addNewTab(WindowLayoutControler myLayout)
    {
        MainContentModel UCContentModel = new MainContentModel();
        MainContentModel UMLContenctModel = new MainContentModel();
        MainContentModel OOPNContentModel = new MainContentModel();
        myLayout.addNewTab(UCContentModel, UMLContenctModel, OOPNContentModel);
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
}
