import java.awt.*;

public abstract class MyShape implements Cloneable {

	private Point point1; 
	private Point point2;
	private Color color; 

	public MyShape(Point p1, Point p2, Color myColor){
		point1 = p1;
		point2 = p2;
		color = myColor;
	}
	
	
	/**
	 * get and set point Point1 and Point2 and color shape
	 */

	public Point getP1(){
		Point p = new Point(point1);
		return  p;
	}
	public Point getP2(){
		Point p = new Point(point2);
		return p;
	}

	public Color getColor(){
		return color;
	}

	public void setP1(Point p){
		point1 = new Point (p);
	}

	public void setP2(Point p){
		point2 = new Point(p);
	}

	public void setColor(Color myColor){
		color = myColor;
	}
	
	public abstract void draw(Graphics d);

	public abstract boolean equals(Object obj);

	/**
	 * method clone()
	 * @throws CloneNotSupportedException 
	 */

	protected Object clone() throws CloneNotSupportedException{
		MyShape cloned = (MyShape)super.clone(); // downcasting
		cloned.setP1(new Point(this.getP1())); //set the clone point 1 
		cloned.setP2(new Point(this.getP2()));// set the clone point 2 
		return cloned;
		
	}
	
}
