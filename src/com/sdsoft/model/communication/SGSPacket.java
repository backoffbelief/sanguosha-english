package com.sdsoft.model.communication;

import java.util.List;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Player;
import com.sdsoft.model.gameplay.CentrolController.Role;

public class SGSPacket {
	private int clientPort;

	private String playerName;
	private int playerCount;
	private int playerId;
	private Integer toPlayerId;
	private int lordId;
	private Action action;
	private Role role = Role.RULER;
	private String message;
	private int messageToID;
	private int handCardCount;
	private List<Player> players;
	private List<Hero> heros;
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public List<Hero> getHeros() {
		return heros;
	}

	public void setHeros(final List<Hero> heros) {
		this.heros = heros;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}

	public int getLordId() {
		return lordId;
	}

	public void setLordId(final int lordId) {
		this.lordId = lordId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public void setToPlayerId(final Integer toPlayerId) {
		this.toPlayerId = toPlayerId;
	}

	public void setAction(final Action action) {
		this.action = action;
	}

	public SGSPacket() {
	}

	public boolean checkAction(final Action action) {
		return this.action.equals(action);
	}

	public SGSPacket(final Action action) {
		this.action = action;
	}

	public SGSPacket(final Action action, final int clientPort) {
		this(action);
		this.clientPort = clientPort;
	}

	public SGSPacket(final Action action, final String playerName, final int clientPort) {
		this(action, clientPort);
		this.playerName = playerName;
	}

	public int getHandCardCount() {
		return handCardCount;
	}

	public void setHandCardCount(final int handCardCount) {
		this.handCardCount = handCardCount;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(final int clientPort) {
		this.clientPort = clientPort;
	}

	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(final int playerCount) {
		this.playerCount = playerCount;
	}

	// public PlayerVO[] getPlayers() {
	// return players;
	// }
	//
	// public void setPlayers(PlayerVO[] players) {
	// this.players = players;
	// }
	//
	// public void setPlayers(Player[] players) {
	// this.players = new PlayerVO[players.length];
	// for (int i = 0; i < players.length; ++i) {
	// if (players[i] != null) {
	// this.players[i] = new PlayerVO(players[i]);
	// }
	// }
	// }
	//
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	//
	public int getMessageToID() {
		return messageToID;
	}

	public void setMessageToID(final int messageToID) {
		this.messageToID = messageToID;
	}

	//
	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }
	//
	// public int getLordId() {
	// return lordId;
	// }
	//
	// public void setLordId(int lordId) {
	// this.lordId = lordId;
	// }
	//
	// public CharacterVO[] getCharacterVOs() {
	// return characterVOs;
	// }
	//
	// public void setCharacterVOs(CharacterVO[] characterVOs) {
	// this.characterVOs = characterVOs;
	// }
	//
	// public CharacterVO getCharacterVO() {
	// return characterVO;
	// }
	//
	// public void setCharacterVO(CharacterVO characterVO) {
	// this.characterVO = characterVO;
	// }
	//
	// public CardVO[] getCardVOs() {
	// return cardVOs;
	// }
	//
	// public void setCardVOs(CardVO[] cardVOs) {
	// this.cardVOs = cardVOs;
	// }
	//
	// public Phase getPhase() {
	// return phase;
	// }
	//
	// public void setPhase(Phase phase) {
	// this.phase = phase;
	// }
	//
	// public CardVO getCardVO() {
	// return cardVO;
	// }
	//
	// public void setCardVO(CardVO cardVO) {
	// this.cardVO = cardVO;
	// }
	//
	public Integer getToPlayerId() {
		return toPlayerId;
	}

	//
	// public void setToPlayerId(int toPlayerId) {
	// this.toPlayerId = toPlayerId;
	// }
	//
	// public CardVO getAnotherCardVO() {
	// return anotherCardVO;
	// }
	//
	// public void setAnotherCardVO(CardVO anotherCardVO) {
	// this.anotherCardVO = anotherCardVO;
	// }

	public Action getAction() {
		return action;
	}

}
