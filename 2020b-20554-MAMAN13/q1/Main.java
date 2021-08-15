import java.io.FileNotFoundException;
import javax.swing.*;

/**
* This is the main class to run the exam application from myPanel class for Q1 in MMN13 
* @author Emmanuel Fhal
*/

public class Main {

	public static void main(String[] args) throws FileNotFoundException {		
		JFrame frame = new JFrame("Exams - The first question in MMN13"); 

		MyPanel panel = new MyPanel();
		frame.add(panel);
		
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

}
