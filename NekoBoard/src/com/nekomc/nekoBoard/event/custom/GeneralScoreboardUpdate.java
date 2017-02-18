package com.nekomc.nekoBoard.event.custom;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.nekomc.leCorePlugin.LeCorePlugin;
import com.nekomc.nekoBoard.Hub;

public class GeneralScoreboardUpdate implements Listener {

	@EventHandler
	public void onGeneralScoreboardUpdate() {
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			UUID id = p.getUniqueId();
			
			String playerWorld = p.getWorld().getName();
			String playerBoard = LeCorePlugin.plugin.worldBoards.get(playerWorld);
			
			if (playerBoard.equals("Hub")) {
				
				new Hub().showPlayer(id);
				
			}
			
		}
		
	}
	
}
