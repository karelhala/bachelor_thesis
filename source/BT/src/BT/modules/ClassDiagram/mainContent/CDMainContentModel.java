/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.DiagramPlacesManager;
import BT.managers.DrawingListeners;
import BT.managers.MainContentController;
import BT.modules.ClassDiagram.CDLeftBottomContent;
import BT.modules.ClassDiagram.CDLeftTopContent;
import BT.modules.ClassDiagram.CDMainContent;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Class for storing all variables for main content in class diagram. Here every variable that is needed for main
 * content is stored, gettery and settery are here.
 *
 * @author Karel Hala
 */
abstract class CDMainContentModel extends MainContentController {

    /**
     * Panel which has buttons for changing join edges. It stores each button for changing style and type of join egdes.
     */
    protected CDLeftBottomContent LeftBottomContent;
    /**
     * Panel which stores buttons for changing class type. If you want to create either class or interface this class
     * has buttons for setting what you want to create.
     */
    protected CDLeftTopContent LeftTopContent;
    /**
     * Connector between class diagram and use case. When the class is being reconnected with use case, this connector
     * is called.
     */
    protected CDUseCaseConnector useCaseConnector;
    /**
     * Reactivator between class diagram and use case. It stores methods for reactivating use cases and class diagrams
     * together.
     */
    protected CDUseCaseReactivator useCaseReactivator;
    /**
     * Panel with buttons to reactivate or delete inactive objects. In here it is also stored adding new method or class
     * variable.
     */
    protected BottomRightContentModel bottomRightContent;
    /**
     * Panel with text fields and buttons for inserting new attributes. In this panel is stored textfields and
     * comboboxes for creating new class variable or new ethod.
     */
    protected ClassDiagramAttributesPanel attributesPanel;
    /**
     * Panel for saving methods and variables of class.
     */
    protected BottomLeftContentModel leftContentModel;
    /**
     * controller for handeling deleting and managing of variables and methods of class.
     */
    protected CDBottomLeftController leftBottomController;

    /**
     * Basic Constructor. It sets DiagramPlacesManager, BottomRightContentModel and ClassDiagramAttributesPanel from
     * arguments. Creates new CDMaincontent, CDUseCaseConnector and CDUseCaseReactivator.
     *
     * @param diagramPlaces stores each object of each diagram.
     * @param bottomRightContent stores panel with right content panel.
     * @param attributesPanel stores panel with new variables and methods panel.
     */
    public CDMainContentModel(DiagramPlacesManager diagramPlaces, BottomRightContentModel bottomRightContent, ClassDiagramAttributesPanel attributesPanel) {
        this.diagramPlaces = diagramPlaces;
        this.places = diagramPlaces.getCdPlaces();
        this.mainContent = new CDMainContent(this.places);
        this.bottomRightContent = bottomRightContent;
        this.attributesPanel = attributesPanel;
        this.useCaseConnector = new CDUseCaseConnector(diagramPlaces.getUcPlaces());
        this.useCaseReactivator = new CDUseCaseReactivator(useCaseConnector, diagramPlaces.getCdPlaces());
        createMainPane();
    }

    /**
     * Get left bottom content with buttons for setting line types.
     * @return CDLeftBottomContent.
     */
    public CDLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    /**
     * Get panel with buttons for setting which object should be added to drawing panel.
     * @return CDLeftTopContent
     */
    public CDLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    /**
     * Set panel with buttons for changing line types.
     * @param LeftBottomContent CDLeftBottomContent.
     */
    public void setLeftBottomContent(CDLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    /**
     * Set panel with buttons for changing which object should be added to class diagram panel.
     * @param LeftTopContent CDLeftTopContent.
     */
    public void setLeftTopContent(CDLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }

    /**
     * Create main pane with drawing on it and add listeners to it.
     */
    private void createMainPane() {
        CDDrawingPane UCdrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        DrawingListeners alpha = new DrawingListeners((DrawingClicks) this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
        setButtonsListeners();
    }

    /**
     * Set listeners to delete and esc keyboard buttons.
     */
    public void setButtonsListeners() {
        CDDrawingPane drawingPane = (CDDrawingPane) this.mainContent.getDrawingPane();
        drawingPane.getDrawing().getActionMap().put("removeObject", new AbstractAction() {
            CDDrawingPane drawingPane = (CDDrawingPane) mainContent.getDrawingPane();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (places.getSelectedObject() instanceof CDClass) {
                    diagramPlaces.removePnPlace(((CDClass) places.getSelectedObject()).getPnNetwork());
                }
                places.removeAllSelectedItems();
                deleteNewLine();
            }
        });

        drawingPane.getDrawing().getActionMap().put("selectionCanceled", new AbstractAction() {
            CDDrawingPane drawingPane = (CDDrawingPane) mainContent.getDrawingPane();

            @Override
            public void actionPerformed(ActionEvent e) {
                places.setAllObjectDiselected();
                LeftTopContent.setAllButtonsAvailable();
                LeftTopContent.setAllButtonsDiselected();
                LeftBottomContent.setAllButtonsAvailable();
                LeftBottomContent.setAllButtonsDiselected();
                drawingPane.getDrawing().repaint();
                bottomRightContent.showAdditionalContent(false);
            }
        });

        InputMap inputMap = drawingPane.getDrawing().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "selectionCanceled");
    }

    /**
     * Delete new line between objects.
     */
    protected void deleteNewLine() {
        this.newJoinEdge = null;
        this.mainContent.getDrawingPane().setNewLine(null);
        ((CDDrawingPane) this.mainContent.getDrawingPane()).getDrawing().repaint();
    }

    /**
     * Call this method when line is unjoined from second object. It will remove line from objects and create new line.
     * @param joinEdge CDJoinEdgeController join edge to be changed.
     */
    public void removeLineFromArrayListAndSetNewLine(CDJoinEdgeController joinEdge) {
        this.newJoinEdge = new CDJoinEdgeController();
        this.newJoinEdge.setFirstObject(joinEdge.getFirstObject());
        ((CDJoinEdgeController) this.newJoinEdge).setJoinEdgeType(joinEdge.getJoinEdgeType());
        if (this.LeftBottomContent.getSelectedButton() != null) {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (CDJoinEdgeController) this.newJoinEdge);
        }
        this.places.removeJoinEdge(joinEdge);
    }
}
