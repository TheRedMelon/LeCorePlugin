package come.nekomc.bungeecore.playerMisc;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Misc {
	
	  public String getRank(UUID uuid)
	  {
	    ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
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
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
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
