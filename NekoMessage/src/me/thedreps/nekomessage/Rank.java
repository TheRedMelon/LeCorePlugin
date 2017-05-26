package me.thedreps.nekomessage;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Rank {
	
	  public String getRank(UUID uuid)
	  {
	    ProxiedPlayer player = NekoMessage.plugin.getProxy().getPlayer(uuid);
	    if (player.hasPermission("rank.slt")) {
	      return "SLT";
	    }
	    if (player.hasPermission("rank.admin")) {
	      return "ADMIN";
	    }
	    if (player.hasPermission("rank.mod")) {
	      return "MOD";
	    }
	    if (player.hasPermission("rank.nekochan")){
	    	return "NEKOCHAN";
	    }
	    if (player.hasPermission("rank.koneko")){
	    	return "KONEKO";
	    }
	    if (player.hasPermission("rank.default")) {
	      return "No Rank";
	    }
	    return "No Rank";
	  }
	  
	  public String getColoredRank(UUID uuid)
	  {
		  ProxiedPlayer player = NekoMessage.plugin.getProxy().getPlayer(uuid);
	    if (player.hasPermission("rank.slt")) {
	      return ChatColor.DARK_RED + "" + ChatColor.BOLD + "SLT " + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.admin")) {
	      return ChatColor.AQUA + "" + ChatColor.BOLD + "ADMIN " + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.mod")) {
	      return ChatColor.GOLD + "" + ChatColor.BOLD + "MOD " + ChatColor.RESET;
	    }
	    if (player.hasPermission("rank.nekochan")){
	    	return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Neko-chan ";
	    }
	    if (player.hasPermission("rank.koneko")){
	    	return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Koneko ";
	    }
	    if (player.hasPermission("rank.default")) {
	      return "";
	    }
	    return "";
	  }

}
