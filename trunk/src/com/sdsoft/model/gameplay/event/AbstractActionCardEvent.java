package com.sdsoft.model.gameplay.event;

import java.util.ArrayList;
import java.util.List;

import com.sdsoft.model.gameplay.Player;

/**
 * Abstract event representing an action type card (e.g. Strike)
 * 
 * @author Michael
 */
public abstract class AbstractActionCardEvent extends AbstractCardEvent {
	/**
	 * Targets is a list of players who the action targets. This may be empty but should
	 * never be null
	 */
	protected List<Player> targets = new ArrayList<Player>();

	
	protected List<Player> getTargets() {
		return targets;
	}

	
	protected void setTargets(List<Player> targets) {
		this.targets = targets;
	}
}
