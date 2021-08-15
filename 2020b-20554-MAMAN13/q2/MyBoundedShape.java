import java.awt.Color;

//Declaration of class MyBoundedShape.
public abstract class MyBoundedShape extends MyShape {
    private boolean fillShape;// is shape filled

    // Constructor - default values
    public MyBoundedShape() {
	super();
	fillShape = false;
    }

    // Costructor - input values
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean fillShape) {
	super(x1, y1, x2, y2, color);
	this.fillShape = fillShape;
    }

    public void setFillShape(boolean fillShape) {
	this.fillShape = fillShape;
    }

    public boolean getFillShape() {
	return fillShape;
    }

    // Get upper left x coordinate
    protected int getTopX() {
	return Math.min(getX1(), getX2());
    }

    // Get upper left y coordinate
    protected int getTopY() {
	return Math.min(getY1(), getY2());
    }

    // Get shape width
    protected int getWidth() {
	return Math.abs(getX2() - getX1());
    }

    // Get shape height
    protected int getHeight() {
	return Math.abs(getY2() - getY1());
    }
}
