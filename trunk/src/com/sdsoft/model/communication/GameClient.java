package com.sdsoft.model.communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Player;
import com.sdsoft.ui.MainUI;
import com.sdsoft.ui.SelectHeroDialog;

public class GameClient {

	public String getServerAddress() {
		return serverAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

	private static Logger log = Logger.getLogger(GameClient.class);

	private final String serverAddress;
	private final int serverPort;
	private final String name;
	private final GameClientMonitor clientMonitor;
	// private GameFrame gameFrame;
	private int playerCount;
	private int lordId;
	private Player me;
	public List<Player> players;

	public GameClient(final int serverPort, final String serverAddress, final String name) {
		this.serverPort = serverPort;
		this.serverAddress = serverAddress;
		this.name = name;
		clientMonitor = new GameClientMonitor(this);
		clientMonitor.start();
		// connect();
	}

	public String getName() {
		return name;
	}

	private void send(final SGSPacket packet) {
		// UDPSender.send(packet, serverAddress, serverPort);
		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		// SGSPacket talk = new SGSPacket(Action.SendClientMessage);
		// talk.setMessage("hello word");
		// UDPSender.send(talk, serverAddress, serverPort);
	}

	public void updatePlayers(final SGSPacket packet) {
		// System.out.println(this.name + "updating player");
		final List<Player> players = new ArrayList<Player>(packet.getPlayers());
		MainUI.getInstance().getLungePanel().update(players);
		for (final Player player : players) {
			// System.out.println(player);
		}
	}

	public void gameStart(final SGSPacket packet) {
		me = packet.getPlayer();
		this.players = new ArrayList<Player>(packet.getPlayers());

		// final TestGameFrame frame = new TestGameFrame(players, me);

	}

	public void pickRulerHero(final SGSPacket packet) {
		final List<Hero> heros = new ArrayList<Hero>(packet.getHeros());
		new SelectHeroDialog(me, heros);
		heros.remove(0);
		// final SGSPacket response = new
		// SGSPacket(Action.PickRulerHeroFinished);
		// response.setHeros(heros);
		// send(packet);
	}

	public void pickHero(final List<Hero> heros) {
		new SelectHeroDialog(me, heros);
	}

	//
}
