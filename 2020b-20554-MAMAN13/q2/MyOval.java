import java.awt.Color;
import java.awt.Graphics;

/**
 * In this class we will define and work on the shape of the oval
 */

public class MyOval extends MyBoundedShape {
    // Constructor - default values
    public MyOval() {
	super();
    }

    // Costructor - input values
    public MyOval(int x1, int y1, int x2, int y2, Color color, boolean fillShape) {
	super(x1, y1, x2, y2, color, fillShape);
    }

    // Draw oval via the input parameters
    public void draw(Graphics g) {
	g.setColor(getColor());
	if (getFillShape())
	    g.fillOval(getTopX(), getTopY(), getWidth(), getHeight());
	else
	    g.drawOval(getTopX(), getTopY(), getWidth(), getHeight());
    }
}
