package com.sdsoft.model.gameplay.event;

import java.util.List;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.model.gameplay.card.Card;
import com.sdsoft.model.gameplay.event.DamageActionCardEvent.DamageType;
import com.sdsoft.model.gameplay.eventcontroller.EventController;

/**
 * Event representing the use of a Strike.
 * 
 * @author Michael
 */
public class StrikeActionCardEvent extends AbstractActionCardEvent {
	private int damage = 1;

	public StrikeActionCardEvent(Player source, Card cardUsed, Player target, int damage) {
		this.sourcePlayer = source;
		this.cardUsed = cardUsed;
		this.targets.add(target);
		this.damage = damage;
	}
	
	public StrikeActionCardEvent(Player source, Card cardUsed, List<Player> targets, int damage) {
		this.sourcePlayer = source;
		this.cardUsed = cardUsed;
		this.targets = targets;
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public void resolve() {
		for (Player target : targets) {
			//check applicable skills

			//request dodge
			if (!negated) {
				DamageActionCardEvent damageEvent = new DamageActionCardEvent(sourcePlayer, cardUsed, target, damage, DamageType.STRIKE);
				EventController.getInstance().handleEvent(damageEvent);
			}
		}
	}
}
