package com.sdsoft.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class SGSButton extends JButton {

	public SGSButton(final String text) {
		super(text);
		// setOpaque(false);
		// setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(150, 20));
	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
	}

}
