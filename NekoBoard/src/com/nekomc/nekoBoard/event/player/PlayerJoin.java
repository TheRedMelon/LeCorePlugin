package com.nekomc.nekoBoard.event.player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.nekomc.nekoBoard.NekoBoard;

public class PlayerJoin implements Listener {
	
	ScoreboardManager sbm = Bukkit.getScoreboardManager();
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
				
		Object inst = null;
			
		try {
				
			inst = NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld().getName()).newInstance();
				
		} catch (InstantiationException | IllegalAccessException e1) {
				
			e1.printStackTrace();
				
		}
		
		Scoreboard sb = sbm.getNewScoreboard();
		Objective obj = sb.registerNewObjective(inst.getClass().getSimpleName(), "dummy");
			
		obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Method sp = null;
		
		try {
			
			sp = NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld().getName()).getMethod("showPlayer", new Class[] {UUID.class, Objective.class});
			
		} catch (NoSuchMethodException | SecurityException e1) {
			
			e1.printStackTrace();
			
		}
		
		if (sp != null) {
			
			try {
				
				sp.invoke(inst, e.getPlayer().getUniqueId(), obj);
				
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				
				e1.printStackTrace();
				
			}
			
		}
		
		e.getPlayer().setScoreboard(sb);
			
	}
	
}
