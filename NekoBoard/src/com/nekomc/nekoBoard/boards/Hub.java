package com.nekomc.nekoBoard.boards;

import java.text.DecimalFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import com.nekomc.leCorePlugin.LeCorePlugin;
import com.nekomc.leCorePlugin.playerMisc.Leveling;
import com.nekomc.nekoBoard.NekoBoard;

public class Hub {
	
	Score server2 = null;
	Score pMoney2 = null;
	Score pLevel2 = null;
	
	public void showPlayer (UUID id, Objective serverObje) {
		
		Double money = NekoBoard.economy.getBalance(Bukkit.getPlayer(id));
		DecimalFormat df = new DecimalFormat("#.00");
		
		Score line1 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------");
		Score blank1 = serverObje.getScore(" ");
		Score website1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Website:");
		Score website2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "www.nekomc.com");
		Score blank5 = serverObje.getScore("     ");
		Score line2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "------------------ ");
		Score server1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Server:");
		server2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + LeCorePlugin.plugin.worldAlias.get(Bukkit.getPlayer(id).getWorld().getName()));
		Score blank6 = serverObje.getScore("      ");
		Score pCount1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Money:");
		pMoney2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + "$" + df.format(money));
		Score pBlank1 = serverObje.getScore("              ");
		Score pLevel1 = serverObje.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Level:");
		pLevel2 = serverObje.getScore(ChatColor.LIGHT_PURPLE + Integer.toString(new Leveling().getLevel(id)) + " " + new Leveling().getProgress(id));
		Score pBlank2 = serverObje.getScore("  ");
		
		line1.setScore(24);
		blank1.setScore(23);
		server1.setScore(22);
		server2.setScore(21);
		blank5.setScore(20);
		pCount1.setScore(19);
		pMoney2.setScore(18);
		blank6.setScore(17);
		pLevel1.setScore(16);
		pLevel2.setScore(15);
		pBlank2.setScore(14);
		website1.setScore(13);
		website2.setScore(12);
		pBlank1.setScore(11);
		line2.setScore(10);
		
	}
	
	public Score getServer() {
		
		return server2;
		
	}
	
	public Score getMoney() {
		
		return pMoney2;
		
	}
	
	public Score getLevel() {
		
		return pLevel2;
		
	}
		
}
