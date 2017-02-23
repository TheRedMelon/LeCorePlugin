package com.nekomc.leCorePlugin.customEvents;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.nekomc.leCorePlugin.randomStuff.BoardSection;
import com.nekomc.leCorePlugin.randomStuff.BoardType;

public class PlayerBoardUpdateEvent extends Event {
	
	UUID id;
	BoardType boardType;
	BoardSection boardSection;
	
	public PlayerBoardUpdateEvent(UUID uuid, BoardType bType, BoardSection bSection) {
		
		id = uuid;
		boardType = bType;
		boardSection = bSection;
		
	}
	
	public UUID getPlayerUniqueId() {
		
		return id;
		
	}
	
	public BoardType getBoardType() {
		
		return boardType;
		
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
