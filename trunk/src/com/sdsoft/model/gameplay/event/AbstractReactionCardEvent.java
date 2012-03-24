package com.sdsoft.model.gameplay.event;


/**
 * Abstract event representing the use of a reaction type card (e.g. Dodge)
 * 
 * @author Michael
 */
public abstract class AbstractReactionCardEvent extends AbstractCardEvent {
	protected AbstractActionCardEvent actionCardEvent;

	public AbstractActionCardEvent getActionCardEvent() {
		return actionCardEvent;
	}

	public void setActionCardEvent(AbstractActionCardEvent actionCardEvent) {
		this.actionCardEvent = actionCardEvent;
	}
}
