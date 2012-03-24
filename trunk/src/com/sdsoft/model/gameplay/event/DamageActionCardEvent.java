package com.sdsoft.model.gameplay.event;

import java.util.List;

import com.sdsoft.model.gameplay.Player;
import com.sdsoft.model.gameplay.card.Card;
import com.sdsoft.model.gameplay.eventcontroller.EventController;

/**
 * Event representing direct damage being done.
 * 
 * @author Michael
 */
public class DamageActionCardEvent extends AbstractActionCardEvent {
	private int damage = 1;
	private DamageType damageType;

	/**
	 * Represents different types (sources) of damage.
	 * Add to it as appropriate.
	 * Will mostly be used in handling skill checks (e.g. 
	 * 
	 * @author Michael
	 */
	public enum DamageType {
		STRIKE,
		DIRECT,
	}
	
	public DamageActionCardEvent(Player source, Card cardUsed, Player target, int damage, DamageType damageType) {
		this.sourcePlayer = source;
		this.cardUsed = cardUsed;
		this.targets.add(target);
		this.damage = damage;
		this.damageType = damageType;
	}
	
	public DamageActionCardEvent(Player source, Card cardUsed, List<Player> targets, int damage, DamageType damageType) {
		this.sourcePlayer = source;
		this.cardUsed = cardUsed;
		this.targets = targets;
		this.damage = damage;
		this.damageType = damageType;
	}

	@Override
	public void resolve() {
		for (Player target : targets) {
			//check skills
			
			if (!this.negated) {
				EventController.getInstance().handleEvent(new DamageTakenReactionEvent(target, this, damage));
			}
		}
	}
}
