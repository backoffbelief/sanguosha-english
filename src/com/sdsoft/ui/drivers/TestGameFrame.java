package com.sdsoft.ui.drivers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TestGameFrame extends JPanel {

	String imageFile = ".\\res\\image\\system\\background\\bg0.jpg";

	public TestGameFrame() {
		super();
	}

	public TestGameFrame(final String image) {
		super();
		this.imageFile = image;
	}

	public TestGameFrame(final LayoutManager layout) {
		super(layout);
	}

	@Override
	public void paintComponent(final Graphics g) {
		/* create image icon to get image */
		final ImageIcon imageicon = new ImageIcon(imageFile);
		final Image image = imageicon.getImage();

		/* Draw image on the panel */
		super.paintComponent(g);

		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
