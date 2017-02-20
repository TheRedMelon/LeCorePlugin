package com.nekomc.leCorePlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

	LeCorePlugin plugin = LeCorePlugin.plugin;
	
	@EventHandler
	public void onPlayerQuit (PlayerQuitEvent e) {
		
		plugin.playerCount = Bukkit.getServer().getOnlinePlayers().size();
		
	}
	
}
