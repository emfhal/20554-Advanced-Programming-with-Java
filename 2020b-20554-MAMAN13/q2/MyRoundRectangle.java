import java.awt.Color;
import java.awt.Graphics;

/**
 * In this class we will define and work on the shape of the rounded rectangle
 */

// this class represents a round rectangle shape
public class MyRoundRectangle extends MyRectangle {
    private int arcWidth;// the horizontal diameter of the arc at the 4 corners
    private int arcHeight;// the vertical diameter of the arc at the 4 corners
    private final static int ARC_DEFAULT_SIZE = 20;

    // Constructor - default values
    public MyRoundRectangle() {
	super();
	arcWidth = ARC_DEFAULT_SIZE;
	arcHeight = ARC_DEFAULT_SIZE;
    }

    // Constructor - input values
    public MyRoundRectangle(int x1, int y1, int x2, int y2, int arcWidth, int arcHeight, Color color,
	    boolean fillShape) {
	super(x1, y1, x2, y2, color, fillShape);
	this.arcWidth = arcWidth;
	this.arcHeight = arcHeight;
    }

    public void setArcWidth(int arcWidth) {
	this.arcWidth = arcWidth;
    }

    public int getArcWidth() {
	return arcWidth;
    }

    public void setArcHeighth(int arcHeight) {
	this.arcHeight = arcHeight;
    }

    public int getArcHeight() {
	return arcHeight;
    }

    // Draw rectangle shape via the input parameters
    public void draw(Graphics g) {
	g.setColor(getColor());
	if (getFillShape())
	    g.fillRoundRect(getTopX(), getTopY(), getWidth(), getHeight(), getArcWidth(), getArcHeight());
	else
	    g.drawRoundRect(getTopX(), getTopY(), getWidth(), getHeight(), getArcWidth(), getArcHeight());
    }
}
