package com.sdsoft.model.gameplay.event;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.model.gameplay.card.Card;

/**
 * Abstract event to represent a card being played by a player
 * 
 * @author Michael
 */
public abstract class AbstractCardEvent {
	protected Player sourcePlayer;
	protected Card cardUsed;
	protected boolean negated = false;
	
	protected Player getSource() {
		return sourcePlayer;
	}
	
	protected void setSource(Player source) {
		this.sourcePlayer = source;
	}
	
	protected Card getCard() {
		return cardUsed;
	}
	
	protected void setCard(Card card) {
		this.cardUsed = card;
	}
	
	public void setNegated(boolean negated) {
		this.negated = negated;
	}
	
	public boolean getNegated() {
		return negated;
	}

	public abstract void resolve();
}
