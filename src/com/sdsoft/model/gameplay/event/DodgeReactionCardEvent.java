package com.sdsoft.model.gameplay.event;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.model.gameplay.card.Card;

/**
 * A card event representing the reactive use of a dodge
 * 
 * @author Michael
 */
public class DodgeReactionCardEvent extends AbstractReactionCardEvent {
	
	public DodgeReactionCardEvent(Player sourcePlayer, Card cardUsed, AbstractActionCardEvent actionCardEvent) {
		this.sourcePlayer = sourcePlayer;
		this.cardUsed = cardUsed;
		this.actionCardEvent = actionCardEvent; 
	}

	@Override
	public void resolve() {
		//check skills
		
		//set action card event as negated. 
		//Presumably the dodge can only be played if it is to negate the action preceding it
		this.getActionCardEvent().setNegated(true);
	}
}
