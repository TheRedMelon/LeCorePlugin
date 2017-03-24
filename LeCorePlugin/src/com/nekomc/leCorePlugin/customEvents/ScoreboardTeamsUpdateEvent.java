package com.nekomc.leCorePlugin.customEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

public class ScoreboardTeamsUpdateEvent extends Event {
	
	Team t;
	String s;
	
	public ScoreboardTeamsUpdateEvent(Team team, String name) {
		
		t = team;
		s = name;
		
	}
	
	public Team getTeam() {
		
		return t;
		
	}
	
	public String getName() {
		
		return s;
		
	}
	
	private static final HandlerList handlers = new HandlerList();
	 
	public HandlerList getHandlers() {
		
	    return handlers;
	    
	}
	 
	public static HandlerList getHandlerList() {
		
	    return handlers;
	    
	}
	
}
