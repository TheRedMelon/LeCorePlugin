package com.nekomc.leCorePlugin.customEvents;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.nekomc.leCorePlugin.randomStuff.BoardSection;

public class PlayerBoardUpdateEvent extends Event {
	
	UUID id;
	BoardSection boardSection;
	
	public PlayerBoardUpdateEvent(UUID uuid, BoardSection bSection) {
		
		id = uuid;
		boardSection = bSection;
		
	}
	
	public UUID getPlayerUniqueId() {
		
		return id;
		
	}
	
	public BoardSection getBoardSection() {
		
		return boardSection;
		
	}
	
	private static final HandlerList handlers = new HandlerList();
	 
	public HandlerList getHandlers() {
		
	    return handlers;
	    
	}
	 
	public static HandlerList getHandlerList() {
		
	    return handlers;
	    
	}
	
}
