package com.sdsoft.model.gameplay.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sdsoft.model.gameplay.Hero;
import com.sdsoft.model.gameplay.Skill;

public class CardDeck {
	List<Card> drawDeck = new ArrayList<Card>();
	List<Card> discardDeck = new ArrayList<Card>();
	List<Hero> heroDeck = new ArrayList<Hero>();
	List<Hero> discardHeroDeck = new ArrayList<Hero>();

	Hero liubei = new Hero(4, 4, new ArrayList<Skill>(), "Liu Bei", "res\\image\\generals\\small\\liubei.png", "res\\image\\generals\\big\\liubei.png",
			"res\\image\\generals\\card\\liubei.jpg");
	Hero sunquan = new Hero(4, 4, new ArrayList<Skill>(), "Liu Bei", "res\\image\\generals\\small\\sunquan.png", "res\\image\\generals\\big\\sunquan.png",
			"res\\image\\generals\\card\\sunquan.jpg");
	Hero caocao = new Hero(4, 4, new ArrayList<Skill>(), "Liu Bei", "res\\image\\generals\\small\\caocao.png", "res\\image\\generals\\big\\caocao.png",
			"res\\image\\generals\\card\\caocao.jpg");

	public CardDeck() {
		populateStartingDeck();
		populateCards();
	}

	private void populateStartingDeck() {

	}

	private void populateCards() {
		assert !drawDeck.isEmpty() : "should not shuffle deck now";
		drawDeck.addAll(discardDeck);
		discardDeck.clear();
		Collections.shuffle(drawDeck);
	}

	public List<Card> takeNumberOfCardsFromDrawDeck(int number) {
		final List<Card> result = new ArrayList<Card>();
		while (number > 0) {
			if (drawDeck.isEmpty()) {
				populateCards();
			}
			result.add(drawDeck.remove(0));
			number--;
		}
		return result;
	}

	public void putCardsToTopOfDrawDeck(final List<Card> cards) {
		for (final Card card : cards) {
			drawDeck.add(0, card);
		}
	}

	public void putCardsToBottomOfDrawDeck(final List<Card> cards) {
		drawDeck.addAll(cards);
	}

	public void discardCards(final List<Card> cards) {
		discardDeck.addAll(cards);
	}

	public List<Hero> takeRulerHeros(final int numberOfCards) {
		final List<Hero> result = new ArrayList<Hero>();
		int counter = numberOfCards - 3;
		result.add(liubei);
		result.add(sunquan);
		result.add(caocao);
		heroDeck.remove(liubei);
		heroDeck.remove(sunquan);
		heroDeck.remove(caocao);
		while (counter > 0) {
			result.add(heroDeck.remove(0));
			counter--;
		}
		return result;
	}

	public void putHeroBackAndShuffle(final List<Hero> heros) {
		heroDeck.addAll(heros);
		Collections.shuffle(heros);
	}

	public List<Hero> takeHeros(final int numberOfCards) {
		final List<Hero> result = new ArrayList<Hero>();
		final int counter = numberOfCards;
		while (counter > 0) {
			result.add(heroDeck.remove(0));
		}
		return result;
	}

	public void returnHeros(final List<Hero> heros) {
		discardHeroDeck.addAll(heros);
	}
}
