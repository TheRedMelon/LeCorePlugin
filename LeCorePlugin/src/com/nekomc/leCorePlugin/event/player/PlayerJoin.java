package com.nekomc.leCorePlugin.event.player;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nekomc.leCorePlugin.LeCorePlugin;

public class PlayerJoin implements Listener {

	LeCorePlugin plugin = LeCorePlugin.plugin;
	
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		plugin.playerCount = Bukkit.getServer().getOnlinePlayers().size();
		
	}
	
}
