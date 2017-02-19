package com.nekomc.nekoBoard.event.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import com.nekomc.nekoBoard.NekoBoard;

import customEvents.GeneralScoreboardUpdateEvent;

public class PlayerJoin implements Listener {

	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
				
			Object inst = null;
			
			try {
				
				inst = NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld().getName()).newInstance();
				
			} catch (InstantiationException | IllegalAccessException e1) {
				
				e1.printStackTrace();
				
			}
			
			NekoBoard.plugin.playerBoards.put(e.getPlayer().getUniqueId(), inst);
		
		pm.callEvent(new GeneralScoreboardUpdateEvent());
		
	}
	
}
