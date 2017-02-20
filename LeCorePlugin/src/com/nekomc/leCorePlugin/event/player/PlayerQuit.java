package com.nekomc.leCorePlugin.event.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.nekomc.leCorePlugin.LeCorePlugin;

public class PlayerQuit implements Listener {

	LeCorePlugin plugin = LeCorePlugin.plugin;
	
	@EventHandler
	public void onPlayerQuit (PlayerQuitEvent e) {
		
		plugin.playerCount = plugin.playerCount - 1;
		
	}
	
}
