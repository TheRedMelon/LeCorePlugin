package me.thedreps.nekochat.events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.nekomc.nekocore.api.RankAPI;

public class PlayerQuit implements Listener{
	
	RankAPI rankClass = new RankAPI();
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		
		UUID uuid = e.getPlayer().getUniqueId();
		
		String joinPrefix = ChatColor.WHITE + "[" + ChatColor.RED + "-" + ChatColor.WHITE + "]";
		String rank = rankClass.getColoredRank(uuid);
		
		e.setQuitMessage(joinPrefix + " " + rank + ChatColor.RESET + e.getPlayer().getName());

	}

}
