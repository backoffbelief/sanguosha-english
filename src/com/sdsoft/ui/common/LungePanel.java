package com.sdsoft.ui.common;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.ui.drivers.MainUI;

public class LungePanel extends JPanel {
	JButton start = new JButton("Start");
	JPanel centerPanel = new JPanel();
	Set<Player> players = new HashSet<Player>();
	private static final int playerCount = 8;

	public LungePanel(final boolean isHost) {
		this.setLayout(new BorderLayout());
		centerPanel.setLayout(new GridLayout(2, 4, 10, 10));
		this.add(centerPanel, BorderLayout.CENTER);
		final JPanel wrapper = new JPanel();
		wrapper.add(start);
		start.setEnabled(isHost);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				MainUI.getInstance().startGame();
			}
		});
		this.add(wrapper, BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				MainUI.getInstance().gameStart();
			}

		});
	}

	public void update(final List<Player> players) {
		centerPanel.removeAll();
		for (int i = 0; i < playerCount; i++) {
			if (i < players.size()) {
				final Player player = players.get(i);
				if (!this.players.contains(player)) {
					final WaitingLungePlayerPanel panel = new WaitingLungePlayerPanel(player);
					panel.setBorder(BorderFactory.createTitledBorder(""));
					centerPanel.add(panel);
				}
			} else {
				final JPanel panel = new JPanel();
				panel.setBorder(BorderFactory.createTitledBorder(""));
				centerPanel.add(panel);
			}
		}
		revalidate();
		repaint();
	}

}
