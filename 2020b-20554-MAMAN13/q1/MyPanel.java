import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MyPanel extends JPanel {

    /**
     * In this class we will use the JPanel with the required buttons the myExam
     * reading exam.txt file and returing diffrent questions by suffles answer (4
     * diffrent answer for each question)
     */

    private static final long serialVersionUID = 1L;
    private JRadioButtonMenuItem[] answers;
    private JButton next;
    private JButton yes;
    private JButton no;

    private Scanner input;
    private boolean lastPage;
    private boolean repeat;

    private String correctAnswer;
    private JLabel reExam;
    private JLabel question;

    private int i;
    private ArrayList<String> questionsList;

    private int numGoodAnswers;
    private int numQuestions;

    // First event call myExam function to start the exam
    public MyPanel() throws FileNotFoundException {
	myExam();
    }

    // myExam function by reading exam.txt file (in eclipse in src folder)
    private void myExam() throws FileNotFoundException {

	numGoodAnswers = numQuestions = i = 0;
	correctAnswer = "";
	repeat = false;
	questionsList = new ArrayList<String>();
	reExam = new JLabel("Try again?");

	input = new Scanner(new File("src/exam.txt"));
	answers = new JRadioButtonMenuItem[4];
	question = new JLabel("Question");
	next = new JButton("Next");
	yes = new JButton("Yes");
	no = new JButton("No");

	// Layout and listener for Jcompounets
	setLayout(new GridLayout(6, 2, 5, 5));
	add(question);
	ButtonListener lis = new ButtonListener();

	yes.addActionListener(lis);
	no.addActionListener(lis);
	next.addActionListener(lis);

	for (int i = 0; i < answers.length; i++) {
	    answers[i] = new JRadioButtonMenuItem();

	}
	for (int i = 0; i < answers.length; i++) {
	    answers[i].addActionListener(lis);
	    add(answers[i]);
	}
	add(next);
	update();
    }

    public void update() {

	// Update question
	String[] QA = new String[5];
	if (input.hasNextLine()) {
	    for (int i = 0; i < QA.length; i++) {
		QA[i] = input.nextLine();
	    }
	    questionsList.add(QA[0]);
	    numQuestions++;
	    correctAnswer = QA[1];
	    // calling shuffle function for mixing the order
	    suffle(QA);
	}

	lastPage = !input.hasNextLine();
	if (lastPage && !repeat) {
	    next.setText("Cheak the answers");
	}

	for (int i = 1; i < QA.length; i++) {
	    answers[i - 1].setText(QA[i]);
	}
	if (QA[0] != null)
	    question.setText(QA[0]);

    }

    // Suffle the order of the questions by using rand.nextInt evey question have 4
    // difrrent answer
    private void suffle(String[] ans) {
	Random rand = new Random();
	int des = rand.nextInt(3) + 1;
	for (int i = 1; i < ans.length; i++) {
	    String temp = ans[i];
	    ans[i] = ans[des];
	    ans[des] = temp;
	    des = rand.nextInt(3) + 1;
	}

    }

    // lisener for button
    private class ButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == no) {
		// Incase the player have enoght and want to exit the test
		input.close();
		System.exit(0);
	    }
	    if (e.getSource() == yes) {
		// Incase the plyer want to play agin
		reExam.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		removeAll();

		try {
		    myExam();
		} catch (FileNotFoundException e1) {
		    e1.printStackTrace();
		}

	    }
	    if (e.getSource() == next) { // Next question
		if (repeat && !lastPage) {
		    next.setText("Next");
		    if (i < 2 * numQuestions) {
			String q = questionsList.get(i);// The lined ot the question is always i
			String a = questionsList.get(i + 1);// The right answer is always i+1 (the first anser in
							    // exma.txt)
			question.setText(q + " - " + a);
			i += 2;
		    } else {
			repeat = false;
			lastPage = false;

			question.setVisible(false);
			next.setVisible(false);
			setLayout(new FlowLayout());
			add(reExam);
			add(yes);
			add(no);
		    }

		}

		if ((oneIsSelected() != -1)) {
		    if (correctAnswer == answers[oneIsSelected()].getText()) {
			numGoodAnswers++;
			questionsList.add("Correct");// right anser
		    } else {
			questionsList.add("Not Correct");// wrong anser
		    }
		    // Incase we finish with the question and its the last page so the user will get
		    // check button
		    if (lastPage) {
			repeat = true;
			next.setVisible(true);
			next.setText("Check ansnswers");

			for (int i = 0; i < answers.length; i++) {
			    answers[i].setVisible(false);
			}

			setLayout(new BorderLayout());

			question.setText(("You exam is over - you answer to " + numGoodAnswers
				+ " right answers, Your final grade is: "
				+ (((double) numGoodAnswers) / numQuestions) * 100));

			add(question, BorderLayout.NORTH);
			lastPage = false;

		    } else {
			update();
			for (int i = 0; i < answers.length; i++) {
			    answers[i].setSelected(false);
			}
		    }
		}
	    }

	    for (int i = 0; i < answers.length; i++) {
		if (e.getSource() == answers[i]) {

		    for (int j = 0; j < answers.length; j++) {
			if (i != j) {
			    answers[j].setSelected(false);
			} else {
			    answers[i].setSelected(true);
			}
		    }
		}
	    }
	}

	// Cheaking if any answer was selecting - if yest return the nubmer/i of the
	// answer
	private int oneIsSelected() {

	    for (int i = 0; i < answers.length; i++) {
		if (answers[i].isSelected() && !repeat) {
		    return i;
		}
	    }
	    return -1;
	}

    }
}
