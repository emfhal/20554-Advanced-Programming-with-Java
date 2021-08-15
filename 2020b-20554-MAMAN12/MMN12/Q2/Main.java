import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Main {

    public static void main(String[] args) {
		JFrame frame = new JFrame("The second question in MMN12");
		// Create MyPanel (part b in mmn12)
		JPanel p = new MyPanel();
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setVisible(true);
	}

}