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
 * Class for drawing actors of useCase diagram.
 *
 * @author Karel Hala
 */
public class UCActor extends CoordinateModel {

    /**
     * Gap between object and border.
     */
    private final int gap;

    /**
     * Basic constructor. It sets x and y coordinates to passed x and y. It also sets selected color, width, height,
     * basic color, color, name, textSize, fap, hower color, inJoins, outJoins.
     *
     * @param x coordinate X.
     * @param y coordinate Y.
     */
    public UCActor(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.selectedColor = Color.GREEN;
        this.width = 30;
        this.height = 60;
        this.basicColor = Color.BLUE;
        this.color = this.basicColor;
        this.name = "Default";
        this.textSize = 15;
        this.gap = 2;
        this.howerColor = Color.red;
        this.inJoins = new MyArrayList<>();
        this.outJoins = new MyArrayList<>();
    }

    /**
     * Method for fetchign maximum object width.
     *
     * @return
     */
    public int getMaxWidth() {
        return getMax(this.objectWidth, this.width);
    }

    /**
     * Method for fetchign maximum object height.
     *
     * @return
     */
    public int getMaxHeight() {
        return this.objectHeight;
    }

    /**
     * Method for drawing actors. It draws outline if actor is selected.
     *
     * @param g Graphics2D for drawing objects.
     */
    public void drawActor(Graphics2D g) {
        Color actorColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        this.objectHeight = this.getHeight() + textSize;
        this.objectWidth = this.getWidth() + fm.stringWidth(this.name) + gap - textSize;
        g.setColor(Color.white);
        g.fillRect(this.x - getMax(this.objectWidth, this.width) / 2 + this.gap, this.y - this.objectHeight / 2 + this.gap, getMax(this.objectWidth, this.width) - this.gap * 2, this.objectHeight - this.gap * 2);

        if (getSelected()) {
            drawRectArroundActor(g);
            actorColor = this.selectedColor;
        }

        int actorX = this.getX();
        int actorY = this.getY();
        g.setColor(actorColor);
        g.setStroke((this.inJoins.isEmpty() && this.outJoins.isEmpty()) ? this.dashedStroke : new BasicStroke(2));
        int middle = this.getHeight() / 2;
        int bottom = (middle / 2) - this.gap;
        int neck = bottom / 2;
        int arm = getWidth() / 2 - this.gap;
        int headSize = arm / 2;
        g.drawLine(actorX, actorY, actorX, actorY - neck - bottom);
        g.drawLine(actorX, actorY - bottom, actorX - arm, actorY - bottom);
        g.drawLine(actorX, actorY - bottom, actorX + arm, actorY - bottom);
        g.drawLine(actorX, actorY, actorX - arm, actorY + bottom);
        g.drawLine(actorX, actorY, actorX + arm, actorY + bottom);
        g.drawOval(actorX - neck, actorY - bottom - bottom - headSize, headSize * 2, headSize * 2);
        g.setColor(Color.black);
        g.drawString(this.name, actorX - fm.stringWidth(this.name) / 2, actorY + this.getHeight() / 2);
        drawNoObjectString(g, "/no class/");
    }

    /**
     * Draw rectangle if actor is selected.
     *
     * @param g Graphics2D to draw rectanle arround actor.
     */
    public void drawRectArroundActor(Graphics2D g) {
        int borderWidth = getMax(this.objectWidth, this.getWidth());
        int borderHeight = this.objectHeight;
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.black);
        g.drawRect(this.x - borderWidth / 2, this.y - borderHeight / 2, borderWidth, borderHeight);
    }
}
