package com.nekomc.nekoBoard.event.player;

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
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
				
			Object inst = null;
			
			try {
				
				inst = NekoBoard.plugin.worldBoards.get(e.getPlayer().getWorld().getName()).newInstance();
				
			} catch (InstantiationException | IllegalAccessException e1) {
				
				e1.printStackTrace();
				
			}
			
			Scoreboard sb = sbm.getNewScoreboard();
			Objective obj = sb.registerNewObjective("board", "dummy");
			
			NekoBoard.plugin.playerBoardClassInst.put(e.getPlayer().getUniqueId(), inst);
			NekoBoard.plugin.playerBoard.put(e.getPlayer().getUniqueId(), sb);
			NekoBoard.plugin.playerObj.put(e.getPlayer().getUniqueId(), obj);
			
			obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			e.getPlayer().setScoreboard(sb);
			
	}
	
}
