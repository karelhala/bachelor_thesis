/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.BT;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;

/**
 *
 * @author Karel
 */
public class CDUseCaseConnector {
    private CoordinateModel selectedModel;
    private LineModel newline;
    private final PlaceManager ucPlaces;
    
    public CDUseCaseConnector(PlaceManager UCPlaces)
    {
        this.ucPlaces = UCPlaces;
    }

    public CoordinateModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(CoordinateModel selectedModel) {
        this.selectedModel = selectedModel;
    }

    public LineModel getNewline() {
        return newline;
    }

    public void setNewline(LineModel newline) {
        this.newline = newline;
    }

    public PlaceManager getUcPlaces() {
        return ucPlaces;
    }
    
    /**
     * Method that creates new actor or use case from new class. 
     * It will check if type of class has been changed and if so, it will remove propriete use case from
     * use case diagram and create new one. If new class was created create appropriate object in use case.
     * @param useCaseName name of created use case
     */
    public void createNewUseCaseObject(String useCaseName)
    {
        CDClass selectedClass = (CDClass) selectedModel;
        if (selectedClass.getTypeOfClass()!=BT.ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            if (selectedClass.getTypeOfClass()==BT.ClassType.ACTOR && !(selectedClass.getAssignedObject() instanceof UCActor))
            {
                ucPlaces.removePlace(selectedClass.getAssignedObject());
                createNewActor();
            }
            else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTIVITY && !(selectedClass.getAssignedObject() instanceof UCUseCase))
            {
                ucPlaces.removePlace(selectedClass.getAssignedObject());
                createNewUseCase();
            }
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTOR && selectedClass.getAssignedObject() == null)
        {
            createNewActor();
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.ACTIVITY && selectedClass.getAssignedObject() == null)
        {
            createNewUseCase();
            selectedClass.getAssignedObject().setName(useCaseName);
        }
        else if (selectedClass.getTypeOfClass()==BT.ClassType.NONE && selectedClass.getAssignedObject() != null)
        {
            selectedClass.getAssignedObject().setAssignedObject(selectedClass);
            selectedClass.setAssignedObject(selectedClass);
        }
        else
        {
            selectedClass.setAssignedObject(selectedClass);
        }
    }
    
    /**
     * Method for creating new actor and inserting him in use case diagram.
     */
    private void createNewActor()
    {
        CDClass selectedClass = (CDClass) this.selectedModel;
        UCActor newActor = new UCActor(selectedClass.getX(), selectedClass.getY());
        newActor.setName(this.selectedModel.getName());
        ucPlaces.addObject(newActor);
        selectedClass.setAssignedObject(newActor);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }
    
    /**
     * Method for creating new use case and inserting it in use case diagram.
     */
    private void createNewUseCase()
    {
        CDClass selectedClass = (CDClass) this.selectedModel;
        UCUseCase newUsecase = new UCUseCase(selectedClass.getX(), selectedClass.getY());
        newUsecase.setName(this.selectedModel.getName());
        ucPlaces.addObject(newUsecase);
        selectedClass.setAssignedObject(newUsecase);
        selectedClass.getAssignedObject().setAssignedObject(selectedClass);
    }
    
    /**
     * Method for creating new joins between associated objects in use case.
     */
    public void createNewUseCaseJoin()
    {
        UCJoinEdgeController newUseCaseLine = new UCJoinEdgeController(this.newline.getFirstObject().getAssignedObject(), this.newline.getSecondObject().getAssignedObject());
        CDJoinEdgeController cdJoin = (CDJoinEdgeController) this.newline;
        if (cdJoin.getJoinEdgeType() == BT.CDLineType.ASSOCIATION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.GENERALIZATION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.GENERALIZATION);
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.AGGREGATION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.USERINPUT);
        }
        else if (cdJoin.getJoinEdgeType() == BT.CDLineType.COMPOSITION)
        {
            newUseCaseLine.setJoinEdgeType(BT.UCLineType.USERINPUT);
        }
        if (this.newline.getFirstObject().getAssignedObject() != null && this.newline.getSecondObject().getAssignedObject() != null)
        {
            this.newline.getFirstObject().getAssignedObject().addOutJoins(newUseCaseLine);
            this.newline.getSecondObject().getAssignedObject().addInJoin(newUseCaseLine);
        }
        newUseCaseLine.setAssignedObject(cdJoin);
        cdJoin.setAssignedObject(newUseCaseLine);
        this.ucPlaces.addObject(newUseCaseLine);
    }
}
