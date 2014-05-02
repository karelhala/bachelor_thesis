/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Karel
 */
public class PNPlace extends PNPlaceModel {

    /**
     *
     * @param x
     * @param y
     */
    public PNPlace(int x, int y) {
        super(x, y);
    }

    /**
     * TODO: refactor
     *
     * @param g
     */
    public void drawPlace(Graphics2D g) {
        Color placeColor = this.color;
        g.setFont(new Font("Arial", Font.BOLD, this.textSize));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        if (getSelected()) {
            placeColor = this.selectedColor;
        }
        g.setStroke((this.inJoins.isEmpty() && this.outJoins.isEmpty()) ? this.dashedStroke : new BasicStroke(2));
        g.setColor(Color.BLACK);
        setObjectHeight(fm).setObjectWidth(fm).drawConstant(g, fm);
        g.drawString(name, x - this.width / 2, y + this.height / 2 + fm.getHeight() + 2);

        g.setColor(placeColor);
        g.drawOval(x - this.width / 2, y - this.height / 2, this.width, this.height);
    }

    /**
     *
     * @param fm
     * @return
     */
    protected PNPlace setObjectHeight(FontMetrics fm) {
        int objectTall = 15;
        if (this.constant != null && !"".equals(this.constant)) {
            objectTall += fm.getHeight() + 5;
        }

        this.height = objectTall;
        return this;
    }

    /**
     *
     * @param fm
     * @return
     */
    protected PNPlace setObjectWidth(FontMetrics fm) {
        this.width = 30;
        if (this.constant != null && !"".equals(this.constant)) {
            this.width += fm.stringWidth(this.constant);
            this.width += 5;
        }
        return this;
    }

    /**
     *
     * @param g
     * @param fm
     * @return
     */
    protected PNPlace drawConstant(Graphics2D g, FontMetrics fm) {
        if (this.constant != null)
        {
            g.drawString(this.constant, x - fm.stringWidth(this.constant) / 2, this.y + fm.getHeight() / 2 - 4);
        }
        return this;
    }

    /**
     * Get all variables as string joined with ",".
     * @return allVariables String.
     */
    public String getVariablesAsString() {
        String allVariables = "";
        for (String oneVariable : this.variables) {
            if (oneVariable != null) {
                if (oneVariable.equals(this.variables.getFirst())) {
                    allVariables += "(";
                }

                allVariables += oneVariable;

                if (oneVariable.equals(this.variables.getLast())) {
                    allVariables += ")";
                } else {
                    allVariables += ", ";
                }
            }
        }
        return allVariables;
    }
}
