package com.sdsoft.model.gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sdsoft.model.gameplay.card.CardDeck;

public class CentrolController {
	public enum Role {
		RULER,
		REBEL,
		SPY,
		LOYALIST,
	}

	public static final Role[] GAME_ROLE_SEQUENCE = new Role[] {
			Role.RULER,
			Role.SPY,
			Role.REBEL,
			Role.LOYALIST,
			Role.REBEL,
			Role.REBEL,
			Role.LOYALIST,
			Role.REBEL };

	public static final Role[] GAME_ROLE_SEQUENCE_DOUBLE_SPY = new Role[] {
			Role.RULER,
			Role.SPY,
			Role.REBEL,
			Role.LOYALIST,
			Role.REBEL,
			Role.REBEL,
			Role.SPY,
			Role.REBEL };

	List<Player> players = new ArrayList<Player>();
	List<Hero> heros = new ArrayList<Hero>();
	CardDeck deck = new CardDeck();
	Player ruler;

	public CentrolController(final List<Player> players, final boolean isDoubleSpy, final boolean isShuffleSeating) {
		this.players.addAll(players);
		if (isShuffleSeating) {
			Collections.shuffle(this.players);
		}
		final List<Role> roles = new ArrayList<Role>();
		final Role[] gameSequence = isDoubleSpy ? GAME_ROLE_SEQUENCE_DOUBLE_SPY : GAME_ROLE_SEQUENCE;
		for (int i = 0; i < this.players.size(); i++) {
			roles.add(gameSequence[i]);
		}

		Collections.shuffle(roles);

		for (int i = 0; i < this.players.size(); i++) {
			final Role role = roles.get(i);
			if (role.equals(Role.RULER)) {
				this.ruler = this.players.get(i);
			}
			this.players.get(i).setRole(role);
			this.players.get(i).setSeating(i);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}

	public List<Hero> getHeros() {
		return heros;
	}

	public void setHeros(final List<Hero> heros) {
		this.heros = heros;
	}

	public CardDeck getDeck() {
		return deck;
	}

	public void setDeck(final CardDeck deck) {
		this.deck = deck;
	}

	public Player getRuler() {
		return ruler;
	}

	public void setRuler(final Player ruler) {
		this.ruler = ruler;
	}

	public List<Hero> takeRulerHeros(final int numberOfCards) {
		return deck.takeRulerHeros(numberOfCards);
	}
}
