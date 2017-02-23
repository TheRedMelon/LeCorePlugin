package com.nekomc.nekoBoard.event.custom;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.nekomc.leCorePlugin.LeCorePlugin;
import com.nekomc.leCorePlugin.customEvents.PlayerBoardUpdateEvent;
import com.nekomc.leCorePlugin.playerMisc.Leveling;
import com.nekomc.leCorePlugin.randomStuff.BoardSection;
import com.nekomc.leCorePlugin.randomStuff.BoardType;
import com.nekomc.nekoBoard.NekoBoard;

public class PlayerBoardUpdate implements Listener {

	@EventHandler
	public void onPlayerBoardUpdate (PlayerBoardUpdateEvent e) {
		
		Objective obj = Bukkit.getPlayer(e.getPlayerUniqueId()).getScoreboard().getObjective("board");
		Scoreboard sb = Bukkit.getPlayer(e.getPlayerUniqueId()).getScoreboard();
		
		if (e.getBoardType() == BoardType.HUB) {	
		
			if (e.getBoardSection() == BoardSection.MONEY) {
				
				Score money = null;
				
				for (String score : sb.getEntries()) {
					
					Set<Score> i = sb.getScores(score);
					
					for (Score s : i) {
						
						if (s.getObjective() == obj) {
							
							if (s.getScore() == 18) {
								
								money = s;
								
							}
							
						}
						
					}
					
				}
				
				if (money == null) {
					
					return;
					
				} else {
					
					sb.resetScores(money.getEntry());
					
				}
				
				Score money2 = obj.getScore(ChatColor.LIGHT_PURPLE + "$" + Double.toString(NekoBoard.economy.getBalance(Bukkit.getPlayer(e.getPlayerUniqueId()))));
				
				money2.setScore(money.getScore());
				
			}
			
			if (e.getBoardSection() == BoardSection.LEVEL) {
				
				Score level = null;
				
				for (String score : sb.getEntries()) {
					
					Set<Score> i = sb.getScores(score);
					
					for (Score s : i) {
						
						if (s.getObjective() == obj) {
							
							if (s.getScore() == 15) {
								
								level = s;
								
							}
							
						}
						
					}
					
				}
				
				if (level == null) {
					
					return;
					
				} else {
					
					sb.resetScores(level.getEntry());
					
				}
				
				Score level2 = obj.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(new Leveling().getLevel(e.getPlayerUniqueId())) + " " + new Leveling().getProgress(e.getPlayerUniqueId()));
				
				level2.setScore(level.getScore());
				
			}
			
			if (e.getBoardSection() == BoardSection.SERVER) {
				
				Score server = null;
				
				for (String score : sb.getEntries()) {
					
					Set<Score> i = sb.getScores(score);
					
					for (Score s : i) {
						
						if (s.getObjective() == obj) {
							
							if (s.getScore() == 21) {
								
								server = s;
								
							}
							
						}
						
					}
					
				}
				
				if (server == null) {
					
					return;
					
				} else {
					
					sb.resetScores(server.getEntry());
					
				}
				
				Score server2 = obj.getScore(ChatColor.LIGHT_PURPLE + LeCorePlugin.plugin.worldAlias.get(Bukkit.getPlayer(e.getPlayerUniqueId()).getWorld().getName()));
				
				server2.setScore(server.getScore());
				
			}
		
		}
			
	}
	
}
