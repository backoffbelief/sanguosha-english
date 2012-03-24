package com.sdsoft.model.gameplay.eventcontroller;

import java.util.ArrayDeque;
import java.util.Deque;

import com.sdsoft.model.gameplay.event.AbstractCardEvent;

public class EventController {
	private static EventController instance;
	private Deque<AbstractCardEvent> eventStack = new ArrayDeque<AbstractCardEvent>();
	
	public static synchronized EventController getInstance() {
		if (instance == null) {
			instance = new EventController();
		}
		return instance;
	}
	
	public void handleEvent(AbstractCardEvent event) {
		eventStack.push(event);
		event.resolve();
	}
	
}
