package com.sdsoft.ui.common;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sdsoft.ui.dialog.ConnectToHostDialog;
import com.sdsoft.ui.drivers.MainUI;

public class StartingPanel extends JPanel {
	private static final String BACKGROUND_PREFIX = ".\\res\\image\\system\\background\\bg";
	private int backgroundID = 0;
	private static final int ImageCount = 5;
	private Image image;
	JButton createServer = new JButton("Create Server");
	JButton joinServer = new JButton("Join Server");
	JButton settings = new JButton("Setting");
	JButton about = new JButton("About");

	public StartingPanel(final LayoutManager layout) {
		super(layout);
	}

	public void updateBackGround() {
		backgroundID = ((backgroundID + 1) % ImageCount);
		repaint();
	}

	@Override
	public void paintComponent(final Graphics g) {
		/* create image icon to get image */
		final ImageIcon imageicon = new ImageIcon(BACKGROUND_PREFIX + backgroundID + ".jpg");
		final Image image = imageicon.getImage();

		/* Draw image on the panel */
		super.paintComponent(g);

		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public StartingPanel() {
		super();
		super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 180));

		final JPanel panelbtn = new JPanel(new GridLayout(4, 1));

		createServer.setFont(new java.awt.Font("Showcard Gothic", 1, 20));
		joinServer.setFont(new java.awt.Font("Showcard Gothic", 1, 20));
		settings.setFont(new java.awt.Font("Showcard Gothic", 1, 20));
		about.setFont(new java.awt.Font("Showcard Gothic", 1, 20));

		this.add(panelbtn);

		panelbtn.add(createServer);
		panelbtn.add(joinServer);
		panelbtn.add(settings);
		panelbtn.add(about);

		// final ActionListener listener = new ActionListener() {
		//
		// @Override
		// public void actionPerformed(final ActionEvent arg0) {
		// updateBackGround();
		// }
		//
		// };

		createServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				MainUI.getInstance().createServerLungePanel("", 12345);
			}
		});
		joinServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				final ConnectToHostDialog dialog = new ConnectToHostDialog();
				dialog.setVisible(true);
				if (dialog.isOKPressed()) {
					MainUI.getInstance().createClientLungePanel(dialog.getIPAddress(), dialog.getPort(), dialog.getPlayerName());
				}

			}
		});
		// settings.addActionListener(listener);
		// about.addActionListener(listener);

	}

}
