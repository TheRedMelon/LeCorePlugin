package me.thedreps.nekochat.events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.thedreps.nekochat.NekoChat;
import me.thedreps.nekochat.Rank;

public class PlayerJoin implements Listener{
	
	Rank rankClass = new Rank();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		
		UUID uuid = e.getPlayer().getUniqueId();
		
		String joinPrefix = ChatColor.WHITE + "[" + ChatColor.GREEN + "+" + ChatColor.WHITE + "]";
		String rank = rankClass.getColoredRank(uuid);
		
		e.setJoinMessage(joinPrefix + " " + rank + ChatColor.RESET + e.getPlayer().getName());

		
		NekoChat.plugin.getNamesConfig().set(e.getPlayer().getName().toLowerCase(), rankClass.getRank(e.getPlayer().getUniqueId()));
		NekoChat.plugin.saveNames();
		
	}

}
