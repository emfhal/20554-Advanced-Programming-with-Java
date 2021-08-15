import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyPanel extends JPanel {

	private final static int MAXNUM = 200; // maximum value (x/y)
	private final static int MOVE = 10; // move (x/y) + move value
	private final static Color colorBefore = Color.RED;
	private final static Color colorAfter = Color.GREEN;

	private final ArrayList<MyShape> list; // original list
	private final ArrayList<MyShape> listClone; // cloned list

	public MyPanel() {
		super();
		list = new ArrayList<MyShape>();
		listClone = new ArrayList<MyShape>();
		final Random rand = new Random();

		// 2 shape for each diffrent shape (2 * 3 = 6)
		for (int i = 0; i < 6; i++) { 
			if(i % 3 == 0){//add 2 line shape
				list.add(new MyLine(new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)),
				new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)), colorBefore));
			}else if(i % 3 == 1){ //add 2 rectangle shape
				list.add(new MyRectangle(new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)),
				new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)), colorBefore, true));
			}else{ //add 2 oval shapes
				list.add(new MyOval(new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)),
				new Point(rand.nextInt(MAXNUM), rand.nextInt(MAXNUM)), colorBefore, true));
			}
		} // clone the list and add to the cloned list
		for (int i = 0; i < list.size(); i++) {
			try {
				listClone.add((MyShape) list.get(i).clone());
			} catch (final CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		changeDuplicate(); // change event (paint green and setIsFill to not fill to the dublicate shapes )
	}



	private void changeDuplicate() { //changeDuplicate listColne + MOVE and setColor
		for (int i = 0; i < listClone.size(); i++) {
			if (listClone.get(i) instanceof MyBoundedShape) {
				final MyBoundedShape temp = (MyBoundedShape) listClone.get(i);
				temp.setIsFIll(!(temp.getIsFill()));
			}

			final MyShape t = listClone.get(i);
			t.setP1(new Point((int) t.getP1().getX() + MOVE, (int) t.getP1().getY() + MOVE));
			t.setP2(new Point((int) t.getP2().getX() + MOVE, (int) t.getP2().getY() + MOVE));
			t.setColor(colorAfter);

		}
	}

	public void paintComponent(final Graphics s) { //paintComponent (draw) graphics s method
		super.paintComponent(s);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).draw(s);
			listClone.get(i).draw(s);
		}

	}

}