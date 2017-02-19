package com.nekomc.nekoBoard;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import customEvents.GeneralScoreboardUpdateEvent;

public class PlayerQuit implements Listener {

	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	@EventHandler
	public void onPlayerQuit (PlayerQuitEvent e) {
		
		pm.callEvent(new GeneralScoreboardUpdateEvent());
		
	}
	
}
