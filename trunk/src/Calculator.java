import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculator extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//ints the GUI components so you can call on them
	private JTextField NumberOneField;
	private JTextField NumberTwoField;
	private JLabel NumberOneTitle;
	private JLabel NumberTwoTitle;
	private JLabel MainTitle;
	private JButton SumNumbersButton;
	private JLabel SumNumbersDisplayLable;
	
	static int numberOne = 0;
	static int numberTwo = 0;	
	static int sumNumbers;
	
	String sumString;
	
	static	Calculator gui;
	
	public static void main2() {		
		gui = new Calculator();
		gui.setVisible(true);
	}

	public Calculator() {
		//Loads all the components
		initComponents();
	}

	private void SumNumbersButtonActionPerformed(ActionEvent e) {
		numberOne = Integer.parseInt(NumberOneField.getText());
		numberTwo = Integer.parseInt(NumberTwoField.getText());
		sumNumbers = (numberOne + numberTwo);
		sumString = String.valueOf(sumNumbers);
		SumNumbersDisplayLable.setText(sumString);
	}

	private void initComponents() {
		//Importing GUI Components
		NumberOneField = new JTextField();
		NumberTwoField = new JTextField();
		NumberOneTitle = new JLabel();
		NumberTwoTitle = new JLabel();
		MainTitle = new JLabel();
		SumNumbersButton = new JButton();
		SumNumbersDisplayLable = new JLabel();
		/*
		JMenu jmenu = new JMenu("File");
		JMenuItem calc = new JMenuItem("Quadratic Solver");
		  calc.addActionListener(new ActionListener() {
		         public void actionPerformed (ActionEvent e) {
		        	 JPanelExample.main(null);
					gui.setVisible(false);

		          }
		       });
jmenu.add(calc);
*/
		//======== this ========
		setTitle("Calculator!");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.add(NumberOneField);
		NumberOneField.setBounds(60, 80, 135, NumberOneField.getPreferredSize().height);
		contentPane.add(NumberTwoField);
		NumberTwoField.setBounds(205, 80, 135, 20);

		//---- NumberOneTitle ----
		NumberOneTitle.setText("Number One");
		contentPane.add(NumberOneTitle);
		NumberOneTitle.setBounds(90, 55, 80, 14);

		//---- NumberTwoTitle ----
		NumberTwoTitle.setText("Number Two");
		contentPane.add(NumberTwoTitle);
		NumberTwoTitle.setBounds(240, 55, 165, 14);

		//---- MainTitle ----
		MainTitle.setText("Calculator!");
		MainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		MainTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(MainTitle);
		MainTitle.setBounds(20, 5, 355, 45);

		//---- SumNumbersButton ----
		SumNumbersButton.setText("Sum Numbers!");
		SumNumbersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SumNumbersButtonActionPerformed(e);
			}
		});
		contentPane.add(SumNumbersButton);
		SumNumbersButton.setBounds(115, 120, 170, 35);
		SumNumbersDisplayLable.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(SumNumbersDisplayLable);
		SumNumbersDisplayLable.setBounds(115, 150, 170, 50);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
	}
}