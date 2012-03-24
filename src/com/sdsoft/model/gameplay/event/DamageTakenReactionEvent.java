package com.sdsoft.model.gameplay.event;

import com.sdsoft.model.gameplay.Player;


/**
 * Event representing damage being taken
 * 
 * @author Michael
 */
public class DamageTakenReactionEvent extends AbstractReactionCardEvent {
	private int damage = 1;
	
	public DamageTakenReactionEvent(Player sourcePlayer, AbstractActionCardEvent actionCardEvent, int damage) {
		this.sourcePlayer = sourcePlayer;
		this.actionCardEvent = actionCardEvent;
		this.damage = damage;
	}
	
	@Override
	public void resolve() {
		//set new health
		sourcePlayer.setCurrentHealth(sourcePlayer.getCurrentHealth() - damage);
		
		//check death status
		if (sourcePlayer.getCurrentHealth() <= 0) {
			//TODO: BrinkOfDeathEvent
		}
		//if the player has been saved continue; otherwise do nothing
		if (sourcePlayer.getCurrentHealth() > 0) {
			//check skills
		}
	}
}
