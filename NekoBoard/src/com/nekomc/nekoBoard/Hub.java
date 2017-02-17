package com.nekomc.nekoBoard;

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
import com.nekomc.leCorePlugin.playerMisc.Misc;

public class Hub {

	ScoreboardManager sbm = Bukkit.getServer().getScoreboardManager();
	BukkitScheduler bs = Bukkit.getServer().getScheduler();
	
	public void showPlayer (UUID id) {
		
		Scoreboard serverBoard = sbm.getNewScoreboard();
		Scoreboard playerBoard = sbm.getNewScoreboard();
		Objective serverObje = serverBoard.registerNewObjective("server", "dummy");
		Objective playerObje = playerBoard.registerNewObjective("player", "dummy");
		
		serverObje.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks Server");
		playerObje.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks Player");
		serverObje.setDisplaySlot(DisplaySlot.SIDEBAR);
		playerObje.setDisplaySlot(DisplaySlot.SIDEBAR);
		
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
		Score blank7 = serverObje.getScore("       ");
		
		Score pLine1 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------");
		Score pBlank1 = serverObje.getScore(" ");
		Score pLevel1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Level:");
		Score pLevel2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(new Leveling().getLevel(id)));
		Score pBlank2 = serverObje.getScore("  ");
		Score pXp1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "XP For Next Level:");
		Score pXp2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + new Leveling().getProgress(id));
		Score pBlank3 = serverObje.getScore("   ");
		Score pRank1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Rank:");
		Score pRank2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + new Misc().getRank(id));
		Score pBlank4 = serverObje.getScore("    ");
		Score pLine2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------ ");
		
		line1.setScore(21);
		blank1.setScore(20);
		server1.setScore(19);
		server2.setScore(18);
		blank6.setScore(17);
		pCount1.setScore(16);
		pCount2.setScore(15);
		blank7.setScore(14);
		website1.setScore(13);
		website2.setScore(12);
		blank5.setScore(11);
		line2.setScore(10);
		
		pLine1.setScore(21);
		pBlank1.setScore(20);
		pLevel1.setScore(19);
		pLevel2.setScore(18);
		pBlank2.setScore(17);
		pXp1.setScore(16);
		pXp2.setScore(15);
		pBlank3.setScore(14);
		pRank1.setScore(13);
		pRank2.setScore(12);
		pBlank4.setScore(11);
		pLine2.setScore(10);
		
		bs.scheduleSyncRepeatingTask(NekoBoard.plugin, new Runnable() {

			@Override
			public void run() {
				
				Bukkit.getServer().getPlayer(id).setScoreboard(playerBoard);
				
				bs.scheduleSyncDelayedTask(NekoBoard.plugin, new Runnable() {

					@Override
					public void run() {
						
						Bukkit.getServer().getPlayer(id).setScoreboard(serverBoard);
						
					}
					
				}, 200);
				
			}
			
		}, 0, 400);
		
	}
	
}
