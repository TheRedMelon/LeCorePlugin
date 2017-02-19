package com.nekomc.leCorePlugin.customEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GeneralScoreboardUpdateEvent extends Event {
	
	public GeneralScoreboardUpdateEvent() {
		
		
		
	}
	
	private static final HandlerList handlers = new HandlerList();
	 
	public HandlerList getHandlers() {
		
	    return handlers;
	    
	}
	 
	public static HandlerList getHandlerList() {
		
	    return handlers;
	    
	}
	
}
