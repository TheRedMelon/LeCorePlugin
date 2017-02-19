package com.nekomc.nekoBoard.boards;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.nekomc.leCorePlugin.LeCorePlugin;
import com.nekomc.leCorePlugin.playerMisc.Leveling;

public class Hub {

	ScoreboardManager sbm = Bukkit.getServer().getScoreboardManager();
	BukkitScheduler bs = Bukkit.getServer().getScheduler();
	
	Scoreboard serverBoard = sbm.getNewScoreboard();
	Objective serverObje = serverBoard.registerNewObjective("server", "dummy");
	
	public void showPlayer (UUID id) {
		
		if (!(serverObje.getDisplayName() == ("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks")) || !(serverObje.getDisplaySlot().equals(DisplaySlot.SIDEBAR))) {
		
			serverObje.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
			serverObje.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		}
		
		Score line1 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------");
		Score blank1 = serverObje.getScore(" ");
		Score website1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Website:");
		Score website2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "www.nekomc.com");
		Score blank5 = serverObje.getScore("     ");
		Score line2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------ ");
		Score server1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Server:");
		Score server2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + LeCorePlugin.plugin.worldAlias.get(Bukkit.getPlayer(id).getWorld().getName()));
		Score blank6 = serverObje.getScore("      ");
		Score pCount1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Online Players:");
		Score pCount2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(LeCorePlugin.plugin.playerCount) + "/" + Integer.toString(LeCorePlugin.plugin.maxPlayers));
		Score pBlank1 = serverObje.getScore("              ");
		Score pLevel1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Level:");
		Score pLevel2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(new Leveling().getLevel(id)) + " " + new Leveling().getProgress(id));
		Score pBlank2 = serverObje.getScore("  ");
		
		for (String e : serverBoard.getEntries()) {
			
			if (serverObje.getScore(e).getScore() == 18 || serverObje.getScore(e).getScore() == 15) {
				
				serverBoard.resetScores(e);
				
			}
			
		}
		
		line1.setScore(24);
		blank1.setScore(23);
		server1.setScore(22);
		server2.setScore(21);
		blank5.setScore(20);
		pCount1.setScore(19);
		pCount2.setScore(18);
		blank6.setScore(17);
		pLevel1.setScore(16);
		pLevel2.setScore(15);
		pBlank2.setScore(14);
		website1.setScore(13);
		website2.setScore(12);
		pBlank1.setScore(11);
		line2.setScore(10);
		
		if (!Bukkit.getServer().getPlayer(id).getScoreboard().equals(serverBoard)) {
			
			Bukkit.getServer().getPlayer(id).setScoreboard(serverBoard);
	
		}
		
	}
		
}
