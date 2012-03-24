package com.sdsoft.model.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdsoft.model.gameplay.CentrolController;
import com.sdsoft.model.gameplay.CentrolController.Role;
import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Player;
import com.sdsoft.ui.drivers.MainUI;

public class GameServer {
	private final String serverName;
	private final int port;
	private final int timeDelay;
	private final int playerCount;
	private final int playerOnline = 0;
	private int lordId;
	private int currentId;
	private final static int RulerCardCount = 3;
	private final Map<Integer, Player> idToPlayerMap = new HashMap<Integer, Player>();
	private int id = 0;
	private final boolean isShuffleSeating;
	private final List<Player> players = new ArrayList<Player>();
	private CentrolController controller;
	private final GameServerMonitor monitor;
	private boolean gameStarted = false;

	// private final Player me;

	public GameServer(final String serverName, final int port, final int timeDelay, final int playerCount, final boolean isShuffleSeating) {
		this.serverName = serverName;
		this.port = port;
		this.timeDelay = timeDelay;
		this.playerCount = playerCount;
		this.isShuffleSeating = isShuffleSeating;
		// me = new Player("", -1, serverName, id);
		// players.add(me);
		MainUI.getInstance().getLungePanel().update(players);
		id++;
		monitor = new GameServerMonitor(this);
		monitor.start();
	}

	public void chat(final SGSPacket packet) {
		if (packet.getToPlayerId() == null) {
			System.out.println("mass message: " + packet.getMessage());
		}
	}

	public void playerConnect(final SGSPacket packet, final String playerAddress, final int port) {
		synchronized (players) {
			if (players.size() == playerCount) {
				return;
			}
			final Player player = new Player(playerAddress, port, packet.getPlayerName(), id);
			idToPlayerMap.put(id++, player);
			players.add(player);
			MainUI.getInstance().getLungePanel().update(players);
			for (final Player entity : players) {
				final SGSPacket update = new SGSPacket(Action.UpdatePlayers);
				update.setPlayers(new ArrayList<Player>(players));
				send(update, entity);
			}
		}
	}

	public void startGame() {
		synchronized (players) {
			gameStarted = true;
			controller = new CentrolController(players, false, isShuffleSeating);
			final List<Player> players = new ArrayList<Player>(controller.getPlayers());
			for (final Player player : players) {
				final SGSPacket packet = new SGSPacket(Action.GameStart);
				packet.setPlayers(players);
				packet.setPlayer(player);
				send(packet, player);
				if (player.getRole().equals(Role.RULER)) {
					final SGSPacket rulerPacket = new SGSPacket(Action.PickRulerHero);
					final List<Hero> heros = new ArrayList<Hero>(controller.takeRulerHeros(RulerCardCount));
					rulerPacket.setHeros(heros);
					send(rulerPacket, player);
				}
			}
		}
	}

	public void unregisterPlayer(final String ip, final int port) {
		Player target = null;
		for (final Player player : players) {
			if (player.getAddress().equalsIgnoreCase(ip) && player.getPort() == port) {
				target = player;
				break;
			}
		}
		assert target != null : "Cannot find player to unregister";
		if (!gameStarted) {
			players.remove(target);
			MainUI.getInstance().getLungePanel().update(players);
			for (final Player entity : players) {
				final SGSPacket update = new SGSPacket(Action.UpdatePlayers);
				update.setPlayers(new ArrayList<Player>(players));
				send(update, entity);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void pickRulerHeroFinished(final SGSPacket packet) {
		System.out.println("ruler finished");
	}

	public String getServerName() {
		return serverName;
	}

	public int getPort() {
		return port;
	}

	private void send(final SGSPacket packet, final Player player) {
		// if (player != me) {
		monitor.send(packet, player.getAddress(), player.getPort());
		// }
	}

}
