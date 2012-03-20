package com.sdsoft.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.util.ImageUtil;

public class WaitingLungePlayerPanel extends JPanel {
	Player player;
	private static String imagePath = ".\\res\\image\\system\\card-back.png";
	private final Image playerImage;

	public WaitingLungePlayerPanel(final Player player) {
		this.player = player;
		playerImage = ImageUtil.getImage(imagePath);
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (playerImage != null) {
			g.drawImage(playerImage, 0, 0, getWidth(), getHeight(), this);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			g.drawString(player.getName(), getWidth() / 2 - (player.getName().length() * 5), 40);
		}

	}
}
