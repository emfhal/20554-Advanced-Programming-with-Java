import java.awt.Color;
import java.awt.Graphics;

//Declaration of class MyShape.
public abstract class MyShape {
    enum Type {
	LINE, OVAL, RECTANGLE, ROUND_RECTANGLE
    };

    private int x1; // x coordinate of first endpoint
    private int y1; // y coordinate of first endpoint
    private int x2; // x coordinate of second endpoint
    private int y2; // y coordinate of second endpoint
    private Color myColor; // color of the shape

    // Constructor - default values
    public MyShape() {
	this(0, 0, 0, 0, Color.BLACK);
    }

    // Constructor - input values
    public MyShape(int x1, int y1, int x2, int y2, Color color) {
	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	myColor = color;
    }

    public static MyShape getShape(MyShape.Type type) {
	// Switch between 4 different shape types
	switch (type) {
	case LINE:
	    return new MyLine();
	case OVAL:
	    return new MyOval();
	case RECTANGLE:
	    return new MyRectangle();
	case ROUND_RECTANGLE:
	    return new MyRoundRectangle();
	default:
	    return new MyRectangle();
	}
    }

    // set the x-coordinate of the first point
    public void setX1(int x1) {
	this.x1 = (x1 >= 0 ? x1 : 0);
    }

    // get the x-coordinate of the first point
    public int getX1() {
	return x1;
    }

    public void setX2(int x2) {
	this.x2 = (x2 >= 0 ? x2 : 0);
    }

    public int getX2() {
	return x2;
    }

    // set the y-coordinate of the first point
    public void setY1(int y1) {
	this.y1 = (y1 >= 0 ? y1 : 0);
    }

    // get the y-coordinate of the first point
    public int getY1() {
	return y1;
    }

    public void setY2(int y2) {
	this.y2 = (y2 >= 0 ? y2 : 0);
    }

    public int getY2() {
	return y2;
    }

    // set the color of the shape
    public void setColor(Color color) {
	myColor = color;
    }

    // get the color of the shape
    public Color getColor() {
	return myColor;
    }

    public abstract void draw(Graphics g);
}
