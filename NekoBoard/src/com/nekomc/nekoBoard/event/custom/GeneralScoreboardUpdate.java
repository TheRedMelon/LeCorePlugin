package com.nekomc.nekoBoard.event.custom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.nekomc.nekoBoard.NekoBoard;

import customEvents.GeneralScoreboardUpdateEvent;

public class GeneralScoreboardUpdate implements Listener {

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onGeneralScoreboardUpdate (GeneralScoreboardUpdateEvent e) {
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
		
			Method sp = null;
			
			try {
				
				sp = NekoBoard.plugin.worldBoards.get(p.getWorld().getName()).getMethod("showPlayer", new Class[] {UUID.class});
				
			} catch (NoSuchMethodException | SecurityException e1) {
				
				e1.printStackTrace();
				
			}
			
			if (sp != null) {
				
				try {
					
					sp.invoke(NekoBoard.plugin.playerBoards.get(p.getUniqueId()), p.getUniqueId());
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		}
		
	}
	
}
