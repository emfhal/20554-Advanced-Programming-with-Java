import java.awt.*;

public class MyLine extends MyShape {

	public MyLine(Point p1, Point p2, Color myColor) {
		super(p1, p2, myColor);
	}

	/**
	 * method getLength - checking the length of the line for equals method
	 */
	private double getLength() {
		int x1 = (int) this.getP1().getX();
		int y1 = (int) this.getP1().getY();
		int x2 = (int) this.getP2().getX();
		int y2 = (int) this.getP2().getY();

		double res = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return res;
	}

	/**
	 * draw method paint a line using setColor and drawLine from graphics
	 */
	@Override
	public void draw(Graphics d) {
		d.setColor(this.getColor());
		d.drawLine((int) this.getP1().getX(), (int) this.getP1().getY(), (int) this.getP2().getX(),
				(int) this.getP2().getY());
	}

	/**
	 * method clone
	 * 
	 * @throws CloneNotSupportedException
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyLine cloned = (MyLine) super.clone();
		return cloned;
	}

	/**
	 * equals method for MyLine
	 */
	@Override
	public boolean equals(Object obj) {
		if ((this instanceof MyLine) && (obj instanceof MyLine)) {
			if (((MyLine) this).getLength() == ((MyLine) obj).getLength()) {
				return true;
			}
		}
		return false;
	}
}
