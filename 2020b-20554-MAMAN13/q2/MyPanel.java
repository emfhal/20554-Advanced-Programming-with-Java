import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JFrame implements ActionListener, ItemListener {
    /**
     * In this class we will use the Jframe with the required buttons This is
     * actually the first step of creating the application
     */

    private static final long serialVersionUID = 1L;
    private DrawPanel drawPanel = new DrawPanel();
    private JRadioButton rbLine = new JRadioButton("Line");
    private JRadioButton rbOval = new JRadioButton("Oval");
    private JRadioButton rbRectangle = new JRadioButton("Rectangle");
    private JRadioButton rbRoundRectangle = new JRadioButton("Round Rectangle");
    private JCheckBox chkFillShape = new JCheckBox("Fill Shape");
    private JButton btnSelectColor = new JButton("Select Color");
    private JButton btnUndo = new JButton("Undo");
    private JButton btnClear = new JButton("Clear");
    private JButton btnExit = new JButton("Exit");

    // To work cleaner we didn't call a Jframe from the main class that we created a
    // class with the buttons and the layout and broder
    public MyPanel() {
	super("Shapes - The second question in MMN13");
	setLayout(new BorderLayout());
	initBTN();
	add(drawPanel, BorderLayout.CENTER);

	setSize(600, 600);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Init the GUI for buttons and others (radio and checkbox)
    private void initBTN() {
	JPanel panelBottom = new JPanel();
	panelBottom.setLayout(new GridLayout(1, 2));

	// set radio and checkbox
	JPanel panelRadio = new JPanel();
	panelRadio.setLayout(new GridLayout(5, 1));
	ButtonGroup group = new ButtonGroup();
	group.add(rbLine);
	group.add(rbOval);
	group.add(rbRectangle);
	group.add(rbRoundRectangle);
	rbRectangle.setSelected(true);
	rbLine.addItemListener(this);
	rbOval.addItemListener(this);
	rbRectangle.addItemListener(this);
	rbRoundRectangle.addItemListener(this);
	chkFillShape.addItemListener(this);
	panelRadio.add(rbLine);
	panelRadio.add(rbOval);
	panelRadio.add(rbRectangle);
	panelRadio.add(rbRoundRectangle);
	panelRadio.add(chkFillShape);
	drawPanel.setFillShape(chkFillShape.isSelected());
	setShapeByRadioSelection();
	panelBottom.add(panelRadio);

	// Set buttons of undo cleade and exit and select color
	JPanel panelButtons = new JPanel();
	panelButtons.setLayout(new GridLayout(4, 1));
	panelButtons.add(btnSelectColor);
	panelButtons.add(btnUndo);
	panelButtons.add(btnClear);
	panelButtons.add(btnExit);
	btnSelectColor.addActionListener(this);
	btnUndo.addActionListener(this);
	btnClear.addActionListener(this);
	btnExit.addActionListener(this);

	panelBottom.add(panelButtons);
	add(panelBottom, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnSelectColor)
	    onSelectColor();
	else if (e.getSource() == btnUndo)
	    onUndo();
	else if (e.getSource() == btnClear)
	    onClear();
	else if (e.getSource() == btnExit)
	    onExit();
    }

    public void itemStateChanged(ItemEvent e) {
	if (e.getSource() == chkFillShape) // set fill shape by checkbox
	{
	    drawPanel.setFillShape(chkFillShape.isSelected());
	} else // set parameters by radio buttons
	{
	    chkFillShape.setEnabled(e.getSource() != rbLine);
	    setShapeByRadioSelection();
	}
    }

    // setting the shapes type of draw by the radio button
    private void setShapeByRadioSelection() {
	if (rbLine.isSelected())
	    drawPanel.setShapeType(MyShape.Type.LINE);
	else if (rbOval.isSelected())
	    drawPanel.setShapeType(MyShape.Type.OVAL);
	else if (rbRectangle.isSelected())
	    drawPanel.setShapeType(MyShape.Type.RECTANGLE);
	else if (rbRoundRectangle.isSelected())
	    drawPanel.setShapeType(MyShape.Type.ROUND_RECTANGLE);
    }

    // Events of clicking the select color undo and clear and exit
    private void onSelectColor() {
	Color color = JColorChooser.showDialog(this, "Select the color of the shape", drawPanel.getColor());
	drawPanel.setColor(color);
    }

    private void onUndo() {
	drawPanel.Undo();
	repaint();
    }

    private void onClear() {
	drawPanel.Clear();
	repaint();
    }

    private void onExit() {
	System.exit(0);
    }
}
