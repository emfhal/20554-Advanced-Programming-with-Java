import java.awt.Color;
import java.awt.Graphics;

/**
 * In this class we will define and work on the shape of the rectangle
 */

public class MyRectangle extends MyBoundedShape {
    // Constructor - default values
    public MyRectangle() {
	super();
    }

    // Constructor - input values
    public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean fillShape) {
	super(x1, y1, x2, y2, color, fillShape);
    }

    // Draw rectangle via the input parameters
    public void draw(Graphics g) {
	g.setColor(getColor());
	if (getFillShape())
	    g.fillRect(getTopX(), getTopY(), getWidth(), getHeight());
	else
	    g.drawRect(getTopX(), getTopY(), getWidth(), getHeight());
    }
}
