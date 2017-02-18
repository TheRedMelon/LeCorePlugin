package com.nekomc.nekoBoard.event.player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nekomc.nekoBoard.NekoBoard;
import com.nekomc.nekoBoard.boards.Hub;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		Method sp = null;
		
		try {
			
			sp = NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld()).getClass().getMethod("showPlayer");
			
		} catch (NoSuchMethodException | SecurityException e1) {
			
			e1.printStackTrace();
			
		}
		
		if (sp != null) {
			
			try {
				
				sp.invoke(NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld()), e.getPlayer().getUniqueId());
				
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				
				e1.printStackTrace();
				
			}
			
		} else {
			
			new Hub().showPlayer(e.getPlayer().getUniqueId());
			
		}
		
	}
	
}
