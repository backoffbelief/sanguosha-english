package com.sdsoft.model.gameplay.event;

import java.util.List;


public class CardEventKnowledgeBase {
	private static List<Class<? extends AbstractCardEvent>> cardEventTypes;
	
	public static List<Class<? extends AbstractCardEvent>> getCardEventTypes() {
		if (cardEventTypes == null) {
			populateCardEventTypes();
		}
		return cardEventTypes;
	}

	private static void populateCardEventTypes() {
		//Action
		cardEventTypes.add(StrikeActionCardEvent.class);
		
		//Reaction
		cardEventTypes.add(DodgeReactionCardEvent.class);
	}
}
