package com.sdsoft.ui.gameplay;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.util.UIUtil;

public class PlayerLabel extends JPanel {
	private final Player player;
	private int x;
	private int y;
	private final int orientSeating;
	private final int totalPlayer;

	public PlayerLabel(final Player player, final int orientSeating, final int totalPlayer) {
		this.player = player;
		this.orientSeating = orientSeating;
		this.totalPlayer = totalPlayer;
		initUI();
	}

	private void initUI() {
		this.setLayout(new BorderLayout());
		JPanel wrapper = new JPanel();
		final JLabel northLabel = new JLabel();
		northLabel.setText(player.getName());
		wrapper.add(northLabel);
		this.add(wrapper, BorderLayout.NORTH);

		wrapper = new JPanel();
		final JLabel centerLabel = new JLabel();
		final String text = "role: " + player.getRole() + "<br>" + player.getCurrentHealth() + "/" + player.getMaximumHealth();
		centerLabel.setText(UIUtil.getHtmlString(text));
		wrapper.add(centerLabel);
		this.add(wrapper, BorderLayout.CENTER);
		wrapper = new JPanel();
		final JLabel south = new JLabel("seat: " + player.getSeating());
		wrapper.add(south);
		this.add(wrapper, BorderLayout.SOUTH);
		setSize(100, 200);
		calculatePosition();
	}

	private void calculatePosition() {
		int targetSeating = player.getSeating() + (player.getSeating() < orientSeating ? totalPlayer : 0);
		targetSeating -= orientSeating;
		switch (targetSeating) {
		case 0:
			x = 450;
			y = 800;
			break;
		case 1:
			x = 600;
			y = 600;
			break;
		case 2:
			x = 750;
			y = 400;
			break;
		case 3:
			x = 600;
			y = 200;
			break;
		case 4:
			x = 450;
			y = 0;
			break;
		case 5:
			x = 300;
			y = 200;
			break;
		case 6:
			x = 150;
			y = 400;
			break;
		case 7:
			x = 300;
			y = 600;
			break;
		}
	}

	public int getXpos() {
		return x;
	}

	public void setXpos(final int x) {
		this.x = x;
	}

	public int getYpos() {
		return y;
	}

	public void setYpos(final int y) {
		this.y = y;
	}
}
