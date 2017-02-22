package com.nekomc.leCorePlugin.playerMisc;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Misc {
	
	  public String getRank(UUID uuid)
	  {
	    Player player = Bukkit.getServer().getPlayer(uuid);
	    if (player.hasPermission("rank.slt")) {
	      return "SLT";
	    }
	    if (player.hasPermission("rank.admin")) {
	      return "ADMIN";
	    }
	    if (player.hasPermission("rank.mod")) {
	      return "MOD";
	    }
	    if (player.hasPermission("rank.default")) {
	      return "No Rank";
	    }
	    return null;
	  }
	  
	  public String getColoredRank(UUID uuid)
	  {
	    Player player = Bukkit.getServer().getPlayer(uuid);
	    if (player.hasPermission("rank.slt")) {
	      return ChatColor.DARK_RED + "" + ChatColor.BOLD + "SLT" + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.admin")) {
	      return ChatColor.AQUA + "" + ChatColor.BOLD + "ADMIN" + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.mod")) {
	      return ChatColor.GOLD + "" + ChatColor.BOLD + "MOD" + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.default")) {
	      return "";
	    }
	    return "";
	  }
	
}
