/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.export;

import BT.managers.DiagramPlacesManager;
import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import de.erichseifert.vectorgraphics2d.EPSGraphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;

/**
 * Class for exporting selected file to EPS.
 * It will take diagramPlacesManager and loop through every object and print it to file.
 * @author Karel
 */
public class ExportToEps extends ExportModel{    
    
    public ExportToEps(DiagramPlacesManager diagramPlaces, File exportToFolder)
    {
        super(diagramPlaces, exportToFolder);
    }
    
    public void exportAllToFiles()
    {
            try {
                if (this.exportedPlaces.getUcPlaces().getObjects().size()>0)
                {   
                    File ucPlacesFile = createNewFile(this.exportToFolder + "/UseCase", ".eps");
                    EPSGraphics2D ucGraphics = new EPSGraphics2D(0, 0, 800, 500);
//                    for (LineModel oneLine : this.exportedPlaces.getUcPlaces().getJoinEdges()) {
//                        ((UCJoinEdgeController) oneLine).drawJoinEdge(ucGraphics);
//                    }
                    for (CoordinateModel object : this.exportedPlaces.getUcPlaces().getObjects()) {
                        if (object instanceof UCActor)
                        {
                            ((UCActor) object).drawActor(ucGraphics);
                        }
                        else if (object instanceof UCUseCase)
                        {
                            ((UCUseCase) object).drawUseCase(ucGraphics);
                        }
                    }
                    try (PrintWriter ucOut = new PrintWriter(ucPlacesFile)) {
                        ucOut.print(ucGraphics.toString());
                    }
                }
                
                if (this.exportedPlaces.getCdPlaces().getObjects().size()>0)
                {  
                    File classDiagram = createNewFile(this.exportToFolder + "/ClassDiagram", ".eps");
                    EPSGraphics2D cdGraphics = new EPSGraphics2D(0, 0, 800, 500);
//                    for (LineModel oneLine : this.exportedPlaces.getCdPlaces().getJoinEdges()) {
//                        ((CDJoinEdgeController)oneLine).drawJoinEdge(cdGraphics);
//                    }
                    for (CoordinateModel object : this.exportedPlaces.getCdPlaces().getObjects()) {
                        if (object instanceof CDClass)
                        {
                            ((CDClass) object).drawClass(cdGraphics);
                        }
                    }
                    try (PrintWriter cdOut = new PrintWriter(classDiagram)) {
                        cdOut.print(cdGraphics.toString());
                        cdOut.close();
                    }
                }
                System.out.println(this.exportedPlaces.getPnPlaces().size());
                if (this.exportedPlaces.getPnPlaces().size()>0)
                {
                    for (PlaceManager onePetriNet : this.exportedPlaces.getPnPlaces()) {
                        File petriNetDiagram = createNewFile(this.exportToFolder + "/petriNet", ".eps");
                        EPSGraphics2D pnGraphics = new EPSGraphics2D(0, 0, 800, 500);
//                        for (LineModel oneLine : onePetriNet.getJoinEdges()) {
//                            ((PNJoinEdgeController)oneLine).drawJoinEdge(pnGraphics);
//                        }
                        for (CoordinateModel object : onePetriNet.getObjects()) {
                            if (object instanceof PNPlace)
                            {
                                ((PNPlace) object).drawPlace(pnGraphics);
                            }
                            else if (object instanceof PNTransition)
                            {
                                ((PNTransition) object).drawTransition(pnGraphics);
                            }
                        }
                        try (PrintWriter out = new PrintWriter(petriNetDiagram)) {
                            out.print(pnGraphics.toString());
                            out.close();
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println("error wile saving file");
            } catch (IOException ex) {
                Logger.getLogger(ExportToEps.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
