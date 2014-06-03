/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places;

import BT.models.CoordinateModel;
import BT.models.MyArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * Class for drawing use case of useCase diagram. Rectangle will be drawn arround object if it is selected.
 *
 * @author Karel Hala
 */
public class UCUseCase extends CoordinateModel {

    /**
     * Gap between use case and rectangle.
     */
    private final int gap;

    /**
     * Color of Rectangle arround use case.
     */
    private final Color outLineColor;

    /**
     * Basic constructor. It will create object on given coordinates. Sets selected color, width, height, basic color,
     * color, outline color, name, hower color. It also creates in joins and out joins as new objects of MyArrayList.
     *
     * @param x coordinate X of object.
     * @param y coordinate Y of object.
     */
    public UCUseCase(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.YELLOW;
        this.width = 60;
        this.height = 30;
        this.basicColor = Color.ORANGE;
        this.color = this.basicColor;
        this.outLineColor = Color.BLACK;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.howerColor = Color.green;
        this.inJoins = new MyArrayList<>();
        this.outJoins = new MyArrayList<>();
    }

    /**
     * Draw use case using Graphics2D. It draws rectangle arround use case if it is selected.
     * 
     * @param g Graphics2D for drawing these objects.
     */
    public void drawUseCase(Graphics2D g) {
        Color useCaseColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.width = fm.stringWidth(name) + this.textSize * 2;
        this.objectHeight = this.height + gap;
        this.objectWidth = this.width + gap;
        if (this.selected) {
            useCaseColor = selectedColor;
            drawRectArroundUseCase(g);
        }
        g.setStroke((this.inJoins.isEmpty() && this.outJoins.isEmpty()) ? this.dashedStroke : new BasicStroke(2));
        g.setColor(useCaseColor);
        g.fillOval(this.x - width / 2, y - height / 2, this.width, this.height);
        g.setColor(this.outLineColor);
        g.drawOval(this.x - width / 2, y - height / 2, this.width, this.height);
        g.setColor(Color.black);
        g.drawString(this.name, this.x - width / 2 + this.textSize, y + this.textSize / 2);
        drawNoObjectString(g, "/no class/");
    }

    /**
     * Draw rectangle arround use cases if this object is selected.
     * 
     * @param g Graphics2D for drawing rectangle.
     */
    public void drawRectArroundUseCase(Graphics2D g) {
        int borderWidth = getMax(this.objectWidth, this.width);
        int borderHeight = this.objectHeight;
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.black);
        g.drawRect(this.x - borderWidth / 2 - this.gap, this.y - this.height / 2 - this.gap, borderWidth + this.gap, borderHeight + this.gap);
    }
}
