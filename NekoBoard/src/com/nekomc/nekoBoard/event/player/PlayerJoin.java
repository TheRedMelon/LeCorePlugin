package com.nekomc.nekoBoard.event.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import com.nekomc.leCorePlugin.LeCorePlugin;
import com.nekomc.nekoBoard.Hub;

import customEvents.GeneralScoreboardUpdateEvent;

public class PlayerJoin implements Listener {

	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		String board = LeCorePlugin.plugin.worldBoards.get(e.getPlayer().getWorld());
		
		if (board.equals("Hub")) {
			
			new Hub().showPlayer(e.getPlayer().getUniqueId());
			
		}
		
		pm.callEvent(new GeneralScoreboardUpdateEvent());
		
	}
	
}
