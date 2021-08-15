import java.awt.*;

public class MyRectangle extends MyBoundedShape{

	public MyRectangle(Point p1, Point p2, Color myColor, boolean myFill) {
		super(p1, p2, myColor, myFill); //use father constructor
	}


	/**
	 * method draw - paint an oval using fillRect/drawRect from d graphics
	 */
	@Override
	public void draw(Graphics d){
		d.setColor(this.getColor());
		if(this.getIsFill()){ //check if rectangle need to be full with color or not
			d.fillRect((int)this.getP1().getX(), (int)this.getP1().getY(), Math.abs((int)(this.getP2().getX() - this.getP1().getX())), Math.abs((int)(this.getP2().getY() - this.getP1().getY())));
		}
		else {
			d.drawRect((int)this.getP1().getX(), (int)this.getP1().getY(), Math.abs((int)(this.getP2().getX() - this.getP1().getX())), Math.abs((int)(this.getP2().getY() - this.getP1().getY())));
		}
	}
	/**
	 * method clone
	 * @throws CloneNotSupportedException
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException{
		MyRectangle cloned = (MyRectangle)super.clone();
		return cloned;
	}
	/**
	 * method equals
	 */
	@Override
	public boolean equals(Object obj){
		if((this instanceof MyRectangle) && (obj instanceof MyRectangle)){
			return super.equals(obj);
			}
		return false;
	}
}
