package Calculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel[] row = new JPanel[5];
	JButton[] button = new JButton[19];
	String[] buttonString = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2",
			"3", "*", ".", "/", "C", "√", "+/-", "=", "0" };
	int[] dimensionWidth = { 300, 45, 100, 90 };
	int[] dimHeighт = { 35, 40 };
	Dimension displayDimension = new Dimension(dimensionWidth[0], dimHeighт[0]);
	Dimension regularDimension = new Dimension(dimensionWidth[1], dimHeighт[1]);
	Dimension rColumnDimension = new Dimension(dimensionWidth[2], dimHeighт[1]);
	Dimension zeroButDimension = new Dimension(dimensionWidth[3], dimHeighт[1]);
	boolean[] function = new boolean[4];
	double[] temporary = { 0, 0 };
	JTextArea display = new JTextArea(1, 15);
	Font font = new Font("Times new Roman", Font.BOLD, 20);
	

	Calculator() {
		super("Calculator");
		setSize(350, 250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(5, 5);
		setLayout(grid);
		
		
		for (int i = 0; i < 4; i++)
			function[i] = false;

		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
		for (int i = 0; i < 5; i++)
			row[i] = new JPanel();
		row[0].setLayout(f1);
		for (int i = 1; i < 5; i++)
			row[i].setLayout(f2);

		for (int i = 0; i < 19; i++) {
			button[i] = new JButton();
			button[i].setForeground(Color.MAGENTA);
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
			
		}
		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension);

		for (int i = 0; i < 14; i++)
			button[i].setPreferredSize(regularDimension);

		for (int i = 14; i < 18; i++)
		button[i].setPreferredSize(rColumnDimension);
		button[18].setPreferredSize(zeroButDimension);
		row[0].add(display);
		add(row[0]);

		for (int i = 0; i < 4; i++)
			row[1].add(button[i]);
		row[1].add(button[14]);
		add(row[1]);

		for (int i = 4; i < 8; i++)
			row[2].add(button[i]);
		row[2].add(button[15]);
		add(row[2]);

		for (int i = 8; i < 12; i++)
			row[3].add(button[i]);
		row[3].add(button[16]);
		add(row[3]);
		row[4].add(button[18]);

		for (int i = 12; i < 14; i++)
			row[4].add(button[i]);
		row[4].add(button[17]);
		add(row[4]);
		setVisible(true);
	}

	public void clear() {
		display.setText("");
		for (int i = 0; i < 4; i++)
			function[i] = false;
		for (int i = 0; i < 2; i++)
			temporary[i] = 0;
	}

	public void getSqrt() {
		double value = Math.sqrt(Double.parseDouble(display.getText()));
		display.setText(Double.toString(value));
	}

	public void getPosNeg() {
		double value = Double.parseDouble(display.getText());
		if (value != 0) {
			value = value * (-1);
			display.setText(Double.toString(value));
		} else {
		}
	}

	public void getResult() {
		double result = 0;
		temporary[1] = Double.parseDouble(display.getText());
		String temp0 = Double.toString(temporary[0]);
		String temp1 = Double.toString(temporary[1]);

		if (temp0.contains("-")) {
			String[] temp00 = temp0.split("-", 2);
			temporary[0] = (Double.parseDouble(temp00[1]) * -1);
		}
		if (temp1.contains("-")) {
			String[] temp11 = temp1.split("-", 2);
			temporary[1] = (Double.parseDouble(temp11[1]) * -1);
		}
		if (function[2] == true)
			result = temporary[0] * temporary[1];
		else if (function[3] == true)
			result = temporary[0] / temporary[1];
		else if (function[0] == true)
			result = temporary[0] + temporary[1];
		else if (function[1] == true)
			result = temporary[0] - temporary[1];
		display.setText(Double.toString(result));
		for (int i = 0; i < 4; i++)
			function[i] = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0])
			display.append("7");
		if (e.getSource() == button[1])
			display.append("8");
		if (e.getSource() == button[2])
			display.append("9");
		if (e.getSource() == button[3]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		if (e.getSource() == button[4])
			display.append("4");
		if (e.getSource() == button[5])
			display.append("5");
		if (e.getSource() == button[6])
			display.append("6");
		if (e.getSource() == button[7]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		if (e.getSource() == button[8])
			display.append("1");
		if (e.getSource() == button[9])
			display.append("2");
		if (e.getSource() == button[10])
			display.append("3");
		if (e.getSource() == button[11]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		if (e.getSource() == button[12])
			display.append(".");
		if (e.getSource() == button[13]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[3] = true;
			display.setText("");
		}
		if (e.getSource() == button[14])
			clear();
		if (e.getSource() == button[15])
			getSqrt();
		if (e.getSource() == button[16])
			getPosNeg();
		if (e.getSource() == button[17])
			getResult();
		if (e.getSource() == button[18])
			display.append("0");
	}

	public static void main(String[] arguments) {
		Calculator c = new Calculator();
	}
}