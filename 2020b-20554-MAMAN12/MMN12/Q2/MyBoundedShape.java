import java.awt.*;

public abstract class MyBoundedShape extends MyShape{

	private boolean isFill; //if the bounded shape fill or not

	public MyBoundedShape(Point p1 , Point p2 , Color myColor , boolean myFill) throws IllegalArgumentException{
		super(p1, p2, myColor);
		isFill = myFill;
	}
	/**
	 * Return boolean that say if shape is fill or not
	 * @return boolean - true for fill shape false for not
	 */
	public boolean getIsFill(){
		return isFill;
	}
	public void setIsFIll(boolean myFill){
		isFill = myFill;
	}

	public void setP1(Point p){
		super.setP1(p);
	}

	public void setP2(Point p){
		super.setP2(p);
	}

	/**
	 * method clone
	 * @throws CloneNotSupportedException
	 */
	protected Object clone() throws CloneNotSupportedException{
		MyBoundedShape cloned = (MyBoundedShape)super.clone();
		cloned.setIsFIll(this.getIsFill());
		return cloned;
	}
	/**
	 * equals method for MyBoundedShap
	 * 
	 */
	@Override
	public boolean equals(Object obj){
		double thisWidth = Math.abs(this.getP2().getX() - this.getP1().getX());
		double objWidth = Math.abs(((MyBoundedShape)obj).getP2().getX() - ((MyBoundedShape)obj).getP1().getX());
		double thisHeight = Math.abs(this.getP2().getY() - this.getP1().getY());
		double objHeight = Math.abs(((MyBoundedShape)obj).getP2().getY() - ((MyBoundedShape)obj).getP1().getY());
		if(thisWidth == objWidth && thisHeight == objHeight){
			return true;
		}
		return false;
	}
	/**
	 * abstract method draw(Graphics d)
	 */
	public abstract void draw(Graphics d);
	
}
