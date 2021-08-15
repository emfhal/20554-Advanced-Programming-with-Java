import java.awt.*;

public class MyOval extends MyBoundedShape{

	public MyOval(Point p1 , Point p2 , Color myColor , boolean myFill){
		super(p1 , p2 , myColor , myFill); 
	}

	/**
	 * method draw - paint an oval using fillOval/drawOval from d graphics
	 */
	@Override
	public void draw(Graphics d){
		d.setColor(this.getColor());
		if(this.getIsFill()){ //check if the oval need to be full with color or not
			d.fillOval((int)this.getP1().getX(), (int)this.getP1().getY(), Math.abs((int)(this.getP2().getX() - this.getP1().getX())), Math.abs((int)(this.getP2().getY() - this.getP1().getY())));
		}
		else {
			d.drawOval((int)this.getP1().getX(), (int)this.getP1().getY(), Math.abs((int)(this.getP2().getX() - this.getP1().getX())), Math.abs((int)(this.getP2().getY() - this.getP1().getY())));
		}
	}


	/**
	 * method clone
	 * @throws CloneNotSupportedException
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException{
		MyOval cloned = (MyOval)super.clone();
		return cloned;
	}
	/**
	 * method equals
	 */
	@Override
	public boolean equals(Object obj){
		if((this instanceof MyOval) && (obj instanceof MyOval)){
			return super.equals(obj);
			}
		return false;
	}
}
