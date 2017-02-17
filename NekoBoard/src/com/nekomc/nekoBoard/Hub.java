package com.nekomc.nekoBoard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	
	public void showPlayer (UUID id) {
		
		Scoreboard board = sbm.getNewScoreboard();
		Objective obje = board.registerNewObjective("board", "dummy");
		
		obje.setDisplaySlot(DisplaySlot.SIDEBAR);
		obje.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
		
		Score line1 = obje.getScore(ChatColor.LIGHT_PURPLE + "------------------");
		Score blank1 = obje.getScore(" ");
		Score level1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Level:");
		Score level2 = obje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(new Leveling().getLevel(id)));
		Score blank2 = obje.getScore(" ");
		Score xp1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "XP For Next Level:");
		Score xp2 = obje.getScore(ChatColor.LIGHT_PURPLE + new Leveling().getProgress(id));
		Score blank3 = obje.getScore(" ");
		Score rank1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Rank:");
		Score rank2 = obje.getScore(ChatColor.LIGHT_PURPLE + new Misc().getRank(id));
		Score blank4 = obje.getScore(" ");
		Score website1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Website:");
		Score website2 = obje.getScore(ChatColor.LIGHT_PURPLE + "www.nekomc.com");
		Score blank5 = obje.getScore(" ");
		Score line2 = obje.getScore(ChatColor.LIGHT_PURPLE + "------------------");
		Score server1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Server:");
		Score server2 = obje.getScore(ChatColor.LIGHT_PURPLE + LeCorePlugin.plugin.worldAlias.get(Bukkit.getPlayer(id).getWorld().getName()));
		Score blank6 = obje.getScore(" ");
		Score pCount1 = obje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Online Players:");
		Score pCount2 = obje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(LeCorePlugin.plugin.playerCount) + "/" + Integer.toString(LeCorePlugin.plugin.maxPlayers));
		Score blank7 = obje.getScore(" ");
		
		line1.setScore(30);
		blank1.setScore(29);
		server1.setScore(28);
		server2.setScore(27);
		blank6.setScore(26);
		pCount1.setScore(25);
		pCount2.setScore(24);
		blank7.setScore(23);
		level1.setScore(22);
		level2.setScore(21);
		blank2.setScore(20);
		xp1.setScore(19);
		xp2.setScore(18);
		blank3.setScore(17);
		rank1.setScore(16);
		rank2.setScore(15);
		blank4.setScore(14);
		website1.setScore(13);
		website2.setScore(12);
		blank5.setScore(11);
		line2.setScore(10);
		
		Bukkit.getServer().getPlayer(id).setScoreboard(board);
		
	}
	
}
