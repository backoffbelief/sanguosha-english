package com.sdsoft.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame extends JFrame {
	private final JButton btn1 = new JButton("EASY");
	private final JButton btn2 = new JButton("MEDIUM");
	private final JButton btn3 = new JButton("HARD");
	private final JButton btn4 = new JButton("High Score");

	public TestFrame() {
		super("Image Panel Demo");

		/* Create a penel to add the button panel */
		final JPanel panel = new TestGameFrame(new FlowLayout(FlowLayout.CENTER, 50, 180));

		/* this button panel is to add in the buttons */
		final JPanel panelbtn = new JPanel(new GridLayout(4, 1));

		/* Add the button to the button panel */
		/* these buttons are customized font and colour */
		btn1.setBackground(new java.awt.Color(0, 0, 0));
		btn1.setFont(new java.awt.Font("Showcard Gothic", 1, 24));
		btn1.setForeground(new java.awt.Color(0, 255, 102));
		btn2.setBackground(new java.awt.Color(0, 0, 0));
		btn2.setFont(new java.awt.Font("Showcard Gothic", 1, 24));
		btn2.setForeground(new java.awt.Color(0, 255, 102));
		btn3.setBackground(new java.awt.Color(0, 0, 0));
		btn3.setFont(new java.awt.Font("Showcard Gothic", 1, 24));
		btn3.setForeground(new java.awt.Color(0, 255, 102));
		btn4.setBackground(new java.awt.Color(0, 0, 0));
		btn4.setFont(new java.awt.Font("Showcard Gothic", 1, 24));
		btn4.setForeground(new java.awt.Color(0, 255, 102));

		panel.add(panelbtn);

		panelbtn.add(btn1);
		panelbtn.add(btn2);
		panelbtn.add(btn3);
		panelbtn.add(btn4);

		/* Add the panel to the frame */
		add(panel, BorderLayout.CENTER);

		/* set size of the frame */
		setSize(640, 480);

		/* close operation */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(final String... args) {
		new TestFrame().setVisible(true);
	}
}
