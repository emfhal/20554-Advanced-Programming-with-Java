import java.awt.Color;
import java.awt.Graphics;

/**
 * In this class we will define and work on the shape of single line
 */

public class MyLine extends MyShape {
    // Constructor - default values
    public MyLine() {
	super();
    }

    // Costructor - input values
    public MyLine(int x1, int y1, int x2, int y2, Color color) {
	super(x1, y1, x2, y2, color);
    }

    // Draw the line in the specified color requested
    public void draw(Graphics g) {
	g.setColor(getColor());
	g.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}
