package me.thedreps.nekochat.events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.thedreps.nekochat.Rank;

public class PlayerQuit implements Listener{
	
	Rank rankClass = new Rank();
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		
		UUID uuid = e.getPlayer().getUniqueId();
		
		String joinPrefix = ChatColor.WHITE + "[" + ChatColor.RED + "-" + ChatColor.WHITE + "]";
		String rank = rankClass.getColoredRank(uuid);
		
		if(!(rankClass.getRank(uuid).equals("No Rank"))){
			e.setQuitMessage(joinPrefix + " " + rank + ChatColor.RESET + " " + e.getPlayer().getName());
		}else{
			e.setQuitMessage(joinPrefix + ChatColor.RESET + " " + e.getPlayer().getName());
		}
		
	}

}
