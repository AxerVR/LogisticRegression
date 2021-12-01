package logistic.controller;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import logistic.agent.LRAgent;

/**
  @author Axel Villanueva Rodríguez.
			@TecMM Campus Zapopan
 */
public class LRGui extends JFrame {	
	private LRAgent myAgent;
	
	private JTextField inputField;
	
	public LRGui(LRAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(10, 2));
		p.add(new JLabel("Logistic regression:"));
		p.add(new JLabel(""));
		p.add(new JLabel("Inputs: "));
		inputField = new JTextField(15);
		p.add(inputField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton simpleLRButton = new JButton("Simple");
		simpleLRButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String input = inputField.getText().trim();
					if(!input.equals("")) myAgent.simpleLR(input);
					else System.out.println("Empty input.");
					inputField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(LRGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		JButton multipleLRButton = new JButton("Multiple");
		multipleLRButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String input = inputField.getText().trim();
					if(!input.equals("")) myAgent.multipleLR(input);
					else System.out.println("Empty input.");
					inputField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(LRGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(simpleLRButton);
		p.add(multipleLRButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
