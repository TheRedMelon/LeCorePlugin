package com.nekomc.leCorePlugin.playerMisc;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
	
}
