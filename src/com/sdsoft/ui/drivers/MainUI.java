package com.sdsoft.ui.drivers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.sdsoft.model.communication.GameClient;
import com.sdsoft.model.communication.GameServer;
import com.sdsoft.ui.common.LungePanel;
import com.sdsoft.ui.common.StartingPanel;
import com.sdsoft.util.ImageUtil;

public class MainUI extends JFrame {

	private static final String STARTIG_NAME = "Starting";
	private static final String LUNGE_NAME = "Lunge";
	private static MainUI instance = null;
	JPanel centerPanel = new JPanel();
	CardLayout card = new CardLayout();
	private static final String BACKGROUND = ".\\res\\image\\system\\background\\staring_background.jpg";
	private StartingPanel startingPanel;
	private LungePanel lungePanel;
	GameServer server;

	public static MainUI getInstance() {
		if (instance == null) {
			instance = new MainUI();
		}

		return instance;
	}

	public StartingPanel getStartingPanel() {
		return startingPanel;
	}

	public LungePanel getLungePanel() {
		return lungePanel;
	}

	public MainUI() {
		super("Sanguosha");
		initFrame();
		initUI();
		setVisible(true);
	}

	private void initFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImageUtil.getImage(".\\res\\image\\sgs.png"));
		setSize(new Dimension(700, 525));
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(card);
		startingPanel = createStartingPanel();
		centerPanel.add(STARTIG_NAME, startingPanel);
		card.show(centerPanel, STARTIG_NAME);
		// setBackgroundImage(ImageUtil.getImage(BACKGROUND));
		// // card.show(centerPanel, STARTIG_NAME);
	}

	private StartingPanel createStartingPanel() {
		return new StartingPanel();
	}

	public void startGame() {

	}

	public void createServerLungePanel(final String name, final int port) {
		lungePanel = new LungePanel(true);
		centerPanel.add(LUNGE_NAME, lungePanel);
		card.show(centerPanel, LUNGE_NAME);
		server = new GameServer("testserver", 12345, 30, 8, true);
	}

	public void createClientLungePanel(final String serverAddress, final int serverPort, final String name) {
		lungePanel = new LungePanel(false);
		centerPanel.add(LUNGE_NAME, lungePanel);
		card.show(centerPanel, LUNGE_NAME);
		final GameClient client = new GameClient(serverPort, serverAddress, name);
	}

	public void gameStart() {
		if (server != null) {
			server.startGame();
		}
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainUI.getInstance();
			}
		});
	}

}
