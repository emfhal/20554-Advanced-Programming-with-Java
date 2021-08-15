import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    /**
     * In this class is the next step after definitions of shapes where we define
     * the drawing options of the shapes on the panel
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<MyShape> shapes = new ArrayList<MyShape>();
    private MyShape dynamicShape = null;
    private MyShape.Type dynamicType = MyShape.Type.ROUND_RECTANGLE;
    private boolean dynamicFill = false;
    private Color dynamicColor = Color.BLACK;

    // Creates a new instance of DrawPanel
    public DrawPanel() {
	super();

	// Mouse event (listener)
	addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
		dynamicShape = MyShape.getShape(dynamicType);
		if (dynamicShape instanceof MyBoundedShape)
		    ((MyBoundedShape) dynamicShape).setFillShape(dynamicFill);
		dynamicShape.setColor(dynamicColor);
		dynamicShape.setX1(e.getPoint().x);
		dynamicShape.setY1(e.getPoint().y);
	    }

	    public void mouseReleased(MouseEvent e) {
		dynamicShape.setX2(e.getPoint().x);
		dynamicShape.setY2(e.getPoint().y);
		shapes.add(dynamicShape);
		dynamicShape = null;
		repaint();
	    }
	});

	// Mouse event ( motion listener )
	addMouseMotionListener(new MouseMotionAdapter() {
	    public void mouseDragged(MouseEvent e) {
		dynamicShape.setX2(e.getPoint().x);
		dynamicShape.setY2(e.getPoint().y);
		repaint();
	    }
	});
    }

    // Setting shapes type and others( fill shape and color.. )
    public void setShapeType(MyShape.Type type) {
	dynamicType = type;
    }

    public MyShape.Type getShapeType() {
	return dynamicType;
    }

    public void setFillShape(boolean fillShape) {
	dynamicFill = fillShape;
    }

    public boolean getFillShape() {
	return dynamicFill;
    }

    public void setColor(Color color) {
	dynamicColor = color;
    }

    public Color getColor() {
	return dynamicColor;
    }

    // Undo the last shape
    public void Undo() {
	Iterator<MyShape> it;
	for (it = shapes.iterator(); it.hasNext(); it.next())
	    ;
	it.remove();
    }

    // Clear all the shapes
    public void Clear() {
	shapes.clear();
    }

    // Override method for paintingComponent
    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	for (Object shapeObj : shapes) {
	    MyShape shape = (MyShape) shapeObj;
	    shape.draw(g);
	}

	// If dynamic shapes exist draw it
	if (dynamicShape != null)
	    dynamicShape.draw(g);
    }
}
